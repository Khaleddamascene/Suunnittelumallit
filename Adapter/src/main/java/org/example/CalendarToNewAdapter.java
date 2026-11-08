package org.example;

import java.util.Calendar;

public class CalendarToNewAdapter implements NewDateInterface {
    private final Calendar calendar;

    public CalendarToNewAdapter() {
        calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1970, Calendar.JANUARY, 1);
    }

    @Override
    public void setDate(int day) {
        calendar.set(Calendar.DAY_OF_MONTH, day);
    }

    @Override
    public void setMonth(int month) {
        calendar.set(Calendar.MONTH, month - 1);
    }

    @Override
    public void setYear(int year) {
        calendar.set(Calendar.YEAR, year);
    }

    @Override
    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    @Override
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    @Override
    public void advanceDay(int days) {
        calendar.add(Calendar.DAY_OF_MONTH, days);
    }
}
