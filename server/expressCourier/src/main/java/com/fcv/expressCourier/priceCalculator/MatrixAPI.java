package com.fcv.expressCourier.priceCalculator;

import java.io.IOException;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Service
public class MatrixAPI implements PriceCalculator {

    private static final String API_KEY = APIKey.key;

    private OkHttpClient client = new OkHttpClient();

    private String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    public double carPrice(String origin, String destination) {

        String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin +
                "&destinations=" + destination + "&departure_time=now&key=" + API_KEY;
        String response;
        try {
            response = run(url_request);
        }
        catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        JSONObject jsonRespRouteDistance = new JSONObject(response)
                .getJSONArray("rows")
                .getJSONObject(0)
                .getJSONArray("elements")
                .getJSONObject(0)
                .getJSONObject("distance");
        String value = jsonRespRouteDistance.get("value").toString();
        return Integer.parseInt(value) * 0.0006;
    }

    @Override
    public double DronePrice(String origin, String destination) {
        double[] initLocation = geoLocation(origin);
        double[] finalLocation = geoLocation(destination);
        double dist = distance(initLocation, finalLocation);
        return dist * 0.0006;
    }

    private double[] geoLocation(String location) {
        String url_request = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location + "&key=" + API_KEY;
        String response = null;
        try {
            response = run(url_request);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonRespRoute = new JSONObject(response)
                .getJSONArray("results")
                .getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location");
        double latitude = Double.parseDouble(jsonRespRoute.get("lat").toString());
        double longitude = Double.parseDouble(jsonRespRoute.get("lng").toString());
        return new double[]{latitude, longitude};
    }

    private double distance(double[] loc1, double[] loc2) {

        double lat1 = loc1[0];
        double lon1 = loc1[1];
        double lat2 = loc2[0];
        double lon2 = loc2[1];

        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            // mathematics formula for calculating distance
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;  // by default: miles
            dist = dist * 1.609344 * 1000;     // covert miles to m
            return dist;
        }
    }

    public static void main(String[] args){

        MatrixAPI matrix = new MatrixAPI();
        System.out.println(matrix.carPrice("UTD", "Church in Dallas"));
        System.out.println(matrix.DronePrice("UTD", "Church in Dallas"));
    }
}