package com.fcv.expressCourier.services.warehouse;

import com.fcv.expressCourier.models.Address;
import com.fcv.expressCourier.models.WareHouse;
import com.fcv.expressCourier.payload.Location;

public interface WarehouseQuery {
    WareHouse nearestWarehouseInStraightLine(Location location);
    WareHouse nearestWarehouseInRoadDistance(Address address);
}
