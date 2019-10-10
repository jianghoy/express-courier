package com.fcv.expressCourier.controller;


import com.fcv.expressCourier.payload.PricePlan;
import com.fcv.expressCourier.services.price.PriceCalculator;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PriceSelectionController {
    private final PriceCalculator priceCalculator;

    public PriceSelectionController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @RequestMapping(value = "/price", method = RequestMethod.GET, produces = "application/json")
    public List<PricePlan> prices(@RequestParam(name = "orig") String orig,
                                  @RequestParam(name = "dest") String dest) {
        if (orig == null || orig.isEmpty() || dest == null || dest.isEmpty()) {
            return null;
        }
        List<PricePlan> resultList = new ArrayList<>();
        resultList.add(priceCalculator.carPricePlan(orig,dest));
        resultList.add(priceCalculator.dronePricePlan(orig, dest));
        return resultList;

    }

}

