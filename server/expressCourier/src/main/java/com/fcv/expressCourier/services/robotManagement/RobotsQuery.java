package com.fcv.expressCourier.services.robotManagement;

import com.fcv.expressCourier.models.Robot;
import com.fcv.expressCourier.models.WareHouse;

public interface RobotsQuery {
    Robot findEarliestAvailableDroneInWarehouse(WareHouse wareHouse);
    Robot findEarliestAvailableCarInWarehouse(WareHouse wareHouse);
}
