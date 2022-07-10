package com.pint.roombookerfinal;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;
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
    public String formatDateForAPI(String string_date) {

        try {
            DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(string_date, date_format);
            return formatter.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return string_date;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalTime addDurationToHour(LocalTime hora, Duration duration){
        LocalTime nova_hora = (LocalTime) duration.addTo(hora);
        return nova_hora;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Duration stringToDuration(String duration)
    {
        return Duration.between(LocalTime.MIN, LocalTime.parse(duration));
    }
}
