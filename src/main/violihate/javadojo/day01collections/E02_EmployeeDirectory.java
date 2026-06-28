package main.violihate.javadojo.day01collections;

/*
========================================
JAVA DOJO - DAY 01
Exercise 02 - Employee Directory
Difficulty: ⭐⭐
========================================

DESCRIPTION

You have a list of employees.

class Employee {

    Long id;
    String name;
    String department;

}

Group all employees by department.

EXPECTED OUTPUT

IT
 - Mario
 - Luca

HR
 - Anna
 - Paolo

REQUIREMENTS

- Return a Map<String, List<Employee>>.
- Preserve all employees.
- Departments must be sorted alphabetically.

CONSTRAINTS

- Do not create one list for each department manually.
- Use Collections API only.

EXTRA

- Solve it using Stream Collectors.groupingBy().
- Print the department containing the highest number of employees.
*/

import java.util.*;
import java.util.stream.Collectors;

class Employee {

    private final Long id;
    private final String name;
    private final String department;

    public Employee(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return name;
    }
}

public class E02_EmployeeDirectory {



    List<Employee> employees = List.of(new Employee(1L, "Mario", "IT"),
            new Employee(2L, "Anna", "HR"), new Employee(3L, "Luca", "IT"),
            new Employee(4L, "Paolo", "HR"), new Employee(5L, "Andrea", "IT"),
            new Employee(6L, "Anna", "CTO"), new Employee(7L, "Luca", "PM"),
            new Employee(8L, "Luigi", "PM"), new Employee(9L, "Aziz", "IT"),
            new Employee(10L, "Francis", "CEO"), new Employee(11L, "Gloria", "CIO"));

    public Map<String, List<Employee>> solve(List<Employee> employees){
        Map<String, List<Employee>> map = new HashMap<>();

        for (Employee emp : employees) {
            map.putIfAbsent(emp.getDepartment(), new ArrayList<>());
            map.get(emp.getDepartment()).add(emp);
        }

        return new TreeMap<>(map); // TreeMap automatically sorts keys
    }

    public Map<String, List<Employee>> solveExtra(List<Employee> employees){
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        TreeMap::new,
                        Collectors.toList()
                ));
    }

    private static void print(Map<String, List<Employee>> directory) {
        for (Map.Entry<String, List<Employee>> entry : directory.entrySet()) {
            System.out.println(entry.getKey());
            for (Employee emp : entry.getValue()) {
                System.out.println(" - " + emp.getName());
            }
            System.out.println();
        }
    }

    private static void printMax(Map<String, List<Employee>> directory) {
        Optional<Map.Entry<String, List<Employee>>> max = directory.entrySet().stream()
                .max(Comparator.comparingInt(e -> e.getValue().size()));

        max.ifPresent(entry ->
                System.out.println("Department with the most employees: " + entry.getKey()
                        + " (" + entry.getValue().size() + " employees)")
        );
    }


    public static void main(String[] args) {
        E02_EmployeeDirectory employeeDirectory = new E02_EmployeeDirectory();
        E02_EmployeeDirectory.print(employeeDirectory.solve(employeeDirectory.employees)); //classic
        E02_EmployeeDirectory.printMax(employeeDirectory.solve(employeeDirectory.employees)); //classic
        E02_EmployeeDirectory.print(employeeDirectory.solveExtra(employeeDirectory.employees)); //extra
        E02_EmployeeDirectory.printMax(employeeDirectory.solveExtra(employeeDirectory.employees)); //extra


    }

}
