package com.fcv.expressCourier.priceCalculator;

public interface PriceCalculator {
    double carPrice(String origin,String destination);
    double dronePrice(String origin, String destination);
}
