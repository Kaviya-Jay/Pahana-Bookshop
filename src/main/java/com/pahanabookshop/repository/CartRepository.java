package com.pahanabookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pahanabookshop.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    public Cart findByBookIdAndUserId(Integer bookId, Integer userId);
}
