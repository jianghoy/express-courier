package com.fcv.expressCourier.services.deliveryManagement;

import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.models.Robot;

public interface RobotAssignmentInterface {
    Robot findRobot(Order o);
}
