package main.violihate.javadojo.day01collections;

import java.util.*;

/*
========================================
JAVA DOJO - DAY 01
Exercise 03 - Remove Duplicates
Difficulty: ⭐⭐
========================================

DESCRIPTION

Given a list of integers, remove all duplicate values while preserving
their original order.

INPUT

[4, 2, 6, 4, 8, 2, 1, 6]

EXPECTED OUTPUT

[4, 2, 6, 8, 1]

REQUIREMENTS

- Return a new List<Integer>.
- Preserve insertion order.

CONSTRAINTS

- Do not use nested loops.
- Do not modify the original list.

EXTRA

- Compare different implementations:
  - LinkedHashSet
  - Stream.distinct()
  - Discuss time complexity.
*/
public class E03_RemoveDuplicates {
 List<Integer> list = List.of(4, 2, 6, 4, 8, 2, 1, 6);

 private List<Integer> solveOne(List<Integer> list){
     Set<Integer> noDuplicates = new LinkedHashSet<>(list);
     return new ArrayList<>(noDuplicates);
 }

 private List<Integer> solveTwo(List<Integer> list){
     return list.stream().distinct().toList();
 }

 public static void main(String[] args) {
     E03_RemoveDuplicates removeDuplicates = new E03_RemoveDuplicates();
     System.out.println(removeDuplicates.solveOne(removeDuplicates.list));
     System.out.println(removeDuplicates.solveTwo(removeDuplicates.list));
 }

}
