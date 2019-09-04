package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.models.Customer;
import com.fcv.expressCourier.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {

//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private SalesOrderService customerOrderService;

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public String createOrder(@PathVariable("orderId") int orderId) {
        // find order given order id, the following is dummy data
        Order order = new Order();

        // should get customer from order service
        Customer customer = new Customer();
        order.setCustomer(customer);
        order.setShippingAddress(customer.getShippingAddress());
        order.setBillingAddress(customer.getBillingAddress());

        return "";
    }
}