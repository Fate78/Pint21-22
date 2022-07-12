package com.pint.roombookerfinal;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class Methods implements MethodsInterface{

    int hour, minute;

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

    public void disableSoftInputFromAppearing(EditText editText) {
        editText.setRawInputType(InputType.TYPE_NULL);
    }

    public void popTimePicker(View view, EditText editText){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                editText.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), onTimeSetListener, hour, minute, true);
        timePickerDialog.show();
    }

    public void popDatePicker(View view, EditText editText)
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "-" + month + "-" + year;
                editText.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), style, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

}
