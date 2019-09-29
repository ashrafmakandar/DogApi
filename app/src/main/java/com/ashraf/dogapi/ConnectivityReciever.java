package com.ashraf.dogapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityReciever extends BroadcastReceiver {
    public static   ConnectivityListener connectivityListener;


    public ConnectivityReciever() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        boolean isConnected= networkInfo!=null&&networkInfo.isConnectedOrConnecting();
        if(connectivityListener!=null)
        {
            connectivityListener.onNetworkConnectionChanged(isConnected);
        }

    }
    public static  boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager) Myapplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null&&networkInfo.isConnectedOrConnecting();
    }

  public   interface  ConnectivityListener{

        void onNetworkConnectionChanged(boolean isConnected);

    }
}
