package com.pint.roombookerfinal;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        String new_string_date = formatter.format(date);

        return new_string_date;
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
}
