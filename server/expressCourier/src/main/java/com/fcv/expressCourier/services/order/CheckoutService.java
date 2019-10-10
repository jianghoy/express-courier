package com.fcv.expressCourier.services.order;

import com.fcv.expressCourier.dao.CustomerRepository;
import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.dao.UserRepository;
import com.fcv.expressCourier.models.*;
import com.fcv.expressCourier.security.UserPrincipal;
import com.fcv.expressCourier.services.operation.ScheduleInterface;
import com.fcv.expressCourier.services.price.PriceCalculator;
import com.fcv.expressCourier.utils.AddressToString;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckoutService implements CheckoutInterface {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ScheduleInterface scheduleInterface;
    private final PriceCalculator priceCalculator;
    private final ScheduleInterface schedule;


    public CheckoutService(OrderRepository orderRepository, ScheduleInterface scheduleInterface, UserRepository userRepository, CustomerRepository customerRepository, UserRepository userRepository1, PriceCalculator priceCalculator, AddressToString addressToString, ScheduleInterface schedule) {
        this.orderRepository = orderRepository;
        this.scheduleInterface = scheduleInterface;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository1;
        this.priceCalculator = priceCalculator;
        this.schedule = schedule;
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
    public boolean placeOrder(Order order, UserPrincipal currentUser){
        try {
            //order.setRobot(scheduleInterface.findRobot(order));
            Optional<User> u = userRepository.findByNameOrEmail(currentUser.getEmail(),currentUser.getEmail());
            if (!u.isPresent()) {
                return false;
            }
            Customer c = u.get().getCustomer();
            order.setCustomer(c);
            order.setStatus("waiting to be scheduled");
            order = orderRepository.save(order);
            schedule.assignRobotToOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
