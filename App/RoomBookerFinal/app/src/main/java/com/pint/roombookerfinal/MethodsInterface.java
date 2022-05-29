package com.pint.roombookerfinal;

import java.util.Date;

public interface MethodsInterface {
    public String formatTimeForUser(String time);

    public String formatDateForUser(String string_date);

    public Date stringToDate(String string_date);

    public Date getDateToday();
}
