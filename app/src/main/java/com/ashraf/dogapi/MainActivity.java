package com.ashraf.dogapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashraf.dogapi.adapter.dogadapter;
import com.ashraf.dogapi.model.hound;
import com.ashraf.dogapi.remote.breedlistapi;
import com.ashraf.dogapi.remote.breedsapiservice;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ConnectivityReciever.ConnectivityListener {
    breedlistapi breedlistapis;
    Retrofit retrofits;
    List<String> messages;
    com.ashraf.dogapi.adapter.dogadapter dogadapters;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recView);
        checkconnection();
        Gson gson= new GsonBuilder().setLenient().create();
        retrofits = new Retrofit.Builder().baseUrl("https://dog.ceo/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .addConverterFactory(GsonConverterFactory.create()).build();
        messages = new ArrayList<>();
        Myapplication myapplication= (Myapplication) getApplication();

        breedsapiservice  breedsapiservices=myapplication.getBreedsapiservice(gson,retrofits);
        breedsapiservices.gethounds().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<hound>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(hound hound) {
                        hound.getMessage();
                        dogadapters = new dogadapter(getApplicationContext(), hound.getMessage());
                        recyclerView.setAdapter(dogadapters);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setHasFixedSize(true);


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void checkconnection() {

        boolean isConnected = ConnectivityReciever.isConnected();
        showSnack(isConnected);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }

    private void showSnack(boolean isConnected) {

        String message;

        if (isConnected) {
            message = "internet connection is on";


        } else {
            message = "internet is off";

        }
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Myapplication.getInstance().setConnectivityListener(this);
    }
}
