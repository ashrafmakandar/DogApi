package com.ashraf.dogapi.remote;

import com.ashraf.dogapi.model.hound;
import com.google.gson.Gson;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class breedsapiservice {

    Gson gson;
    Retrofit retrofit;

    public breedsapiservice(Gson gson, Retrofit retrofit) {
        this.gson = gson;
        this.retrofit = retrofit;
    }

    public Observable<hound> gethounds() {

        breedlistapi breedlistapis = retrofit.create(breedlistapi.class);
        Observable<hound> g = breedlistapis.getbreeds();
        return g;
    }
}
