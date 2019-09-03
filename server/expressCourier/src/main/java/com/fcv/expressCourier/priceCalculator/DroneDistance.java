package com.fcv.expressCourier.priceCalculator;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DroneDistance {
	public static void main(String[] args) throws IOException {
	OkHttpClient client = new OkHttpClient();

	Request request = new Request.Builder()
			.url("https://distanceto.p.rapidapi.com/get?car=false&foot=false&route=%5B%7B%22t%22%3A%22TXL%22%7D%2C%7B%22t%22%3A%22Hamburg%22%7D%5D")
		.get()
		.addHeader("x-rapidapi-host", "distanceto.p.rapidapi.com")
		.addHeader("x-rapidapi-key", "47c93fabafmsh28e9bf185210f2fp1e7157jsnb557b8b51b7f")
		.build();

	Response response = client.newCall(request).execute();
	System.out.println(response);
}
}