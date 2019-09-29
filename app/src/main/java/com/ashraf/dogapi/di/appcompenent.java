package com.ashraf.dogapi.di;

import com.ashraf.dogapi.remote.breedsapiservice;

import dagger.Component;

@Component(modules = {appmodule.class})
public interface appcompenent {
breedsapiservice getbreeds();

}
