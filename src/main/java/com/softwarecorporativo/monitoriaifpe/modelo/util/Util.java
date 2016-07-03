package com.softwarecorporativo.monitoriaifpe.modelo.util;

import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Semestre;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Util {

    public static String formatarData(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
        return formatter.format(data);
    }

    public static Date getDate(Integer dia, Integer mes, Integer ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        return c.getTime();
    }

    public static Date getTime(Integer horas, Integer minutos, Integer segundos) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, horas);
        c.set(Calendar.MINUTE, minutos);
        c.set(Calendar.SECOND, segundos);
        return c.getTime();
    }

    public static Periodo getSemestreAtual() {
        Periodo periodo = new Periodo();
        Calendar dataAtual = Calendar.getInstance();
        int anoAtual = dataAtual.get(Calendar.YEAR);
        Calendar inicioSegundoSemestre = Calendar.getInstance();
        inicioSegundoSemestre.set(anoAtual, 7, 1);
        if (dataAtual.after(inicioSegundoSemestre)) {
            periodo.setSemestre(Semestre.SEGUNDO);
        } else {
            periodo.setSemestre(Semestre.PRIMEIRO);
        }
        periodo.setAno(anoAtual);

        return periodo;
    }

    public static String obterNomeMes(int mes) {
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return meses[mes - 1];
    }

 

}
