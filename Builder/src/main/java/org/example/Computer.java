package org.example;

public class Computer {
    private String processor;
    private int ram;
    private String hardDrive;
    private String graphicsCard;
    private String operatingSystem;

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setRAM(int ram) {
        this.ram = ram;
    }

    public void setHardDrive(String graphicsCard){
        this.graphicsCard = graphicsCard;
    }

    public  void setOperatingSystem(String operatingSystem){
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String toString() {
        return "Processor: " + processor + "\n" +
               "RAM: " + ram + " GB\n" +
               "Hard Drive: " + hardDrive + "\n" +
               "Graphics Card: " + graphicsCard + "\n" +
               "Operating System: " + operatingSystem;
    }
}
