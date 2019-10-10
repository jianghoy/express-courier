package com.fcv.expressCourier.services.operation;

import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.dao.RobotRepository;
import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.models.Robot;
import com.fcv.expressCourier.payload.PricePlan;
import com.fcv.expressCourier.services.priceCalculator.PriceCalculator;
import com.fcv.expressCourier.services.utils.AddressToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fcv.expressCourier.services.utils.AddressToString.conversion;

@Service
public class RobotAssignmentService implements RobotAssignmentInterface {
    @Autowired
    private PriceCalculator priceCalculator;
    @Autowired
    private OrderRepository orderRepository;

    private int convertType(String type) {
        return type.toLowerCase().equals("drone")?1:0;
    }

    @Override
    public boolean assignRobot(Order o) {
        PricePlan plan;
        if (o.getType().equals("car")) {
            plan = priceCalculator.carPricePlan(conversion(o.getPickUpAddress()),
                    conversion((o.getShippingAddress())));
        } else {
            plan = priceCalculator.dronePricePlan(conversion(o.getPickUpAddress()),
                    conversion((o.getShippingAddress())));
        }
        o.setRobot(plan.getRobot());
        o.setPrice(plan.getPrice());
        o.setStatus("scheduled");
        orderRepository.save(o);
        return true;
    }
}
