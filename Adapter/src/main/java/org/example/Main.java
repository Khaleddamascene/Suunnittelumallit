package org.example;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args  ) {
        NewDateInterface date = new CalendarToNewAdapter();

        date.setYear(2024);
        date.setMonth(2);
        date.setDate(28);

        System.out.printf("Original date: %02d.%02d.%04d\n",
                date.getDay(), date.getMonth(), date.getYear());

        date.advanceDay(3);

        System.out.printf("After advancing 3 days: %02d.%02d.%04d\n",
                date.getDay(), date.getMonth(), date.getYear());

    }
}
