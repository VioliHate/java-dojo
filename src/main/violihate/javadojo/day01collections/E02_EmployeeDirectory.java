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

class Employee {

    Long id;
    String name;
    String department;

}

public class E02_EmployeeDirectory {

    public static void main(String[] args) {}
}
