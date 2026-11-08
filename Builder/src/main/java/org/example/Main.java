package org.example;

public class Main {
    public static void main(String [] args) {
        ComputerBuilder gamingBuilder = new GamingComputerBuilder();
        ComputerDirector gamingDirector =
                new ComputerDirector(gamingBuilder);

        gamingDirector.constructComputer();
        Computer gamingComputer = gamingBuilder.getComputer();

        ComputerBuilder officeBuilder = new OfficeComputerBuilder();
        ComputerDirector officeDirector =
                new ComputerDirector(officeBuilder);

        officeDirector.constructComputer();
        Computer officeComputer = officeBuilder.getComputer();

        System.out.println("=== Gaming Computer ===");
        System.out.println(gamingComputer);
        System.out.println("\n=== Office Computer ===");
        System.out.println(officeComputer);

    }
}