package com.fcv.expressCourier.services.deliveryManagement;

import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.models.Address;
import com.fcv.expressCourier.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService implements CheckoutInterface {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean isValid(Order order) {
        return isAddressValid(order.getPickUpAddress()) &&
                isAddressValid(order.getShippingAddress());
    }

    // TODO: add check geolocation method
    private boolean isAddressValid(Address address){
        return true;
    }

    @Override
    public boolean placeOrder(Order order) {
        orderRepository.save(order);
        return true;
    }
}
