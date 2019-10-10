package com.fcv.expressCourier.services.operation;

import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.payload.PricePlan;
import com.fcv.expressCourier.services.price.PriceCalculator;
import com.fcv.expressCourier.services.robot.RobotsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fcv.expressCourier.utils.AddressToString.conversion;

@Service
public class ScheduleService implements ScheduleInterface {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PriceCalculator priceCalculator;

    @Override
    public boolean assignRobotToOrder(Order order) {
        PricePlan plan;
        if (order.getType().equals("car")) {
            plan = priceCalculator.carPricePlan(conversion(order.getPickUpAddress()),
                    conversion(order.getShippingAddress()));

        } else {
            plan = priceCalculator.dronePricePlan(conversion(order.getPickUpAddress()),
                    conversion(order.getShippingAddress()));
        }
        order.setPrice(plan.getPrice());
        order.setRobot(plan.getRobot());
        order.setStatus("scheduled");
        return true;
    }
}
