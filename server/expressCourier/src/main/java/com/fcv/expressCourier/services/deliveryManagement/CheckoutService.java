package com.fcv.expressCourier.services.deliveryManagement;

import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.models.Address;
import com.fcv.expressCourier.models.Order;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService implements CheckoutInterface {
    private final OrderRepository orderRepository;
    private final RobotAssignmentInterface robotAssignmentInterface;

    public CheckoutService(OrderRepository orderRepository, RobotAssignmentInterface robotAssignmentInterface) {
        this.orderRepository = orderRepository;
        this.robotAssignmentInterface = robotAssignmentInterface;
    }

    @Override
    public boolean isValid(Order order) {
        return isAddressValid(order.getPickUpAddress()) &&
                isAddressValid(order.getShippingAddress());
    }

    // TODO: add check geolocation method
    private boolean isAddressValid(Address address){
        return address.getCity().equals("San Francisco");
    }

    @Override
    public boolean placeOrder(Order order){
        try {
            //order.setRobot(robotAssignmentInterface.findRobot(order));
            orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
