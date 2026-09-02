package org.example;


public class Employee extends Component {

    private double salary;

    public Employee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void printXML(int indentation) {
        System.out.println(
                indent(indentation)
                        + "<employee name=\""
                        + name
                        + "\" salary=\""
                        + salary
                        + "\"/>"
        );
    }
}