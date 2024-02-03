package com.example.springbootexercise.queries;

import com.example.springbootexercise.models.Author;
import com.example.springbootexercise.repositories.AuthorRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AllAuthorsWithBooksBefore1990 {
    private static final LocalDate RELEASE_DATE = LocalDate.of(1990, 1, 1);
    public static void getAllAuthors(AuthorRepository authorRepository) {
        List<Author> authors = authorRepository.findDistinctByBooksReleaseDateBefore(RELEASE_DATE);
        printAuthorsNames(authors);
    }
    private static void printAuthorsNames(List<Author> authors) {
        authors.forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
       // System.out.println(authors.size()); // Използва се за тест дали връща очаквания брой автори
    }
}
