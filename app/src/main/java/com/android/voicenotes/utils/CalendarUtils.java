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

    public static long getDay(long time){
        int i = getCalendarDate(time).getDate();
        Date date = new Date(i);
        return date.getTime();
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

}
