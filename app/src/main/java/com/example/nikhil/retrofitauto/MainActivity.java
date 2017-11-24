package com.example.nikhil.retrofitauto;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

      ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      listView = (ListView) findViewById(R.id.listViewHeroes);

        //Retrofit Instance

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Api.BaseUrl).addConverterFactory(GsonConverterFactory.create())
                            .build();

        Api api = retrofit.create(Api.class);

        Call <List <PostList>> call = api.getHeros();

        call.enqueue(new Callback<List<PostList>>() {
            @Override
            public void onResponse(Call<List<PostList>> call, Response<List<PostList>> response) {

                List <PostList> heroes = response.body();

              //Creating an string array for listview

               String[] heroesname = new String[heroes.size()];

                //looping through all the heroes and inserting the names inside the string array

                for (int i=0; i<heroes.size();i++)
                {
                    heroesname[i] =  heroes.get(i).getName();
                    Log.d("herosName",heroesname[i]);

                }
                Log.d("HERONAME",heroesname[0]);

                //displaying the string array into listview

                listView.setAdapter(
                        new ArrayAdapter<String>
                                (getApplicationContext(),android.R.layout.simple_list_item_1,
                                        heroesname
                                )

                );


            }

            @Override
            public void onFailure(Call<List<PostList>> call, Throwable t) {

            }
        });
    }
}
