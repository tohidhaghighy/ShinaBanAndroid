package com.example.shinabandudkani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shinabandudkani.AdaptorLayer.CityAdaptor;
import com.example.shinabandudkani.DomainLayer.CityState;
import com.example.shinabandudkani.DomainLayer.InfoApp;
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.ServiceLayer.AmlakApiService;
import com.example.shinabandudkani.ServiceLayer.DataFakeGeneraTor;
import com.example.shinabandudkani.ServiceLayer.TextApiService;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CitySelectorActivity extends AppCompatActivity {

    SharePrefrence share;
    AmlakApiService amlakapi;
    SaveSetting saveset;
    ImageView imgheader;
    TextView txtheader,txtfooter;
    TextApiService textApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_selector);

        imgheader=(ImageView)findViewById(R.id.imgheadericon);
        txtheader=(TextView)findViewById(R.id.txtheadercity);
        txtfooter=(TextView)findViewById(R.id.txtfootercity);


        amlakapi=new AmlakApiService(this);
        final String lang = getIntent().getExtras().getString("lang");
        final Integer id = getIntent().getExtras().getInt("id");

        Log.e("lang",lang);

        textApiService=new TextApiService(this);
        textApiService.gettexts(new TextApiService.OnTextReceived() {
            @Override
            public void onReceived(InfoApp infoapp) {
                Picasso.with(CitySelectorActivity.this).load(infoapp.getIcon()).into(imgheader);
                if (lang.equals("en")){
                    txtheader.setText(infoapp.getChoiseCityen());
                    txtfooter.setText(infoapp.getFooteren());
                }else if(lang.equals("ku")){
                    txtheader.setText(infoapp.getChoiseCityku());
                    txtfooter.setText(infoapp.getFooterku());
                }else if (lang.equals("ar")){
                    txtheader.setText(infoapp.getChoiseCityar());
                    txtfooter.setText(infoapp.getFooterar());
                }
            }
        });

        amlakapi.getcity(id, lang, new AmlakApiService.OnACityStateReceived() {
            @Override
            public void onReceived(List<CityState> citystate) {
                share=new SharePrefrence(CitySelectorActivity.this);
                RecyclerView rv=(RecyclerView)findViewById(R.id.city_recycle);
                CityAdaptor pa=new CityAdaptor(CitySelectorActivity.this, citystate,lang);
                rv.setLayoutManager(new LinearLayoutManager(CitySelectorActivity.this,LinearLayoutManager.VERTICAL,false));
                rv.setAdapter(pa);
            }
        });



    }
}
