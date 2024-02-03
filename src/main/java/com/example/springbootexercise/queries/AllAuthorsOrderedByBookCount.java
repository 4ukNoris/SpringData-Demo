package com.example.springbootexercise.queries;

import com.example.springbootexercise.models.Author;
import com.example.springbootexercise.repositories.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllAuthorsOrderedByBookCount {

    public static void getOrderedAuthors(AuthorRepository authorRepository) {
        List<Author> authors = authorRepository.findAll();
        List<Author> sortedAuthors = authors.stream()
                .sorted((left, right) -> right.getBooks().size() - left.getBooks().size()) // Сортирам в намаляващ ред!
                .toList();
        printAuthorsNames(sortedAuthors);

    }

    private static void printAuthorsNames(List<Author> authors) {
        authors.forEach(author ->
                System.out.printf("%s %s -> %d\n",
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks().size()));
    }
}
