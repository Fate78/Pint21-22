package com.pint.roombookerfinal;

import java.time.LocalDate;
import java.time.LocalTime;

public interface MethodsInterface {
    String formatTimeForUser(String time);

    String formatDateForUser(String string_date);

    LocalDate stringToDate(String string_date);

    LocalDate getDateToday();

    LocalTime stringToTime(String string_time);
}
