package com.pahanabookshop.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pahanabookshop.model.Cart;
import com.pahanabookshop.model.OrderAddress;
import com.pahanabookshop.model.OrderRequest;
import com.pahanabookshop.model.BookOrder;
import com.pahanabookshop.repository.CartRepository;
import com.pahanabookshop.repository.BookOrderRepository;
import com.pahanabookshop.service.OrderService;
import com.pahanabookshop.util.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private BookOrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void saveOrder(Integer userid, OrderRequest orderRequest) {

        List<Cart> carts = cartRepository.findByUserId(userid);

        for (Cart cart : carts) {

            BookOrder order = new BookOrder();

            order.setOrderId(UUID.randomUUID().toString());
            order.setOrderDate(LocalDate.now());

            order.setBook(cart.getBook());
            order.setPrice(cart.getBook().getDiscountPrice());

            order.setQuantity(cart.getQuantity());
            order.setUser(cart.getUser());

            order.setStatus(OrderStatus.IN_PROGRESS.getName());
            order.setPaymentType(orderRequest.getPaymentType());

            OrderAddress address = new OrderAddress();
            address.setFirstName(orderRequest.getFirstName());
            address.setLastName(orderRequest.getLastName());
            address.setEmail(orderRequest.getEmail());
            address.setMobileNo(orderRequest.getMobileNo());
            address.setAddress(orderRequest.getAddress());

            order.setOrderAddress(address);

            orderRepository.save(order);

        }

    }

    @Override
    public List<BookOrder> getOrdersByUser(Integer userId) {
        List<BookOrder> orders = orderRepository.findByUserId(userId);
        return orders;
    }

    @Override
    public Boolean updateOrderStatus(Integer id, String status) {
        Optional<BookOrder> findById = orderRepository.findById(id);
        if (findById.isPresent()) {
            BookOrder bookOrder = findById.get();
            bookOrder.setStatus(status);
            orderRepository.save(bookOrder);
            return true;
        }
        return false;
    }

    @Override
    public List<BookOrder> getAllOrders() {
        return orderRepository.findAll();
    }
}
