package com.example.saurabhkaushik.ebatespractise.rest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by saurabhkaushik on 30/11/16.
 */
public class ApiClient {
    public static final String BASE_URL = "https://api.myjson.com/bins/2ukm9";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
