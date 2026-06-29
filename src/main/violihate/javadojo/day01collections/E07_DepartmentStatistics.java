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

        public Long getId() {
            return id;
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

        DepartmentStatistics(){
            this.departmentName = "";
            this.employeeCount = 0;
            this.avgSalary = 0.0;
            this.highestSalary = 0.0;
            this.lowestSalary = 0.0;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public int getEmployeeCount() {
            return employeeCount;
        }

        public void setEmployeeCount(int employeeCount) {
            this.employeeCount = employeeCount;
        }

        public double getAvgSalary() {
            return avgSalary;
        }

        public void setAvgSalary(double avgSalary) {
            this.avgSalary = avgSalary;
        }

        public double getHighestSalary() {
            return highestSalary;
        }

        public void setHighestSalary(double highestSalary) {
            this.highestSalary = highestSalary;
        }

        public double getLowestSalary() {
            return lowestSalary;
        }

        public void setLowestSalary(double lowestSalary) {
            this.lowestSalary = lowestSalary;
        }

        @Override
        public String toString() {
            return "Department: " + this.departmentName + "\n" +
                    "Employees: " + this.employeeCount + "\n" +
                    "Average Salary:" +  this.avgSalary + "\n" +
                    "Highest Salary:" +  this.highestSalary + "\n" +
                    "Lowest Salary:" +  this.lowestSalary;
        }

        public void calculateAverageSalary(List<Employee> employees, String departmentName) {
           this.avgSalary = employees.stream().filter(employee -> employee.department.equals(departmentName)).mapToDouble(Employee::getSalary).sum() / employees.size() ;
        }
        public void calculateHighestSalary(List<Employee> employees, String departmentName) {
            this.highestSalary = employees.stream().filter(employee -> employee.department.equals(departmentName)).max(Comparator.comparingDouble(Employee::getSalary)).get().getSalary();
        }
        public void calculateLowestSalary(List<Employee> employees, String departmentName) {
            this.lowestSalary = employees.stream().filter(employee -> employee.department.equals(departmentName)).min(Comparator.comparingDouble(Employee::getSalary)).get().getSalary();

        }
        public void countEmployees(List<Employee> employees, String departmentName) {
            this.employeeCount = Math.toIntExact(employees.stream().filter(employee -> employee.department.equals(departmentName)).count());
        }

        public void calculateStatistics(List<Employee> employees, String departmentName) {
            this.departmentName = departmentName;
            this.countEmployees(employees, departmentName);
            this.calculateAverageSalary(employees, departmentName);
            this.calculateHighestSalary(employees, departmentName);
            this.calculateLowestSalary(employees, departmentName);
            System.out.println(this);
        }
        Set<String> getDepartmentFromListEmployees(List<Employee> employees) {
            return employees.stream().map(Employee::getDepartment).collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) {
        DepartmentStatistics departmentStatistics = new DepartmentStatistics();
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
        System.out.println("=========DEPARTMENT STATS===============");
        for(String departmentName : departmentStatistics.getDepartmentFromListEmployees(employees)) {
            departmentStatistics.calculateStatistics(employees, departmentName);
            System.out.println("========================");
        }
    }

}
