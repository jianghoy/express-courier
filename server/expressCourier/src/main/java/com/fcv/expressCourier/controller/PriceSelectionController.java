package com.fcv.expressCourier.controller;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


public class PriceSelectionController {
    @RequestMapping(value = "/price", method = RequestMethod.GET, produces = "application/json")
    public JSONObject greeting(@RequestParam(name="orig",  defaultValue="SFO") String orig, @RequestParam(name="dest",  defaultValue="SFO") String dest, Model model) {
        model.addAttribute("orig", orig);
        model.addAttribute("dest", dest);
        int dronePrice = getPrice(orig, dest, true);
        int carPrice = getPrice(orig, dest, false);
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

    private int getPrice(String  orig, String des, boolean isDrone){
        return 0;
    }
}
