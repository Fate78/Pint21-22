package com.pint.roombookerfinal;

import java.time.LocalTime;
import java.util.Date;

public interface MethodsInterface {
    String formatTimeForUser(String time);

    String formatDateForUser(String string_date);

    Date stringToDate(String string_date);

    Date getDateToday();

    LocalTime stringToTime(String string_time);
}
