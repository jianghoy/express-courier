package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.dao.UserRepository;
import com.fcv.expressCourier.models.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.security.Principal;
import java.util.Optional;

@RestController
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    // TODO: ONLY AUTHORIZE RIGHT USER
    @RequestMapping(value = "/order/{orderID}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable("orderID") int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found");
        }
        return optionalOrder.get();
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public Slice<Order> getOrderPagination(@RequestParam(name = "page") int page,
                                          @RequestParam(name = "size") int size,
                                          Principal principal) {

        Pageable pageable = PageRequest.of(page, size);
        //TODO: check if this is right
        return orderRepository.findAllByCustomer(
                userRepository.getUserById(Integer.parseInt(principal.getName())).getCustomer(), pageable);

    }
}
