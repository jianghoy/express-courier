package com.fcv.expressCourier.services.priceCalculator;

import java.io.IOException;
import java.util.Date;

import com.fcv.expressCourier.models.Robot;
import com.fcv.expressCourier.models.WareHouse;
import com.fcv.expressCourier.payload.PricePlan;
import com.fcv.expressCourier.payload.Location;
import com.fcv.expressCourier.services.location.LocationService;
import com.fcv.expressCourier.services.robot.RobotsQuery;
import com.fcv.expressCourier.services.warehouse.WarehouseQuery;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
// TODO: use modern library for json parsing;

/**
 * ALL DISTANCE UNIT IS METER!
 */
@PropertySource("classpath:secret.properties")
@Service
public class PriceCalculatorImpl implements PriceCalculator {

    @Value("${key.google}")
    private String API_KEY;

    @Value("${base_price.car}")
    private double CAR_BASE_PRICE;

    @Value("${base_price.drone}")
    private double DRONE_BASE_PRICE;

    @Value("${speed.mps.demo.drone}")
    private double DRONE_SPEED;

    private final LocationService locationService;
    private final WarehouseQuery warehouseQuery;
    private final RobotsQuery robotsQuery;

    public PriceCalculatorImpl(LocationService locationService, WarehouseQuery warehouseQuery, RobotsQuery robotsQuery) {
        this.locationService = locationService;
        this.warehouseQuery = warehouseQuery;
        this.robotsQuery = robotsQuery;
    }

    @Override
    public double carPrice(String origin, String destination) {

        return locationService.roadDistInMeter(origin,destination);
    }

    @Override
    public double dronePrice(String origin, String destination) {

        Location origLocation = locationService.getLatLon(origin);
        Location destLocation = locationService.getLatLon(destination);
        double dist = locationService.straightLineDistInMeter(origLocation,destLocation);
        return dist * DRONE_BASE_PRICE;
    }

    @Override
    public PricePlan dronePricePlan(String origin, String destination) {

        Location origLocation = locationService.getLatLon(origin);
        Location destLocation = locationService.getLatLon(destination);

        // find nearest warehouse
        WareHouse nearestWareHouse = warehouseQuery.nearestWarehouseInStraightLine(origLocation);
        Location nearestWareHouseLocation = new Location(nearestWareHouse.getWareHouseAddress().getLatitude(),
                nearestWareHouse.getWareHouseAddress().getLongtitude());

        //get robot
        Robot firstAvailableRobot = robotsQuery.findEarliestAvailableDroneInWarehouse(nearestWareHouse);

        double pickupDist = locationService.straightLineDistInMeter(origLocation,nearestWareHouseLocation);
        double deliveryDist = locationService.straightLineDistInMeter(origLocation,destLocation);
        double returnDist = locationService.straightLineDistInMeter(destLocation,nearestWareHouseLocation);

        Date firstAvailableTime = new Date();
        if (firstAvailableTime.compareTo(firstAvailableRobot.getEstimatedIdleTime()) < 0) {
            firstAvailableTime = firstAvailableRobot.getEstimatedIdleTime();
        }
        Date estPickupTime = new Date(firstAvailableTime.getTime() + (long)(pickupDist / DRONE_SPEED * 1000));
        Date estDeliveryTime = new Date(estPickupTime.getTime() + (long)(deliveryDist / DRONE_SPEED * 1000));
        Date estReturnTime = new Date(estDeliveryTime.getTime() + (long)(returnDist / DRONE_SPEED * 1000));

        double price = deliveryDist * DRONE_BASE_PRICE;
        PricePlan plan = new PricePlan();
        plan.setPrice(price);
        plan.setEstimatedDeliveryTime(estDeliveryTime);
        plan.setEstimatedPickupTime(estPickupTime);
        plan.setEstimatedReturnTime(estReturnTime);
        plan.setRobot(firstAvailableRobot);
        plan.setType("drone");
        return plan;
    }

    @Override
    public PricePlan carPricePlan(String origin, String destination) {
        PricePlan plan = new PricePlan();
        plan.setPrice(carPrice(origin,destination));
        plan.setType("car");
        return plan;
    }

}