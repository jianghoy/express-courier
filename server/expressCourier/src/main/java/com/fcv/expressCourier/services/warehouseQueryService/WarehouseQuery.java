package com.fcv.expressCourier.services.warehouseQueryService;

import com.fcv.expressCourier.models.WareHouse;
import com.fcv.expressCourier.payload.LatLon;

public interface WarehouseQuery {
    WareHouse nearestWarehouseInStraightLine(LatLon latLon);
}
