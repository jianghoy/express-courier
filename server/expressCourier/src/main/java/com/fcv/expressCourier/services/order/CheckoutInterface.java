package com.fcv.expressCourier.services.order;

import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.security.UserPrincipal;

public interface CheckoutInterface {
    boolean isValid(Order order);
    boolean placeOrder(Order order, UserPrincipal currentUser);

}
