package org.example;

public class Character {
    private String name;
    private int experiencePoints;
    private int healthPoints;

    private CharacterState state;

    Character(String name) {
        this.name = name;
        this.experiencePoints = 0;
        this.healthPoints = 100;

        this.state = new NoviceState(this);
    }

    public void setState(CharacterState state) {
        this.state = state;
    }

    public CharacterState getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void addExperience(int amount) {
        experiencePoints += amount;
    }

    public void addHealth(int amount) {
        healthPoints += amount;
    }

    public void removeHealth(int amount) {
        healthPoints -= amount;

        if (healthPoints < 0 ) {
            healthPoints = 0;
        }
    }


    public void operate() {
        while (!(state instanceof MasterState)) {
            displayStatus();
            state.action();
        }

        displayStatus();
        System.out.println("Congratulations! You have reached the Master level!");
    }

    public void displayStatus() {
        System.out.println("\n===========================");
        System.out.println("Name: " + name);
        System.out.println("Level: " + state.getlevelName());
        System.out.println("Experience: " + experiencePoints);
        System.out.println("Health: " + healthPoints);
        System.out.println("============================");


    }

}
