package main.violihate.javadojo.day01collections;

import java.util.*;
import java.util.stream.Collectors;

/*
========================================
JAVA DOJO - DAY 01
Exercise 05 - Library Catalog
Difficulty: ⭐⭐⭐
========================================

DESCRIPTION

Implement a simple library manager.

Book

isbn
title
author
year

IMPLEMENT

addBook()

removeBook()

findByTitle()

findByAuthor()

findOldestBook()

findNewestBook()

REQUIREMENTS

- Store books using Collections.
- Searching must be case insensitive.

CONSTRAINTS

- Do not return null.
- Return Optional<Book> whenever appropriate.

EXTRA

- Prevent duplicate ISBNs.
- Return all books ordered by publication year.
*/
public class E05_LibraryCatalog {

    static class Book {
       private final Long isbn;
       private final String title;
       private final String author;
       private final Integer year;

        Book(Long isbn, String title, String author, Integer year) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.year = year;
        }

        public Long getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public Integer getYear() {
            return year;
        }

        public String toString(){
            return  " isbn: "+ isbn + " title: " + title + " author: " + author + " year: " + year;
        }
    }

    static class LibraryManager {
        private List<Book> books;

        LibraryManager(List<Book> books) {
            this.books = new ArrayList<>(books);
        }

        public void addBook(Book book) {
            if (book == null) return;
            if (this.books.stream().noneMatch(b -> Objects.equals(b.getIsbn(), book.getIsbn())))
                this.books.add(book);
        }

        public boolean removeBook(Long isbn) {
            return books.removeIf(b -> Objects.equals(b.getIsbn(), isbn));
        }

        Optional<Book> findByTitle(String title) {
            return this.books.stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).findFirst();
        }

        List<Book> findByAuthor(String author) {
            if (author == null) return List.of();
            return this.books.stream().filter(b -> b.getAuthor().equalsIgnoreCase(author))
                    .collect(Collectors.toList());
        }

        Optional<Book> findOldestBook(){
            return this.books.stream().min(Comparator.comparingInt(Book::getYear));
        }

        Optional<Book> findNewestBook(){
            return this.books.stream().max(Comparator.comparingInt(Book::getYear));
        }

        List<Book> allBooks() {
            return this.books.stream().sorted(Comparator.comparingInt(Book::getYear)).collect(Collectors.toList());
        }

    }

    public static void main(String[] args){
        List<Book> books = List.of(new Book(9788804668237L, "1984", "George Orwell", 1949),
                new Book(9788806228019L, "Il Signore degli Anelli", "J.R.R. Tolkien", 1954),
                new Book(9788807900389L, "Il Piccolo Principe", "Antoine de Saint-Exupéry", 1943),
                new Book(9788806216597L, "Il vecchio e il mare", "Ernest Hemingway", 1952),
                new Book(9788817122047L, "Il grande Gatsby", "F. Scott Fitzgerald", 1925),
                new Book(9788807901379L, "Delitto e castigo", "Fëdor Dostoevskij", 1866),
                new Book(9788804616993L, "Cronache marziane", "Ray Bradbury", 1950),
                new Book(9788817006279L, "Cent'anni di solitudine", "Gabriel García Márquez", 1967),
                new Book(9788845100314L, "Frankenstein", "Mary Shelley", 1818),
                new Book(9788806243838L, "Se questo è un uomo", "Primo Levi", 1947),
                new Book(9788845292491L, "Lo Hobbit", "J.R.R. Tolkien", 1937),
                new Book(9788804680802L, "La fattoria degli animali", "George Orwell", 1945));
        LibraryManager libraryManager = new LibraryManager(books);

        libraryManager.findNewestBook().ifPresent( book -> System.out.println("newest book ->"+ book));
        libraryManager.findOldestBook().ifPresent(book -> System.out.println("oldest book ->"+ book));

        libraryManager.addBook(new Book(9788804660613L, "Il fu Mattia Pascal", "Luigi Pirandello", 1904));
        libraryManager.removeBook(9788845292491L);
        System.out.println("=======FIND BY AUTHOR: George Orwell =======");
        libraryManager.findByAuthor("George Orwell").forEach(book ->  System.out.println("find book ->"+ book));
        System.out.println("=======FIND BY TITLE: Frankenstein =======");
        libraryManager.findByTitle("Frankenstein").ifPresent(System.out::println);

        System.out.println("=======ALL BOOKS ORDER BY YEAR ASC=======");
        libraryManager.allBooks().forEach(System.out::println);
    }
}
