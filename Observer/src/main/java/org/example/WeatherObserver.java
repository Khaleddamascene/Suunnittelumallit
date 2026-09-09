package org.example;

public class WeatherObserver implements Observer{

    private String name;
    private WeatherStation weatherStation;

    public WeatherObserver(String name, WeatherStation weatherStation) {
        this.name = name;
        this.weatherStation = weatherStation;

        weatherStation.addObserver(this);
    }

    @Override
    public void update() {
        double temperature = weatherStation.getTemperature();

        System.out.println(name + ": I noticed the weather changed!" +
                " Current temperature is " +
                String.format("%.1f", temperature) + "°C."
        );
    }
}
