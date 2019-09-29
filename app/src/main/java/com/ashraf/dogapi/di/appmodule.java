package com.ashraf.dogapi.di;


import com.ashraf.dogapi.remote.breedsapiservice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class appmodule {



@Provides
Gson providegson()
{

    Gson gson= new GsonBuilder().setLenient().create();
    return gson;
}
@Provides
Retrofit provideretro()
{

  Retrofit  retrofits = new Retrofit.Builder().baseUrl("https://dog.ceo/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .addConverterFactory(GsonConverterFactory.create()).build();

return retrofits;
}
@Provides
    breedsapiservice getbreedsservice(Gson gson, Retrofit retrofit)
{
    breedsapiservice breedsapiservices= new breedsapiservice(gson,retrofit);
    return  breedsapiservices;
}
}
