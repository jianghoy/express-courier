package com.fcv.expressCourier.services.price;

import java.io.IOException;
import java.util.Date;

import com.fcv.expressCourier.models.Robot;
import com.fcv.expressCourier.models.WareHouse;
import com.fcv.expressCourier.payload.LatLon;
import com.fcv.expressCourier.payload.PricePlan;
import com.fcv.expressCourier.services.location.LocationService;
import com.fcv.expressCourier.services.robot.RobotsQuery;
import com.fcv.expressCourier.services.warehouseQueryService.WarehouseQuery;
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
    private OkHttpClient client = new OkHttpClient();

    public PriceCalculatorImpl(LocationService locationService, WarehouseQuery warehouseQuery, RobotsQuery robotsQuery) {
        this.locationService = locationService;
        this.warehouseQuery = warehouseQuery;
        this.robotsQuery = robotsQuery;
    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }

    @Override
    public double carPrice(String origin, String destination) {

        String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations="
                + destination + "&departure_time=now&key=" + API_KEY;
        String response;
        try {
            response = run(url_request);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        // String output = response(["data"]["row"][0]["element"]["distance"]["text"]);
        String value;
        try {
            JSONObject jsonRespRouteDistance = new JSONObject(response)
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("distance");
            value = jsonRespRouteDistance.get("value").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        return Integer.parseInt(value) * CAR_BASE_PRICE;
    }

    @Override
    public double dronePrice(String origin, String destination) {

        LatLon origLatLon = locationService.getLatLon(origin);
        LatLon destLatLon = locationService.getLatLon(destination);
        double dist = locationService.straightLineDistInMeter(origLatLon, destLatLon);
        return dist * DRONE_BASE_PRICE;
    }

    @Override
    public PricePlan dronePricePlan(String origin, String destination) {

        LatLon origLatLon = locationService.getLatLon(origin);
        LatLon destLatLon = locationService.getLatLon(destination);

        // find nearest warehouse
        WareHouse nearestWareHouse = warehouseQuery.nearestWarehouseInStraightLine(origLatLon);
        LatLon nearestWareHouseLatLon = new LatLon(nearestWareHouse.getWareHouseAddress().getLatitude(),
                nearestWareHouse.getWareHouseAddress().getLongtitude());

        //get robot
        Robot firstAvailableRobot = robotsQuery.findEarliestAvailableDroneInWarehouse(nearestWareHouse);

        double pickupDist = locationService.straightLineDistInMeter(origLatLon, nearestWareHouseLatLon);
        double deliveryDist = locationService.straightLineDistInMeter(origLatLon, destLatLon);
        Date firstAvailableTime = new Date();
        if (firstAvailableTime.compareTo(firstAvailableRobot.getEstimatedIdleTime()) < 0) {
            firstAvailableTime = firstAvailableRobot.getEstimatedIdleTime();
        }
        Date estPickupTime = new Date(firstAvailableTime.getTime() + (long)(pickupDist / DRONE_SPEED * 1000));
        Date estDeliveryTime = new Date(estPickupTime.getTime() + (long)(deliveryDist / DRONE_SPEED * 1000));
        return new PricePlan(deliveryDist * DRONE_BASE_PRICE,"drone",estPickupTime,estDeliveryTime,firstAvailableRobot);

    }

    //TODO: fix this!
    @Override
    public PricePlan carPricePlan(String origin, String destination) {

        return new PricePlan(carPrice(origin,destination),"car",null,null,null);
    }

}