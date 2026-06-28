package main.violihate.javadojo.day01collections;
/*
========================================
JAVA DOJO - DAY 01
Exercise 04 - Salary Statistics
Difficulty: ⭐⭐⭐
========================================

DESCRIPTION

Given a list of employees, calculate salary statistics.

class Employee {

    Long id;
    String name;
    double salary;

}

IMPLEMENT

- Highest salary
- Lowest salary
- Average salary

REQUIREMENTS

Return all statistics inside a dedicated class.

Example:

SalaryStatistics

maxSalary

minSalary

averageSalary

CONSTRAINTS

- Handle empty collections safely.
- Avoid duplicated loops whenever possible.

EXTRA

- Solve using Stream API.
- Return Optional<Employee> for max and min employee.
*/

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class E04_SalaryStatistics {

    static class Employee {

        private final Long id;
        private final String name;
        private final double salary;

        public Employee(Long id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

    }

    static class SalaryStatistics {

        private final Optional<Employee> maxEmployee;
        private final Optional<Employee> minEmployee;
        private final double maxSalary;
        private final double minSalary;
        private final double averageSalary;
        private final int count;

        public SalaryStatistics(List<Employee> employees) {
            if (employees == null || employees.isEmpty()) {
                this.maxEmployee = Optional.empty();
                this.minEmployee = Optional.empty();
                this.maxSalary = 0.0;
                this.minSalary = 0.0;
                this.averageSalary = 0.0;
                this.count = 0;
                return;
            }
            this.maxEmployee = employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
            this.minEmployee = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));

            this.maxSalary = maxEmployee.map(Employee::getSalary).orElse(0.0);
            this.minSalary = minEmployee.map(Employee::getSalary).orElse(0.0);

            this.averageSalary = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0.0);

            this.count = employees.size();
        }

        public Optional<Employee> maxSalary(List<Employee> employees) {
            return !employees.isEmpty() ? employees.stream().max(Comparator.comparingDouble(Employee::getSalary)) : Optional.empty();
        }

        public Optional<Employee> minSalary(List<Employee> employees) {
            return !employees.isEmpty() ? employees.stream().min(Comparator.comparingDouble(Employee::getSalary)) : Optional.empty();
        }

        public double averageSalary(List<Employee> employees) {
            return employees.isEmpty() ? 0.0 : employees.stream().mapToDouble(Employee::getSalary).sum() / employees.size();
        }

        public void print(List<Employee> employees) {
            maxSalary(employees).ifPresent(e ->
                    System.out.println("Max salary: " + e.getName() + " -> " + e.getSalary()));
            minSalary(employees).ifPresent(e ->
                    System.out.println("Min salary: " + e.getName() + " -> " + e.getSalary()));
            System.out.println("Average Salary: " + String.format("%.2f", averageSalary(employees)));
        }

    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(new Employee(1L, "Mario", 32000.00),
                new Employee(2L, "Anna", 24500.00), new Employee(3L, "Luca", 29999.99),
                new Employee(4L, "Paolo", 24500.50), new Employee(5L, "Andrea", 21000.00),
                new Employee(6L, "Anna", 45000.00), new Employee(7L, "Luca", 33500.50),
                new Employee(8L, "Luigi", 35000.99), new Employee(9L, "Aziz", 18999.99),
                new Employee(10L, "Francis", 75000.00), new Employee(11L, "Gloria", 49000.99));

        SalaryStatistics stats = new SalaryStatistics(employees);
        stats.print(employees);
    }
}
