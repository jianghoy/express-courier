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
        JSONObject jsonRespRouteDistance = new JSONObject(response).getJSONArray("rows").getJSONObject(0)
                .getJSONArray("elements").getJSONObject(0).getJSONObject("distance");
        String value = jsonRespRouteDistance.get("value").toString();

        return Integer.parseInt(value) * 0.0006;
    }

    public static void main(String[] args){

        MatrixAPI matrix = new MatrixAPI();
        System.out.println(matrix.carPrice("UTD", "Church in Dallas"));

    }
}