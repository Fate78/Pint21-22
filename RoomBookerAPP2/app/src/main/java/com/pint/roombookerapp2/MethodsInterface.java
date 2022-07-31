package com.pint.roombookerapp2;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public interface MethodsInterface {
    String formatTimeForUser(String time);

    String formatDateForUser(String string_date);

    LocalDate stringToDate(String string_date);

    LocalDate getDateToday();

    LocalTime stringToTime(String string_time);

    String formatDateForAPI(String string_date);

    LocalTime addDurationToHour(LocalTime hora, Duration duration);

    Duration stringToDuration(String duration);

    void disableSoftInputFromAppearing(EditText editText);

    void popTimePicker(View view, EditText editText);

    void popDatePicker(View view, EditText editText);

    void generateQrCode(int idSala, ImageView img_qrCode);
}
