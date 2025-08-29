package com.pahanabookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pahanabookshop.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
