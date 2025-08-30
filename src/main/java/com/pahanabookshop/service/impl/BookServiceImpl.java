package com.pahanabookshop.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pahanabookshop.model.Book;
import com.pahanabookshop.repository.BookRepository;
import com.pahanabookshop.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Boolean deleteBook(Integer id) {
        Book book = bookRepository.findById(id).orElse(null);

        if (!ObjectUtils.isEmpty(book)) {
            bookRepository.delete(book);
            return true;
        }
        return false;
    }

    @Override
    public Book getBookById(Integer id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book;
    }

    @Override
    public Book updateBook(Book book, MultipartFile image) {

        Book dbBook = getBookById(book.getId());

        String imageName = image.isEmpty() ? dbBook.getImage() : image.getOriginalFilename();

        dbBook.setTitle(book.getTitle());
        dbBook.setDescription(book.getDescription());
        dbBook.setCategory(book.getCategory());
        dbBook.setPrice(book.getPrice());
        dbBook.setStock(book.getStock());
        dbBook.setImage(imageName);

        Book updateBook = bookRepository.save(dbBook);

        if (!ObjectUtils.isEmpty(updateBook)) {

            if (!image.isEmpty()) {

                try {
                    File saveFile = new ClassPathResource("static/img").getFile();

                    Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "book_img" + File.separator
                            + image.getOriginalFilename());
                    Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return book;
        }

        return null;
    }

}

