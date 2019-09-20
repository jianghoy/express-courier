package com.fcv.expressCourier.services.deliveryManagement;

import com.fcv.expressCourier.models.Order;

public interface CheckoutInterface {
    boolean isValid(Order order);
    boolean placeOrder(Order order);

}
