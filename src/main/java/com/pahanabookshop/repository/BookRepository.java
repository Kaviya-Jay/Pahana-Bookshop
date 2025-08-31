package com.pahanabookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pahanabookshop.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByIsActiveTrue();

    List<Book> findByCategory(String category);

}
