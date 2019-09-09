package com.fcv.expressCourier.controller;


import com.fcv.expressCourier.priceCalculator.PriceCalculator;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PriceSelectionController {
    private final PriceCalculator priceCalculator;

    public PriceSelectionController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @RequestMapping(value = "/price", method = RequestMethod.GET, produces = "application/json")
    public List<PricePlan> prices(@RequestParam(name = "orig", defaultValue = "SFO") String orig,
                               @RequestParam(name = "dest", defaultValue = "SFO") String dest) {
        double dronePrice = priceCalculator.dronePrice(orig, dest);
        double carPrice = priceCalculator.carPrice(orig, dest);
        List<PricePlan> resultList = new ArrayList<>();
        resultList.add(new PricePlan(carPrice,"Car"));
        resultList.add(new PricePlan(dronePrice,"Drone"));
        return resultList;

    }

}

class PricePlan implements Serializable {
    private double price;
    private String type;

    public PricePlan(double price, String type) {
        this.price = price;
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}
