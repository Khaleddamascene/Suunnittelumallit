package org.example;

import java.util.Scanner;

public class ExpertState extends CharacterState {

    private static final int EXPERIENCE_TO_MASTER = 500;

    private static final int TRAIN_EXPERIENCE = 30;
    private static final  int MEDITATE_HEALTH = 20;

    private static final int FIGHT_EXPERIENCE = 50;
    private static final int FIGHT_DAMAGE = 25;

    private Scanner scanner = new Scanner(System.in);

    public ExpertState(Character character) {
        super(character);
    }


    @Override
    public void action() {

        System.out.println("\nAvailable actions:");
        System.out.println("1. Train");
        System.out.println("2. Meditale");
        System.out.println("3. Fight");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                train();
                break;

            case 2:
                meditate();
                break;

            case 3:
                fight();
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    @Override
    public String getlevelName() {
        return "Expert";
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
    }

    private void fight() {
    }

    private void checkLevelUp() {
        if (getCharacter().getExperiencePoints() >= EXPERIENCE_TO_MASTER) {
            System.out.println("You reached Master levels!");

            getCharacter().setState(
                    new MasterState(getCharacter())
            );
        }
    }
}
