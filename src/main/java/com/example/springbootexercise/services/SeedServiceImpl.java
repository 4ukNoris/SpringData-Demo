package com.example.springbootexercise.services;

import com.example.springbootexercise.models.*;
import com.example.springbootexercise.repositories.AuthorRepository;
import com.example.springbootexercise.repositories.BookRepository;
import com.example.springbootexercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private static final String RESOURCE_PATH = "src\\main\\resources\\files";
    private static final String AUTHORS_FILE_PATH = RESOURCE_PATH + "\\authors.txt";
    private static final String CATEGORIES_FILE_PATH = RESOURCE_PATH + "\\categories.txt";
    private static final String BOOK_FILE_PATH = RESOURCE_PATH + "\\books.txt";

    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public SeedServiceImpl(AuthorRepository authorRepository, CategoryRepository categoryRepository,
                           BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> s.split("\\s+"))
                .map(name -> new Author(name[0], name[1]))
                .forEach(authorRepository::save);

    }

    @Override
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(Category::new)
                .forEach(categoryRepository::save);
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOK_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(this::getBookObject)
                .forEach(bookRepository::save);
    }

    private Book getBookObject(String data) {
        String[] bookParts = data.split(" ");
        int keyIndex = Integer.parseInt(bookParts[0]);
        EditionType editionType = EditionType.values()[keyIndex];

        LocalDate localDate = LocalDate.parse(bookParts[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        int numbOfCopies = Integer.parseInt(bookParts[2]);
        BigDecimal price = new BigDecimal(bookParts[3]);

        int indexRestriction = Integer.parseInt(bookParts[4]);
        AgeRestriction ageRestriction = AgeRestriction.values()[indexRestriction];

        String title = Arrays.stream(bookParts)
                .skip(5)
                .collect(Collectors.joining(" "));
        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(title, editionType, price, numbOfCopies, localDate, ageRestriction,author, categories);
    }
}
