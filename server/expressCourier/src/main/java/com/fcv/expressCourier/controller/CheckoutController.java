package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.services.deliveryManagement.CheckoutInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class CheckoutController {
    private final CheckoutInterface checkout;

    public CheckoutController(CheckoutInterface checkout) {
        this.checkout = checkout;
    }

    @RequestMapping(value = "/checkout",method = PUT)
    public String checkout(@RequestBody Order order){
        if (!checkout.isValid(order)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot place such order");
        }
        if (!checkout.placeOrder(order)) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Fail to checkout");
        }
        return "success";
    }


}
