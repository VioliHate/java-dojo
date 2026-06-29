package main.violihate.javadojo.day01collections;
/*
========================================
JAVA DOJO - DAY 01
Exercise 07 - Department Statistics
Difficulty: ⭐⭐⭐⭐
========================================

DESCRIPTION

Given a list of employees, compute statistics for each department.

Employee
- id
- name
- department
- salary

CALCULATE:
- Number of employees
- Average salary
- Highest salary
- Lowest salary

EXPECTED OUTPUT
Department: IT
Employees: 12
Average Salary: 36500
Highest Salary: 48000
Lowest Salary: 27000

REQUIREMENTS
Return the result using a dedicated class.

DepartmentStatistics:
- departmentName
- employeeCount
- averageSalary
- highestSalary
- lowestSalary

CONSTRAINTS
- The solution must work for any number of departments.
- Avoid unnecessary iterations.

EXTRA

- Sort departments by average salary descending.
- Print the employee with the highest salary for each department.
*/

import java.util.*;
import java.util.stream.Collectors;

public class E07_DepartmentStatistics {

    static class Employee {

    private Long id;
    private String name;
    private String department;
    private Double salary;

    Employee(Long id, String name, String department, Double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public Double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Employee [id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary + "]";
        }
    }

    static class DepartmentStatistics {
        private String departmentName;
        private int employeeCount;
        private double avgSalary;
        private double highestSalary;
        private double lowestSalary;
        private final Employee highestPaidEmployee;   // EXTRA

        DepartmentStatistics(String departmentName, List<Employee> deptEmployees){
            this.departmentName = departmentName;
            this.employeeCount = deptEmployees.size();

            this.avgSalary = deptEmployees.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0.0);

            this.highestSalary = deptEmployees.stream()
                    .mapToDouble(Employee::getSalary)
                    .max()
                    .orElse(0.0);

            this.lowestSalary = deptEmployees.stream()
                    .mapToDouble(Employee::getSalary)
                    .min()
                    .orElse(0.0);

            this.highestPaidEmployee = deptEmployees.stream()
                    .max(Comparator.comparingDouble(Employee::getSalary))
                    .orElse(null);
        }

        public double getAvgSalary() {
            return avgSalary;
        }

        @Override
        public String toString() {
            return "Department: " + departmentName + "\n" +
                    "Employees: " + employeeCount + "\n" +
                    "Average Salary: " + String.format("%.2f", avgSalary) + "\n" +
                    "Highest Salary: " + String.format("%.2f", highestSalary) +
                    " (" + (highestPaidEmployee != null ? highestPaidEmployee.getName() : "N/A") + ")\n" +
                    "Lowest Salary: " + String.format("%.2f", lowestSalary);
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(List.of(
         new Employee(1L, "Alessandro Rossi", "IT", 3200.00),
         new Employee(2L, "Giulia Bianchi", "HR", 2800.00),
         new Employee(3L, "Luca Verdi", "Marketing", 2500.50),
         new Employee(4L, "Elena Russo", "Finance", 3500.00),
         new Employee(5L, "Marco Ferrari", "Sales", 2700.00),
         new Employee(6L, "Sofia Esposito", "IT", 4100.00),
         new Employee(7L, "Francesco Romano", "IT", 3000.00),
         new Employee(8L, "Chiara Colombo", "HR", 2900.00),
         new Employee(9L, "Davide Ricci", "Marketing", 2600.00),
         new Employee(10L, "Martina Marino", "Finance", 3800.50),
         new Employee(11L, "Lorenzo Greco", "Sales", 2400.00),
         new Employee(12L, "Giorgia Bruno", "IT", 3350.00),
         new Employee(13L, "Mattia Gallo", "IT", 2950.00),
         new Employee(14L, "Sara Conti", "Marketing", 3100.00),
         new Employee(15L, "Andrea De Luca", "Finance", 4200.00),
         new Employee(16L, "Anna Costa", "Sales", 2850.00),
         new Employee(17L, "Gabriele Giordano", "HR", 2750.00),
         new Employee(18L, "Francesca Mancini", "IT", 3600.00),
         new Employee(19L, "Tommaso Rizzo", "Sales", 2300.00),
         new Employee(20L, "Alice Lombardi", "Marketing", 2900.00),
         new Employee(21L, "Roberto Barbieri", "Finance", 3900.00),
         new Employee(22L, "Beatrice Fontana", "IT", 3150.00),
         new Employee(23L, "Riccardo Santoro", "HR", 2650.00),
         new Employee(24L, "Silvia Mariani", "Sales", 3050.00),
         new Employee(25L, "Federico Serra", "IT", 4500.00)));

        Map<String, List<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        List<DepartmentStatistics> statsList = grouped.entrySet().stream()
                .map(entry -> new DepartmentStatistics(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingDouble(DepartmentStatistics::getAvgSalary).reversed())
                .toList();

        System.out.println("=========DEPARTMENT STATS===============\n");

        for (DepartmentStatistics stats : statsList) {
            System.out.println(stats);
            System.out.println("========================");
        }
    }

}
