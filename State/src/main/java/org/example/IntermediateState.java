package org.example;

import java.util.Scanner;

public class IntermediateState extends CharacterState{

    private static final int EXPERIENCE_TO_EXPERT = 250;

    private static final int TRAIN_EXPERIENCE = 25;
    private static final int MEDITATE_HEALTH = 15;

    private Scanner scanner = new Scanner(System.in);

    public IntermediateState(Character character) {
        super(character);
    }



    @Override
    public void action() {

        System.out.println("\nAvailable actions:");
        System.out.println("1. Train");
        System.out.println("2. Meditate");

        int choice = scanner.nextInt();

        switch (choice) {

            case 1:
                train();
                break;
            case 2:
                meditate();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    @Override
    public String getlevelName() {
        return "Intermediate";
    }

    private void train() {
        System.out.println("Training....");

        getCharacter().addExperience(TRAIN_EXPERIENCE);

        System.out.println(
                "You gained " + TRAIN_EXPERIENCE + " experience points."
        );

        checkLevelUp();
    }

    private void meditate() {

        System.out.println("Metitating.....");

        getCharacter().addHealth(MEDITATE_HEALTH);

        System.out.println(
                "You gained " + MEDITATE_HEALTH + " health points."
        );
    }

    private void checkLevelUp() {
        if (getCharacter().getExperiencePoints() >= EXPERIENCE_TO_EXPERT) {

            System.out.println("You reached Expert level!");

            getCharacter().setState(
                    new ExpertState(getCharacter())
            );
        }
    }
}
