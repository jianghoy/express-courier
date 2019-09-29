package com.fcv.expressCourier.services.deliveryManagement;

import com.fcv.expressCourier.dao.CustomerRepository;
import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.dao.UserRepository;
import com.fcv.expressCourier.models.Address;
import com.fcv.expressCourier.models.Customer;
import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.models.User;
import com.fcv.expressCourier.security.UserPrincipal;
import com.fcv.expressCourier.services.priceCalculator.PriceCalculator;
import com.fcv.expressCourier.utils.AddressToString;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckoutService implements CheckoutInterface {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final RobotAssignmentInterface robotAssignmentInterface;
    private final PriceCalculator priceCalculator;
    private final AddressToString addressToString;


    public CheckoutService(OrderRepository orderRepository, RobotAssignmentInterface robotAssignmentInterface, UserRepository userRepository, CustomerRepository customerRepository, UserRepository userRepository1, PriceCalculator priceCalculator, AddressToString addressToString) {
        this.orderRepository = orderRepository;
        this.robotAssignmentInterface = robotAssignmentInterface;
        this.customerRepository = customerRepository;

        this.userRepository = userRepository1;
        this.priceCalculator = priceCalculator;
        this.addressToString = addressToString;
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
            order.setStatus("waiting to be scheduled");

            if (order.getType().equals("car")) {
                order.setPrice(priceCalculator.carPrice(addressToString.conversion(order.getPickUpAddress()),
                        addressToString.conversion(order.getShippingAddress())));
            }
            orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
