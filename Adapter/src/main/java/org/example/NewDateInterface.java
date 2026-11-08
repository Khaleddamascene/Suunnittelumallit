package org.example;

public interface NewDateInterface {
    void setDate(int day);
    void setMonth(int month);
    void setYear(int year);

    int getDay();
    int getMonth();
    int getYear();

    void advanceDay(int days);

}
