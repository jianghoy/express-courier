package com.fcv.expressCourier.priceCalculator;

import java.io.IOException;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MatrixAPI{

    private static final String API_KEY = APIKey.key;

  OkHttpClient client = new OkHttpClient();

  public String run(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    Response response = client.newCall(request).execute();
    return response.body().string();
   
  }
public double price(String origin, String destination) {
	
    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origin+"&destinations="+destination+"&departure_time=now&key="+API_KEY   ;
    String response;
	try {
		response = run(url_request);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -1;
	}
    //String output = response(["data"]["row"][0]["element"]["distance"]["text"]);
    JSONObject jsonRespRouteDistance = new JSONObject(response)
            .getJSONArray("rows")
            .getJSONObject(0)
            .getJSONArray ("elements")
            .getJSONObject(0)
            .getJSONObject("distance");

    JSONObject jsonRespRouteTime = new JSONObject(response)
            .getJSONArray("rows")
            .getJSONObject(0)
            .getJSONArray ("elements")
            .getJSONObject(0)
            .getJSONObject("duration");
String distance = jsonRespRouteDistance.get("text").toString();
String value = jsonRespRouteDistance.get("value").toString();

double price = Integer.parseInt(value)*0.0006;
return price;
}
public static void main(String[] args) throws IOException {
//    MatrixAPI request = new MatrixAPI();
//    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=UTD&destinations=Church in Dallas&departure_time=now&key="+API_KEY   ;
//    String response = request.run(url_request);
//    //String output = response(["data"]["row"][0]["element"]["distance"]["text"]);
//    JSONObject jsonRespRouteDistance = new JSONObject(response)
//            .getJSONArray("rows")
//            .getJSONObject(0)
//            .getJSONArray ("elements")
//            .getJSONObject(0)
//            .getJSONObject("distance");
//
//    JSONObject jsonRespRouteTime = new JSONObject(response)
//            .getJSONArray("rows")
//            .getJSONObject(0)
//            .getJSONArray ("elements")
//            .getJSONObject(0)
//            .getJSONObject("duration");
//String distance = jsonRespRouteDistance.get("text").toString();
//String value = jsonRespRouteTime.get("text").toString();
//
//    System.out.println(distance);
	MatrixAPI matrix = new MatrixAPI();
    System.out.println(matrix.price("UTD","Church in Dallas"));
//    System.out.println(response);
//    
}
}