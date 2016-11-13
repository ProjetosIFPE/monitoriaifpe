package com.softwarecorporativo.monitoriaifpe.modelo.util;

import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Semestre;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    public static Integer getMonthOfDate(Date dataInicio, Date dataFim) {

        Calendar cal = new GregorianCalendar();
        cal.setTime(dataInicio);
        int mesDataInicio = cal.get(Calendar.MONTH);
        cal.setTime(dataFim);
        int mesDataFim = cal.get(Calendar.MONTH);
        int mes = mesDataFim - mesDataInicio;
        if (mes == 0) {
            mes = mesDataInicio;
        } else {
             mes += mesDataInicio;
        }
        return mes;
    }

    public static Date getTime(Integer horas, Integer minutos, Integer segundos) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, horas);
        c.set(Calendar.MINUTE, minutos);
        c.set(Calendar.SECOND, segundos);
        return c.getTime();
    }

    public static String obterNomeMes(int mes) {
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return meses[mes - 1];
    }
    
     public static String getRandomString() {
        int i = (int) (Math.random() * 10000000);
        return String.valueOf(i);
    }

}
