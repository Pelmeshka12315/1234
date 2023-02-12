package com.example.a100;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface PID {
    @GET("pet/8642")
    Call<Jivotnoe> getData();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://petstore.swagger.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
