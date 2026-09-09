package org.example;

import java.util.Random;

public class WeatherStation extends Observable implements Runnable {

    private double temperature;

    private final double MIN_TEMPERATUER = -20.0;
    private final double MAX_TEMPERATUER = 40.0;

    private Random random = new Random();

    public WeatherStation() {
        temperature = MAX_TEMPERATUER
                + random.nextDouble() * (MAX_TEMPERATUER - MIN_TEMPERATUER);
    }

    public  double getTemperature() {
        return  temperature;
    }

    @Override
    public void run() {

        while (true) {

            try {
                int waitTime = 1000 + random.nextInt(4001);

                Thread.sleep(waitTime);

                if (random.nextBoolean()) {
                    temperature++;
                } else {
                    temperature--;
                }

                if (temperature > MAX_TEMPERATUER) {
                    temperature = MAX_TEMPERATUER;
                }

                if (temperature < MIN_TEMPERATUER) {
                    temperature = MIN_TEMPERATUER;
                }

                System.out.println("\nWeather station: temperature changed to "
                + String.format("%.1f", temperature) + "°C"
                );

                notifyObservers();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
