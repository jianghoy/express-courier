package com.fcv.expressCourier.services.warehouseQueryService;

import com.fcv.expressCourier.models.WareHouse;
import com.fcv.expressCourier.services.location.Location;

public interface WarehouseQuery {
    WareHouse nearestWarehouseInStraightLine(Location location);
}
