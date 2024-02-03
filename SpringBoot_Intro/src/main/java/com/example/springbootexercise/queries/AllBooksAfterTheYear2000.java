package com.example.springbootexercise.queries;

import com.example.springbootexercise.models.Book;
import com.example.springbootexercise.repositories.BookRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AllBooksAfterTheYear2000 {
    private static final LocalDate RELEASE_DATE = LocalDate.of(2000, 12, 31);
    public static void getAllBooksAfter2000(BookRepository bookRepository) {
        List<Book> books = bookRepository.findByReleaseDateAfter(RELEASE_DATE);

        printBookTitles(books);
    }

    private static void printBookTitles(List<Book> books) {
        books.forEach(book -> System.out.println(book.getTitle()));
        System.out.println(books.size());
    }
}
