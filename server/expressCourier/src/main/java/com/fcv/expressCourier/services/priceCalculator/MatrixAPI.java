package com.fcv.expressCourier.services.priceCalculator;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
// TODO: use modern library for json parsing;

@PropertySource("classpath:secret.properties")
@Service
public class MatrixAPI implements PriceCalculator {

    @Value("${key.google}")
    private String API_KEY;

    private OkHttpClient client = new OkHttpClient();

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
        try{
            JSONObject jsonRespRouteDistance = new JSONObject(response)
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("distance");
            value = jsonRespRouteDistance.get("value").toString();
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }

        return Integer.parseInt(value) * 0.0006;
    }

    @Override
    public double dronePrice(String origin, String destination) {

        double[] initLocation;
        double[] finalLocation;
        try {
            initLocation = geoLocation(origin);
            finalLocation = geoLocation(destination);
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
        double dist = straightLineDistance(initLocation, finalLocation);
        return dist * 0.0006;
    }

    private double[] geoLocation(String location) throws JSONException {
        String url_request = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location + "&key=" + API_KEY;
        String response = null;
        try {
            response = run(url_request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // String output = response(["data"]["row"][0]["element"]["distance"]["text"]);
        JSONObject jsonRespRoute;
        jsonRespRoute = new JSONObject(response)
                .getJSONArray("results")
                .getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location");
        double latitude = Double.parseDouble(jsonRespRoute.get("lat").toString());
        double longitude = Double.parseDouble(jsonRespRoute.get("lng").toString());


        //System.out.println("lan=" + latitude + "lon=" + longitude);
        return new double[]{latitude, longitude};
    }

    private double straightLineDistance(double[] loc1, double[] loc2) {

        double lat1 = loc1[0];
        double lon1 = loc1[1];
        double lat2 = loc2[0];
        double lon2 = loc2[1];

        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;  // by default: miles
            dist = dist * 1.609344 * 1000;     // covert miles to m
            return dist;
        }
    }

    public static void main(String[] args) {

        MatrixAPI matrix = new MatrixAPI();
        System.out.println(matrix.carPrice("UTD", "Church in Dallas"));
        System.out.println(matrix.dronePrice("UTD", "Church in Dallas"));
    }
}