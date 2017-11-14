package com.android.voicenotes.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by maria on 05.12.16.
 */

public class CalendarUtils {

    public static Date getCalendarDate(long timestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return  calendar.getTime();
    }

    public static String getDate(long timestamp){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date currenTimeZone = getCalendarDate(timestamp);
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
            Log.e("CalendarUtils", e.getStackTrace().toString());
        }
        return "";
    }

    public static String getTime(long timestamp){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date currenTimeZone = getCalendarDate(timestamp);
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
            Log.e("CalendarUtils", e.getStackTrace().toString());
        }
        return "";
    }

    public static String getMinutes(long timestamp){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("mm");
            Date currenTimeZone = getCalendarDate(timestamp);
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
            Log.e("CalendarUtils", e.getStackTrace().toString());
        }
        return "";
    }

    public static long getDay(long time){
        int i = getCalendarDate(time).getDate();
        Date date = new Date(i);
        return date.getTime();
    }

    public static String getWeekDate(long timestamp){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM ", Locale.US);
            Date currenTimeZone = getCalendarDate(timestamp);
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
            Log.e("CalendarUtils", e.getStackTrace().toString());
        }
        return "";
    }

    public static String getWeekDateWithYear(long timestamp){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.US);
            Date currenTimeZone = getCalendarDate(timestamp);
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
            Log.e("CalendarUtils", e.getStackTrace().toString());
        }
        return "";
    }

    public static String getTodayDate(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        return formatDate(today);
    }

    public static String getTomorrow(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        return formatDate(tomorrow);
    }

    public static String getAfterTomorrow(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date tomorrow = calendar.getTime();
        return formatDate(tomorrow);
    }

    public static String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
        return sdf.format(date);
    }

    public static String getSheduleDate(long timestamp){
        try{
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(timestamp);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            Date currenTimeZone = calendar.getTime();
            return formatDate(currenTimeZone);
        }catch (Exception e) {
            Log.e("CalendarUtils", e.getStackTrace().toString());
        }
        return "";
    }

    public static boolean checkTodayArriving(long departureTime, long arrivalTime){
        Calendar arrival = Calendar.getInstance();
        arrival.setTime(getCalendarDate(arrivalTime));
        Calendar departure = Calendar.getInstance();
        departure.setTime(getCalendarDate(departureTime));
        return  (departure.get(Calendar.YEAR) == arrival.get(Calendar.YEAR) && departure.get(Calendar.DAY_OF_YEAR) == arrival.get(Calendar.DAY_OF_YEAR));
    }

}
