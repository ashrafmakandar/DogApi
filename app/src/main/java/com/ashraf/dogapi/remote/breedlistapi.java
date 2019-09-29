package com.ashraf.dogapi.remote;


import com.ashraf.dogapi.model.hound;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface breedlistapi {


    @GET("breed/hound/images")
    //Call<hound> getbreeds();
    Observable<hound> getbreeds();
}
