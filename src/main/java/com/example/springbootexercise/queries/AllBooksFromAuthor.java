package com.example.springbootexercise.queries;

import com.example.springbootexercise.models.Author;
import com.example.springbootexercise.models.Book;
import com.example.springbootexercise.repositories.AuthorRepository;
import com.example.springbootexercise.repositories.BookRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AllBooksFromAuthor {
    private static final String AUTHOR_FULL_NAME = "George Powell";

    public static void getAllBooks(AuthorRepository authorRepository, BookRepository bookRepository) {
        int authorId = getAuthorId(authorRepository, AUTHOR_FULL_NAME);
        List<Book> books = bookRepository.findByAuthorId(authorId);
        List<Book> sortedBooks = getSortedBooks(books);
        printBooksData(sortedBooks);
    }

    private static void printBooksData(List<Book> books) {
        books.forEach(book ->
                System.out.printf("%s %s -> %d\n",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()));
    }

    private static List<Book> getSortedBooks(List<Book> books) {
        return books
                .stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Book::getReleaseDate)
                        .thenComparing(Book::getTitle)))
                .collect(Collectors.toList());
    }

    private static int getAuthorId(AuthorRepository authorRepository, String fullName) {
        return authorRepository.findAll()
                .stream()
                .filter(a -> (a.getFirstName() + " " + a.getLastName()).equals(fullName))
                .mapToInt(a -> (int) a.getId())
                .findFirst().orElse(0);
    }


}
