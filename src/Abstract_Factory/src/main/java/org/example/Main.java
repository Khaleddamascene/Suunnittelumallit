package org.example;

public class Main {

    public static void main(String[] args) {

        // Choose the look-and-feel
        UIFactory factory = new AFactory();

        Button button = factory.createButton("OK");
        TextField textField = factory.createTextField("Hello");
        Checkbox checkbox = factory.createCheckbox("Remember me");

        System.out.println("Style A:");
        button.display();
        textField.display();
        checkbox.display();

        // Change the text dynamically
        System.out.println("\nAfter changing the text:");

        button.setText("Cancel");
        textField.setText("New text");
        checkbox.setText("Accept");

        button.display();
        textField.display();
        checkbox.display();

        // Switch to another look-and-feel
        System.out.println("\nStyle B:");

        factory = new BFactory();

        Button buttonB = factory.createButton("OK");
        TextField textFieldB = factory.createTextField("Hello");
        Checkbox checkboxB = factory.createCheckbox("Remember me");

        buttonB.display();
        textFieldB.display();
        checkboxB.display();
    }
}
