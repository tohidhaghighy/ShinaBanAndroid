package com.example.shinabandudkani;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shinabandudkani.DomainLayer.InfoApp;
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.ServiceLayer.AmlakApiService;
import com.example.shinabandudkani.ServiceLayer.TextApiService;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;
import com.squareup.picasso.Picasso;

public class SplashActivity extends AppCompatActivity {

    SharePrefrence share;
    AmlakApiService amlakapi;
    TextApiService textApiService;
    ImageView imgsplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imgsplash=(ImageView)findViewById(R.id.splashimageview);


        if (check_internet_connection()){
            amlakapi=new AmlakApiService(this);
            textApiService=new TextApiService(this);
            textApiService.gettexts(new TextApiService.OnTextReceived() {
                @Override
                public void onReceived(InfoApp infoapp) {
                    Picasso.with(SplashActivity.this).load(infoapp.getIcon()).into(imgsplash);
                }
            });

            amlakapi.getlicense(new AmlakApiService.Onlinceseactive() {
                @Override
                public void onReceived(String active) {
                    if (active.equals("0")){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(SplashActivity.this, CityActivity.class);
                                startActivity(i);
                                finish();

                            }
                        },5000);
                    }else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "License Error",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Internet not Connected",
                    Toast.LENGTH_SHORT);

            toast.show();
        }


    }

    public boolean check_internet_connection(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
        return connected;
    }

}
