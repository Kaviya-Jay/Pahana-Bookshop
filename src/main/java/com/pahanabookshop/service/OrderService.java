package com.pahanabookshop.service;

import java.util.List;

import com.pahanabookshop.model.OrderRequest;
import com.pahanabookshop.model.BookOrder;

public interface OrderService {

    public void saveOrder(Integer userid,OrderRequest orderRequest);

    public List<BookOrder> getOrdersByUser(Integer userId);

    public Boolean updateOrderStatus(Integer id,String status);

    public List<BookOrder> getAllOrders();
}
