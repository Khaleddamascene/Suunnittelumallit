package org.example;

public class OfficeComputerBuilder implements ComputerBuilder{
    private Computer computer;

    public OfficeComputerBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.computer = new Computer();
    }

    @Override
    public void buildProcessor() {
        computer.setProcessor("Intel Core i5-14400");
    }

    @Override
    public void buildRAM() {
        computer.setRAM(16);
    }

    @Override
    public void buildHardDrive() {
        computer.setHardDrive("256GB SSD");
    }

    @Override
    public void buildGraphicsCard() {
        computer.setGraphicsCard("Intel UHD Graphics 730");
    }

    @Override
    public void buildOperatingSystem() {
        computer.setOperatingSystem("Windows 10 Pro");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
