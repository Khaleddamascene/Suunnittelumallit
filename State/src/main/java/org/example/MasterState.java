package org.example;

public class MasterState extends CharacterState{

    public MasterState(Character character) {
        super(character);
    }


    @Override
    public void action() {
        System.out.println("You are already a Master.");
    }

    @Override
    public String getlevelName() {
        return "Master";
    }


}
