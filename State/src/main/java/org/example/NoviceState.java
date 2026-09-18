package org.example;

import java.util.Scanner;

public class NoviceState extends CharacterState {

    private static final int EXPERIENCE_TO_INTERMDIATE = 100;
    private static final int TRAIN_EXPERIENCE = 20;

    private Scanner scanner = new Scanner(System.in);

    public NoviceState(Character character) {
        super(character);
    }


    @Override
    public void action() {
        System.out.println("\nAvailable actions:");
        System.out.println("1. Train");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                train();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    @Override
    public String getlevelName() {
        return "Novice";
    }

    private void train() {

        System.out.println("Training.....");

        getCharacter().addExperience(TRAIN_EXPERIENCE);

        System.out.println(
                "You gained " + TRAIN_EXPERIENCE + " experience points."
        );

        if (getCharacter().getExperiencePoints() >= EXPERIENCE_TO_INTERMDIATE) {

            System.out.println("You reached Intermediate level!");

            getCharacter().setState(
                    new IntermediateState(getCharacter())
            );
        }
    }
}