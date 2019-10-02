package com.fcv.expressCourier.services.priceCalculator;

import com.fcv.expressCourier.payload.PricePlan;

public interface PriceCalculator {
    double carPrice(String origin,String destination);
    double dronePrice(String origin, String destination);
    PricePlan carPricePlan(String origin, String destination);
    PricePlan dronePricePlan(String origin, String destination);
}
