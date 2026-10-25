package org.example;

import java.time.LocalDateTime;


public class Memento implements IMemento {
    private int[] options;
    private boolean isSelected;
    private final LocalDateTime savedAt;
    private final String description;


    public Memento(int[] options, boolean isSelected) {
        this.options = options.clone();
        this.isSelected = isSelected;
        this.savedAt = LocalDateTime.now();
        String[] colors = {"Red", "Blue", "Yellow"};
        this.description = colors[options[0]] + ", " + colors[options[1]]
                + ", " + colors[options[2]]
                + "; checkbox " + (isSelected ? "checked" : "unchecked");
    }

    public int[] getOptions() {
        return options.clone();
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
