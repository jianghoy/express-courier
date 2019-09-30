package com.fcv.expressCourier.services.deliveryManagement;

import com.fcv.expressCourier.dao.RobotRepository;
import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.models.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RobotAssignmentService implements RobotAssignmentInterface {
    @Autowired
    private RobotRepository robotRepository;

    @Override
    public Robot findRobot(Order o) throws IllegalArgumentException {
        List<Robot> robots = robotRepository.findAllByType(convertType(o.getType()));
        if (robots.isEmpty()) {
            throw new IllegalArgumentException("cannot find robot");
        }
        Robot resRo = robots.get(0);
        for (Robot r:robots) {
            if (r.getTodoOrder().size() < resRo.getTodoOrder().size()) {
                resRo = r;
            }
        }
        return resRo;

    }

    private int convertType(String type) {
        return type.toLowerCase().equals("drone")?1:0;
    }
}
