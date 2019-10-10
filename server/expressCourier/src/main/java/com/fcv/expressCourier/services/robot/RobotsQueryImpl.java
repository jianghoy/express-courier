package com.fcv.expressCourier.services.robot;

import com.fcv.expressCourier.models.Robot;
import com.fcv.expressCourier.models.WareHouse;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RobotsQueryImpl implements RobotsQuery {
    @Override
    public Robot findEarliestAvailableDroneInWarehouse(WareHouse wareHouse) {
        return findEarliest(wareHouse,1);
    }

    private Robot findEarliest(WareHouse wareHouse, int type) {
        Robot firstAvailableRobot = null;
        Date earliestAvailableTime = null;
        if (wareHouse == null) {
            return null;
        }
        for (Robot robot: wareHouse.getRobotList()) {
            if (robot.getType() == type && (earliestAvailableTime == null ||
                    earliestAvailableTime.compareTo(robot.getEstimatedIdleTime()) > 0)) {
                earliestAvailableTime = robot.getEstimatedIdleTime();
                firstAvailableRobot = robot;
            }
        }
        return firstAvailableRobot;
    }

    @Override
    public Robot findEarliestAvailableCarInWarehouse(WareHouse wareHouse) {
        return findEarliest(wareHouse,0);
    }
}
