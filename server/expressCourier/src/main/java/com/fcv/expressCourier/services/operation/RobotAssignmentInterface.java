package com.fcv.expressCourier.services.operation;

import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.models.Robot;

public interface RobotAssignmentInterface {
    Robot findRobot(Order o);

}
