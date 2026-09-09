/*
This class manages the list of observers.
 observers to:

-register themselves
-remove themselves
-receive notifications
 */
package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
