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

import java.util.List;

class Employee {

    Long id;
    String name;
    String department;

   public Employee(Long id, String name, String department) {
       this.id = id;
       this.name = name;
       this.department = department;
    }
}

public class E02_EmployeeDirectory {

    List<Employee> employees = List.of(new Employee(1L, "Mario", "IT"),
            new Employee(2L, "Anna", "HR"), new Employee(3L,"Luca", "IT"),
            new Employee(4L, "Paolo", "HR"), new Employee(5L,"Andrea", "IT"),
            new Employee(6L, "Anna", "CTO"), new Employee(7L,"Luca", "PM"),
            new Employee(8L, "Luigi", "PM"), new Employee(9L,"Aziz", "IT"),
            new Employee(10L, "Francis", "CEO"), new Employee(11L,"Gloria", "CIO"));

    public void solve(){

    }

    public void solveExtra(){

    }


    public static void main(String[] args) {
        E02_EmployeeDirectory EmployeeDirectory = new E02_EmployeeDirectory();
        EmployeeDirectory.solve();
    }
}
