package org.example;

public interface ComputerBuilder {
    void reset();
    void buildProcessor();
    void buildRAM();
    void buildHardDrive();
    void buildGraphicsCard();
    void buildOperatingSystem();

    Computer getComputer();

}
