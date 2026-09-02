package org.example;

import java.util.ArrayList;
import java.util.List;

public class Department extends Component {

    private List<Component> components = new ArrayList<>();

    public Department(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public double getSalary() {
        double totalSalary = 0;

        for (Component component : components) {
            totalSalary += component.getSalary();
        }

        return totalSalary;
    }

    @Override
    public void printXML(int indentation) {
        System.out.println(
                indent(indentation)
                        + "<department name=\""
                        + name
                        + "\">"
        );

        for (Component component : components) {
            component.printXML(indentation + 4);
        }

        System.out.println(
                indent(indentation) + "</department>"
        );
    }
}