package com.fcv.expressCourier.controller;


import com.fcv.expressCourier.priceCalculator.PriceCalculator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class PriceSelectionController {
    @Autowired
    private PriceCalculator priceCalculator;

    @RequestMapping(value = "/price", method = RequestMethod.GET, produces = "application/json")
    public JSONObject prices(@RequestParam(name = "orig", defaultValue = "SFO") String orig,
                               @RequestParam(name = "dest", defaultValue = "SFO") String dest,
                               Model model) {
        model.addAttribute("orig", orig);
        model.addAttribute("dest", dest);
        double dronePrice = priceCalculator.dronePrice(orig, dest);
        double carPrice = priceCalculator.carPrice(orig, dest);
        JSONObject obj = new JSONObject();
        try {
            obj.put("type", "car");
            obj.put("price", carPrice);
            obj.put("time", "Estimate time");

            obj.put("type", "drone");
            obj.put("price", dronePrice);
            obj.put("time", "Estimate time");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;

    }

}
