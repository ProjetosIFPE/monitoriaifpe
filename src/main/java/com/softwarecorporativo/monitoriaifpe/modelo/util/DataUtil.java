package com.softwarecorporativo.monitoriaifpe.modelo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataUtil {

    public static String formatarData(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
        return formatter.format(data);
    }

    public static String formatarDataHoraMinuto(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", new Locale("pt", "br"));
        return formatter.format(data);
    }

    public static Date adicionarUmaHoraEmData(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
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
        if (!((mesDataFim - mesDataInicio) == 0)) {
            if (!(mesDataInicio + 1 == mesDataFim)) {
                mesDataInicio += 1;
            }
            mesDataInicio %= 12;
        }
        mesDataInicio += 1;
        return mesDataInicio;
    }

    public static Date getTime(Integer horas, Integer minutos, Integer segundos) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, horas);
        c.set(Calendar.MINUTE, minutos);
        c.set(Calendar.SECOND, segundos);
        return c.getTime();
    }

    public static String obterNomeMes(int mes) {
        String nomeMes = "";
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        if (mes >= 1 && mes <= 12) {
            nomeMes = meses[mes - 1];
        }
        return nomeMes;
    }

}
