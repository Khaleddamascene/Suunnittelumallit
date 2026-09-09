package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {

        Logger logger = Logger.getInstance();

        logger.setFileName("my_log.txt");
        logger.write("Simulation started.");
        logger.write("Processing data....");
        logger.write("Simulation finished.");

        logger.close();
    }
}
