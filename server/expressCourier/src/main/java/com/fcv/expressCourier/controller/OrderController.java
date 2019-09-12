package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "/order/{orderID}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable("orderID") int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found");
        }
        return optionalOrder.get();
    }
}
