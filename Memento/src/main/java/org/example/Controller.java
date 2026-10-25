package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {
    private final Model model;
    private final Gui gui;
    private final Deque<IMemento> undoHistory = new ArrayDeque<>();
    private final Deque<IMemento> redoHistory = new ArrayDeque<>();
    private final ObservableList<IMemento> savedStates =
            FXCollections.observableArrayList();
    private final ObservableList<IMemento> readOnlyStates =
            FXCollections.unmodifiableObservableList(savedStates);
    private IMemento currentState;

    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        currentState = model.createMemento();
        savedStates.add(currentState); // Include the initial state.
    }

    public ObservableList<IMemento> getSavedStates() {
        return readOnlyStates;
    }

    public void setOption(int optionNumber, int choice) {
        if (optionNumber < 1 || optionNumber > 3 || choice < 0 || choice > 2) {
            throw new IllegalArgumentException("Invalid box or color");
        }
        if (model.getOption(optionNumber) == choice) {
            return;
        }
        saveBeforeChange();
        model.setOption(optionNumber, choice);
        rememberNewState();
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        if (model.getIsSelected() == isSelected) {
            return;
        }
        saveBeforeChange();
        model.setIsSelected(isSelected);
        rememberNewState();
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    private void saveBeforeChange() {
        undoHistory.push(currentState);
        redoHistory.clear();
    }

    private void rememberNewState() {
        currentState = model.createMemento();
        savedStates.add(currentState);
    }

    public void undo() {
        if (!undoHistory.isEmpty()) {
            redoHistory.push(currentState);
            restore(undoHistory.pop());
        }
    }

    public void redo() {
        if (!redoHistory.isEmpty()) {
            undoHistory.push(currentState);
            restore(redoHistory.pop());
        }
    }

    // A history jump is an undoable action and starts a new redo branch.
    public void restoreFromHistory(IMemento state) {
        if (state == null || state == currentState || !savedStates.contains(state)) {
            return;
        }
        saveBeforeChange();
        restore(state);
    }

    private void restore(IMemento state) {
        model.restoreState(state);
        currentState = state;
        gui.updateGui();
    }
}

