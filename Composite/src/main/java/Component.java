package org.example;

public abstract class Component {

    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getSalary();

    public abstract void printXML(int indentation);

    public void add(Component component) {
        throw new UnsupportedOperationException(
                "This component cannot contain other components."
        );
    }

    public void remove(Component component) {
        throw new UnsupportedOperationException(
                "This component cannot contain other components."
        );
    }

    protected String indent(int indentation) {
        return " ".repeat(indentation);
    }
}