package com.pahanabookshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pahanabookshop.model.BookOrder;

public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {

    List<BookOrder> findByUserId(Integer userId);
}
