package com.ashraf.dogapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ashraf.dogapi.adapter.dogadapter;
import com.ashraf.dogapi.model.hound;
import com.ashraf.dogapi.remote.breedlistapi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    breedlistapi breedlistapis;
    ProgressDialog progressBar;
    Retrofit retrofits;
    List<String> messages;
    com.ashraf.dogapi.adapter.dogadapter dogadapters;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recView);
        retrofits = new Retrofit.Builder().baseUrl("https://dog.ceo/api/").addConverterFactory(GsonConverterFactory.create()).build();
        messages = new ArrayList<>();
        breedlistapis = retrofits.create(breedlistapi.class);
        Call<hound> g = breedlistapis.getbreeds();
        g.enqueue(new Callback<hound>() {
            @Override
            public void onResponse(Call<hound> call, Response<hound> response) {



                    dogadapters = new dogadapter(getApplicationContext(), response.body().getMessage());
                    recyclerView.setAdapter(dogadapters);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setHasFixedSize(true);


            }

            @Override
            public void onFailure(Call<hound> call, Throwable t) {

            }
        });

    }
}
