package com.fcv.expressCourier.services.warehouse;

import com.fcv.expressCourier.dao.WareHouseRepository;
import com.fcv.expressCourier.models.Address;
import com.fcv.expressCourier.models.WareHouse;
import com.fcv.expressCourier.payload.Location;
import com.fcv.expressCourier.services.location.LocationService;
import com.fcv.expressCourier.services.utils.AddressToString;
import org.springframework.stereotype.Service;

import java.util.Iterator;

import static com.fcv.expressCourier.services.utils.AddressToString.conversion;

@Service
public class WarehouseQueryImpl implements WarehouseQuery {
    private final WareHouseRepository wareHouseRepository;
    private final LocationService locationService;

    public WarehouseQueryImpl(WareHouseRepository wareHouseRepository, LocationService locationService) {
        this.wareHouseRepository = wareHouseRepository;
        this.locationService = locationService;
    }

    @Override
    public WareHouse nearestWarehouseInStraightLine(Location location) {
        Iterator<WareHouse> wareHouseIterator = wareHouseRepository.findAll().iterator();
        WareHouse nearestWareHouse = null;
        double minDist = Double.MAX_VALUE;
        while (wareHouseIterator.hasNext()) {
            WareHouse wareHouse = wareHouseIterator.next();
            Location nearestWareHouseLatLon = new Location(wareHouse.getWareHouseAddress().getLatitude(),
                    wareHouse.getWareHouseAddress().getLongtitude());
            double dist = locationService.straightLineDistInMeter(nearestWareHouseLatLon, location);
            if (dist < minDist) {
                minDist = dist;
                nearestWareHouse = wareHouse;
            }
        }
        return nearestWareHouse;
    }

    @Override
    public WareHouse nearestWarehouseInRoadDistance(Address address) {
        Iterator<WareHouse> wareHouseIterator = wareHouseRepository.findAll().iterator();
        WareHouse nearestWareHouse = null;
        double minDist = Double.MAX_VALUE;
        while (wareHouseIterator.hasNext()) {
            WareHouse wareHouse = wareHouseIterator.next();
            double dist = locationService.roadDistInMeter(conversion(wareHouse.getWareHouseAddress())
                    , conversion(address));
            if (dist < minDist) {
                minDist = dist;
                nearestWareHouse = wareHouse;
            }
        }
        return nearestWareHouse;
    }


}
