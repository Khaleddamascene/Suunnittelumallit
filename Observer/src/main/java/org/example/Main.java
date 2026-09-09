package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {

    public static void main(String[] args) {

        WeatherStation weatherStation = new WeatherStation();

        WeatherObserver observer1 =
                new WeatherObserver("Observer 1", weatherStation);

        WeatherObserver observer2 =
                new WeatherObserver("Observer 2", weatherStation);

        WeatherObserver observer3 =
                new WeatherObserver("Observer 3", weatherStation);

        Thread weatherThread = new Thread(weatherStation);

        System.out.println(
                "Weather station started. Initial temperature: "
                        + String.format("%.1f", weatherStation.getTemperature())
                        + " °C"
        );

        weatherThread.start();

        try {

            Thread.sleep(15000);

            System.out.println(
                    "\nRemoving Observer 2...\n"
            );

            weatherStation.removeObserver(observer2);


            Thread.sleep(15000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}