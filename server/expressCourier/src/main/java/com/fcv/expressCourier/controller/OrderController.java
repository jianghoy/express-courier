package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.dao.UserRepository;
import com.fcv.expressCourier.exception.BadRequestException;
import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.models.User;
import com.fcv.expressCourier.security.CurrentUser;
import com.fcv.expressCourier.security.UserPrincipal;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.io.Serializable;
import java.security.Principal;
import java.util.List;
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
    public ListAndHasNext getOrderPagination(@RequestParam(name = "page") int page,
                                          @RequestParam(name = "size") int size,
                                          @CurrentUser UserPrincipal currentUser) {

        Pageable pageable = PageRequest.of(page, size);
        Optional<User> u = userRepository.findByNameOrEmail(currentUser.getName(),currentUser.getName());
        if (!u.isPresent()) {
            throw new BadRequestException("user not found");
        }
        Slice<Order> slice =  orderRepository.findByCustomer(u.get().getCustomer(),pageable);
//
////
//
//
//        return new ListAndHasNext(slice.getContent(),slice.hasNext());
        return new ListAndHasNext(slice.getContent(),slice.hasNext());


    }

    private class ListAndHasNext implements Serializable {
        List<Order> orders;
        boolean hasNext;

        public ListAndHasNext(List<Order> orders, boolean hasNext) {
            this.orders = orders;
            this.hasNext = hasNext;
        }

        public List<Order> getOrders() {
            return orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }
    }
}
