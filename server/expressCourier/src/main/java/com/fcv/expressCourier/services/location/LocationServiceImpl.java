package com.fcv.expressCourier.services.location;

import com.fcv.expressCourier.payload.Location;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@PropertySource("classpath:secret.properties")
@Service
public class LocationServiceImpl implements LocationService {

    @Value("${key.google}")
    private String API_KEY;
    // back up plan by Jack
    private OkHttpClient client = new OkHttpClient();

    private String sendReq(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }
    @Override
    public Location getLatLon(String address) {
        String url_request = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + API_KEY;
        String response = null;
        try {
            response = sendReq(url_request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // String output = response(["data"]["row"][0]["element"]["distance"]["text"]);
        JSONObject jsonRespRoute;
        double latitude = 0;
        double longitude = 0;
        try {
            jsonRespRoute = new JSONObject(response)
                    .getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONObject("location");
            latitude = Double.parseDouble(jsonRespRoute.get("lat").toString());
            longitude = Double.parseDouble(jsonRespRoute.get("lng").toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return new Location(latitude,longitude);
    }

    @Override
    public double straightLineDistInMeter(Location latLonOne, Location latLonTwo) {
        if (latLonOne.equals(latLonTwo)) {
            return 0;
        } else {
            double theta = latLonOne.getLongitude() - latLonTwo.getLongitude();
            double dist = Math.sin(Math.toRadians(latLonOne.getLatitude())) * Math.sin(Math.toRadians(latLonTwo.getLatitude()))
                    + Math.cos(Math.toRadians(latLonOne.getLatitude())) * Math.cos(Math.toRadians(latLonTwo.getLatitude()))
                    * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;  // by default: miles
            dist = dist * 1.609344 * 1000;     // covert miles to m
            return dist;
        }
    }
}
