package com.pint.roombookerfinal;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Methods implements MethodsInterface{

    public String formatTimeForUser(String time){
        StringBuilder sb = new StringBuilder(time);
        sb.delete(sb.length()-3, sb.length());
        return sb.toString();
    }

    public String formatDateForUser(String string_date){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Date date = stringToDate(string_date);

        return formatter.format(date);
    }

    public Date stringToDate(String string_date){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = formatter.parse(string_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public Date getDateToday(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String formattedDate = formatter.format(today);
        return stringToDate(formattedDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalTime stringToTime(String string_time){
        return LocalTime.parse(string_time);
    }
}
