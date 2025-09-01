package com.pahanabookshop.service;

import java.util.List;

import com.pahanabookshop.model.Cart;

public interface CartService {

    public Cart saveCart(Integer bookId, Integer userId);

    public List<Cart> getCartsByUser(Integer userId);

    public Integer getCountCart(Integer userId);

    public void updateQuantity(String sy, Integer cid);

}
