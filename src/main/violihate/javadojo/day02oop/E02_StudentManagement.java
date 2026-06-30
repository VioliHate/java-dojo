package main.violihate.javadojo.day02oop;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
========================================
JAVA DOJO - DAY 02
Exercise 02 - Student Management
Difficulty: ⭐⭐
========================================

DESCRIPTION

Create a Student class.

Each student has:

- id
- name
- age
- grades (List<Integer>)

IMPLEMENT

- addGrade()
- getAverageGrade()
- hasPassed()

A student passes if the average grade is at least 18.

REQUIREMENTS

- A grade must be between 0 and 30.
- Return 0 if the student has no grades.

EXTRA

- Find the highest grade.
*/

public class E02_StudentManagement {

    static class Student{
        private final String id;
        private final String name;
        private final int age;
        List<Integer> grades;

        Student(String name, int age) {
            this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            this.name = name;
            this.age = age;
            this.grades = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Student [id= " + id + ", name= " + name + ", age= " + age +
                    ", grades= " + grades + ", average= " + String.format("%.2f", getAverageGrade()) + "]";
        }

        public List<Integer> getGrades() {
            return new ArrayList<>(grades);
        }

        public boolean hasPassed() {
            return getAverageGrade() >= 18.0;
        }

        public void addGrade(int grade) {
            if(grade < 0 || grade > 30) {
                throw new IllegalArgumentException("A grade must be between 0 and 30");
            }
                this.grades.add(grade);
        }

        public double getAverageGrade() {
            if (grades.isEmpty()) {
                return 0.0;
            }
            return grades.stream()
                    .mapToDouble(Integer::intValue)
                    .average()
                    .orElse(0.0);
        }

        public int findHighestGrade(){
            if(!this.grades.isEmpty()){
                return this.grades.stream().max(Integer::compareTo).get();
            }
            return 0;
        }

    }
    public static void main(String[] args) {
        Student marioRossi = new Student("Mario Rossi", 19);
        Student andreaBianchi = new Student("Andrea Bianchi", 22);
        Student francoCipolla = new Student("Franco Cipolla", 23);

        System.out.println(marioRossi);
        System.out.println(andreaBianchi);
        System.out.println(francoCipolla);


        marioRossi.addGrade(18);
        andreaBianchi.addGrade(19);
        andreaBianchi.addGrade(18);
        andreaBianchi.addGrade(30);
        andreaBianchi.addGrade(19);
        marioRossi.addGrade(5);

        System.out.println(marioRossi);
        System.out.println(andreaBianchi);
        System.out.println(francoCipolla);

        System.out.println(andreaBianchi.findHighestGrade());
        System.out.println(andreaBianchi.hasPassed());

    }
}
