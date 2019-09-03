package com.fcv.expressCourier.priceCalculator;

import java.io.IOException;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MatrixAPI2{

    private static final String API_KEY = APIKey.key;
  OkHttpClient client = new OkHttpClient();

  public String run(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    Response response = client.newCall(request).execute();
    return response.body().string();
  }

  public static void main(String[] args) throws IOException {
    MatrixAPI request = new MatrixAPI();
    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=1622 Greenhaven Dr, Richardson&destinations=Church in Dallas&language=fr-FR&key="+API_KEY ;
    String response = request.run(url_request);
    System.out.println(response);
  }
}