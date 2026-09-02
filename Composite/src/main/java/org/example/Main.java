package org.example;

public class Main {

    public static void main(String[] args) {


        Department company = new Department("Tech Company");


        Department development = new Department("Development");
        Department hr = new Department("Human Resources");

        Department backend = new Department("Backend");
        Department frontend = new Department("Frontend");


        Employee alice = new Employee("Alice", 4000);
        Employee bob = new Employee("Bob", 4500);
        Employee charlie = new Employee("Charlie", 3500);
        Employee david = new Employee("David", 3000);
        Employee eve = new Employee("Eve", 3200);


        backend.add(alice);
        backend.add(bob);

        frontend.add(charlie);

        development.add(backend);
        development.add(frontend);

        hr.add(david);
        hr.add(eve);

        company.add(development);
        company.add(hr);


        System.out.println("Total salary: " + company.getSalary());

        System.out.println();


        System.out.println("Organization structure:");
        company.printXML(0);


        System.out.println();
        System.out.println("Removing Bob...");

        backend.remove(bob);

        System.out.println("New total salary: " + company.getSalary());

        System.out.println();
        System.out.println("Updated organization structure:");
        company.printXML(0);
    }
}