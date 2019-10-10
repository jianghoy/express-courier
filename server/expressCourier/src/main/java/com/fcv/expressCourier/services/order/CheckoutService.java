package com.fcv.expressCourier.services.order;

import com.fcv.expressCourier.dao.CustomerRepository;
import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.dao.UserRepository;
import com.fcv.expressCourier.models.Address;
import com.fcv.expressCourier.models.Customer;
import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.models.User;
import com.fcv.expressCourier.security.UserPrincipal;
import com.fcv.expressCourier.services.operation.RobotAssignmentInterface;
import com.fcv.expressCourier.services.priceCalculator.PriceCalculator;
import com.fcv.expressCourier.services.utils.AddressToString;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.fcv.expressCourier.services.utils.AddressToString.conversion;

@Service
public class CheckoutService implements CheckoutInterface {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final RobotAssignmentInterface robotAssignment;
    private final PriceCalculator priceCalculator;


    public CheckoutService(OrderRepository orderRepository, RobotAssignmentInterface robotAssignmentInterface, UserRepository userRepository, CustomerRepository customerRepository, UserRepository userRepository1, PriceCalculator priceCalculator) {
        this.orderRepository = orderRepository;
        this.robotAssignment = robotAssignmentInterface;
        this.customerRepository = customerRepository;

        this.userRepository = userRepository1;
        this.priceCalculator = priceCalculator;
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
            //order.setRobot(robotAssignmentInterface.findRobot(order));
            Optional<User> u = userRepository.findByNameOrEmail(currentUser.getEmail(),currentUser.getEmail());
            if (!u.isPresent()) {
                return false;
            }
            Customer c = u.get().getCustomer();
            order.setCustomer(c);
            order.setGeneratedTimestamp(new Date());
            order.setStatus("waiting to be scheduled");

            order = orderRepository.save(order);
            robotAssignment.assignRobot(order);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
