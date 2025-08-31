package com.pahanabookshop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pahanabookshop.model.Book;

public interface BookService {

    public Book saveBook(Book book);

    public List<Book> getAllBook();

    public Boolean deleteBook(Integer id);

    public Book getBookById(Integer id);

    public Book updateBook(Book book, MultipartFile file);

    public List<Book> getAllActiveBook(String category);

}