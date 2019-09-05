package com.fcv.expressCourier.priceCalculator;

public interface PriceCalculator {
    double carPrice(String origin,String destination);
    double DronePrice(String origin, String destination);
}
