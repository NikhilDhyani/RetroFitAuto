package com.example.nikhil.retrofitauto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by NIKHIL on 23-11-2017.
 */

public interface Api {

    String BaseUrl  = "https://www.simplifiedcoding.net/demos/";

    @GET("marvel")

    Call<List<PostList>> getHeros();
}
