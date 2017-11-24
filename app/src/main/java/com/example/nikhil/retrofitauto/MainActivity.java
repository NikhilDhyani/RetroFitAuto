package com.example.nikhil.retrofitauto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Api.BaseUrl).addConverterFactory(GsonConverterFactory.create())
                            .build();

        Api api = retrofit.create(Api.class);

        Call <List <PostList>> call = api.getHeros();

        call.enqueue(new Callback<List<PostList>>() {
            @Override
            public void onResponse(Call<List<PostList>> call, Response<List<PostList>> response) {

                List <PostList> heroes = response.body();

                for (PostList h : heroes )
                {
                    Log.d("name",h.getName());
                    Log.d("realname",h.getRealname());

                }


            }

            @Override
            public void onFailure(Call<List<PostList>> call, Throwable t) {

            }
        });
    }
}
