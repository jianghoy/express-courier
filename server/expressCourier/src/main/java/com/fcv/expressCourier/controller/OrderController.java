package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.models.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {
    @RequestMapping(value = "/order/{orderID}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable("orderId") int orderId) {
        // Order order = orderDao.getOrder(orderId)
        Order order = new Order();
        return order;
    }
}