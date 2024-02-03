package com.example.springbootexercise.repositories;

import com.example.springbootexercise.models.Author;
import com.example.springbootexercise.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);

}
