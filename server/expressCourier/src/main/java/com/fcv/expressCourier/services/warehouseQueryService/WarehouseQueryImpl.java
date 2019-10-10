package com.fcv.expressCourier.services.warehouseQueryService;

import com.fcv.expressCourier.dao.WareHouseRepository;
import com.fcv.expressCourier.models.WareHouse;
import com.fcv.expressCourier.payload.LatLon;
import com.fcv.expressCourier.services.location.LocationService;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class
WarehouseQueryImpl implements WarehouseQuery {
    private final WareHouseRepository wareHouseRepository;
    private final LocationService locationService;

    public WarehouseQueryImpl(WareHouseRepository wareHouseRepository, LocationService locationService) {
        this.wareHouseRepository = wareHouseRepository;
        this.locationService = locationService;
    }

    @Override
    public WareHouse nearestWarehouseInStraightLine(LatLon latLon) {
        Iterator<WareHouse> wareHouseIterator = wareHouseRepository.findAll().iterator();
        WareHouse nearestWareHouse = null;
        double minDist = Double.MAX_VALUE;
        while (wareHouseIterator.hasNext()) {
            WareHouse wareHouse = wareHouseIterator.next();
            LatLon nearestWareHouseLatLon = new LatLon(wareHouse.getWareHouseAddress().getLatitude(),
                    wareHouse.getWareHouseAddress().getLongtitude());
            double dist = locationService.straightLineDistInMeter(nearestWareHouseLatLon, latLon);
            if (dist < minDist) {
                minDist = dist;
                nearestWareHouse = wareHouse;
            }
        }
        return nearestWareHouse;
    }
}
