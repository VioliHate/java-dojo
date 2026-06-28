package main.violihate.javadojo.day01collections;



/*
========================================
JAVA DOJO - DAY 01
Exercise 01 - Word Counter
Difficulty: ⭐
========================================

DESCRIPTION

Given a list of words, count how many times each word appears.

INPUT

["java", "spring", "java", "linux", "java", "sql", "linux"]

EXPECTED OUTPUT

java -> 3
linux -> 2
spring -> 1
sql -> 1

REQUIREMENTS

- Use a HashMap<String, Integer>.
- The solution must work with any input size.
- Print the result sorted alphabetically.

CONSTRAINTS

- Do not use external libraries.
- Do not hardcode the output.

EXTRA

- Solve the same exercise using Stream API.
- Print the most frequent word.
*/

import java.util.*;
import java.util.stream.Collectors;

public class WordCounter {
    final String[] input = {"java", "spring", "java", "linux", "java", "sql", "linux"};

    public void solve(){
        Map<String, Integer> result = new HashMap<>();
        for (String word : input) {
            result.put(word, result.getOrDefault(word, 0) + 1);
        }
        List<String> sortedWords = new ArrayList<>(result.keySet());
        Collections.sort(sortedWords);
        for (String word : sortedWords) {
            System.out.println(word + " -> " + result.get(word));
        }

    }
    public void solveExtra(){
        Map<String, Long> result = Arrays.stream(input).toList().stream()
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));

        result.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        System.out.println(entry.getKey() + " -> " + entry.getValue())
                );

        Optional<Map.Entry<String, Long>> mostFrequent = result.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        mostFrequent.ifPresent(entry -> System.out.println("most frequent word: " + entry.getKey() + " -> " + entry.getValue()));
    }

    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();
        wordCounter.solveExtra();
    }
}
