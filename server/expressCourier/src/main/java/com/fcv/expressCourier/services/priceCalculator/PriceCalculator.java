package com.fcv.expressCourier.services.priceCalculator;

public interface PriceCalculator {
    double carPrice(String origin,String destination);
    double dronePrice(String origin, String destination);
}
