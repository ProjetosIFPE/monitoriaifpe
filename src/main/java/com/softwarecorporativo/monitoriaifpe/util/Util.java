package com.softwarecorporativo.monitoriaifpe.util;

import com.softwarecorporativo.monitoriaifpe.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.NegocioException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Util {

    public static String criptografarSenha(String senha, String chave, String hash) {

        String senhaCriptografada = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(hash);
            messageDigest.update(senha.getBytes());
            senhaCriptografada = stringHexa(messageDigest.digest(chave.getBytes()));
        } catch (NoSuchAlgorithmException e) {
        }
        return senhaCriptografada;
    }

    private static String stringHexa(byte[] bytes) {

        StringBuffer retorno = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            retorno.append(Integer.toHexString((((bytes[i] >> 4) & 0xf) << 4) | (bytes[i] & 0xf)));
        }

        return retorno.toString();
    }

    public static void enviarEmail(String destinatario, String assunto, String conteudo) throws NegocioException {

        SimpleEmail email = new SimpleEmail();
        try {
            email.addTo(destinatario);
            email.setHostName(Constantes.CONSTANTE_SERVIDOR_SMTP);
            email.setSmtpPort(465);
            email.setFrom(Constantes.CONSTANTE_LOGIN_EMAIL_SISTEMA);
            email.setSubject(assunto);
            email.setMsg(conteudo);
            email.setSSLOnConnect(Boolean.TRUE);
            email.setAuthentication(Constantes.CONSTANTE_LOGIN_EMAIL_SISTEMA,
                    Constantes.CONSTANTE_SENHA_EMAIL_SISTEMA);
            email.send();
        } catch (EmailException e) {
            throw new NegocioException(Constantes.ERRO_ENVIO_EMAIL, e);
        }
    }

    public static String gerarSenhaAleatoria() {
        int qtdeMaximaCaracteres = 8;
        String[] caracteres = {"a", "1", "b", "2", "4", "5", "6", "7", "8",
            "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
            "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};

        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < qtdeMaximaCaracteres; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            senha.append(caracteres[posicao]);
        }
        return senha.toString();
    }

    public static String formatarData(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
        return formatter.format(data);
    }

    public static Date parseTextoData(String texto) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
        return fmt.parse(texto);
    }

    public static String obterNomeMes(int mes) {
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return meses[mes - 1];
    }

    public static boolean isNull(Object object) {
        boolean isNull = Boolean.FALSE;
        if (object == null) {
            isNull = Boolean.TRUE;
        }
        return isNull;
    }

    public static String invalidarCampoCadastrado(boolean cadastrado) {
        if (cadastrado) {
            return "invalido";
        }
        return "valido";
    }

}
