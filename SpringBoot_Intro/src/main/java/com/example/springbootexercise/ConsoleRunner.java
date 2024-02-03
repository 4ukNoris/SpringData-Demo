package com.example.springbootexercise;

import com.example.springbootexercise.queries.AllAuthorsOrderedByBookCount;
import com.example.springbootexercise.queries.AllAuthorsWithBooksBefore1990;
import com.example.springbootexercise.queries.AllBooksAfterTheYear2000;
import com.example.springbootexercise.queries.AllBooksFromAuthor;
import com.example.springbootexercise.repositories.AuthorRepository;
import com.example.springbootexercise.repositories.BookRepository;
import com.example.springbootexercise.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       // this.seedService.seedAuthors();
       // this.seedService.seedCategories();
       // this.seedService.seedBooks();
       // this.seedService.seedAll(); // <- Пълнене на всички таблици в базата едновременно

//        Queries:
//        AllBooksAfterTheYear2000.getAllBooksAfter2000(bookRepository); // 1-ва задача
//        AllAuthorsWithBooksBefore1990.getAllAuthors(authorRepository); // 2-ра задача
//        AllAuthorsOrderedByBookCount.getOrderedAuthors(authorRepository); // 3-та задача
        AllBooksFromAuthor.getAllBooks(authorRepository, bookRepository); // 4-та задача

//      За тестване на заявките трябва да се разкоментират една по една
    }
}
