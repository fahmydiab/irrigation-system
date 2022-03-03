package com.example.irrigationsystem.util;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static java.sql.Date convertToSqlDate(Date date) {
        java.sql.Date convertedDate = new java.sql.Date(date.getTime());
        return convertedDate;
    }

    public static String convertToSqlStringDate(Date date) {
        if (date == null) {
            return null;
        }
        java.sql.Date convertedDate = convertToSqlDate(date);
        return convertedDate.toString();
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }

    public static Date convertToDateWithDefaultTimezone(Date dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate());
    }

    public static boolean areSameDay(Date date1, Date date2) {
        LocalDate localDate1 = date1.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
        LocalDate localDate2 = date2.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
        return localDate1.isEqual(localDate2);
    }

    public static boolean afterDayResolution(Date date1, Date date2) {
        DateTime d1 = new DateTime(date1);
        DateTime d2 = new DateTime(date2);
        return d1.withTimeAtStartOfDay().compareTo(d2.withTimeAtStartOfDay()) >= 0;
    }

    public static String format(Date date) {
        return format("dd-MM-yyyy", date);
    }

    public static String format(String format, Date date) {
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        return format1.format(date);
    }

    public static Date mapToStartOfDay(Date date) {
        return new DateTime(date).withTime(0, 0, 0, 0).toDate();
    }

    public static Date mapToEndOfDay(Date date) {
        return new DateTime(date).withTime(23, 59, 59, 999).toDate();
    }
}
