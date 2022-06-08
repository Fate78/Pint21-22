package com.pint.roombookerfinal;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Methods implements MethodsInterface{

    public String formatTimeForUser(String time){
        StringBuilder sb = new StringBuilder(time);
        sb.delete(sb.length()-3, sb.length());
        return sb.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String formatDateForUser(String string_date){

        LocalDate date = LocalDate.parse(string_date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return formatter.format(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate stringToDate(String string_date){
        LocalDate date = LocalDate.parse(string_date);
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getDateToday(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String formattedDate = formatter.format(today);
        return stringToDate(formattedDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalTime stringToTime(String string_time){
        return LocalTime.parse(string_time);
    }
}
