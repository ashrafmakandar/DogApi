package com.ashraf.dogapi;

import android.app.Application;

import com.ashraf.dogapi.di.appcompenent;
import com.ashraf.dogapi.remote.breedsapiservice;
import com.google.gson.Gson;

import retrofit2.Retrofit;

public class Myapplication extends Application {
    private static Myapplication mInstance;
    breedsapiservice breedsapiservice;

appcompenent appcompenents;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;


    }
    public static synchronized Myapplication getInstance()
    {
        return mInstance;
    }
    public void setConnectivityListener(ConnectivityReciever.ConnectivityListener listener)
    {
        ConnectivityReciever.connectivityListener = listener;
    }


    public com.ashraf.dogapi.remote.breedsapiservice getBreedsapiservice(Gson gson, Retrofit retrofit) {
        breedsapiservice= new breedsapiservice(gson,retrofit);
        return breedsapiservice;
    }
}
