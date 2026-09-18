package org.example;

public abstract class CharacterState {

    private Character character;

    public CharacterState(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public abstract void action();

    public abstract String getlevelName();

}
