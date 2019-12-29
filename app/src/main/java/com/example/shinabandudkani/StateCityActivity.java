package com.example.shinabandudkani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shinabandudkani.AdaptorLayer.CityAdaptor;
import com.example.shinabandudkani.AdaptorLayer.EstateAdaptor;
import com.example.shinabandudkani.AdaptorLayer.StateAdaptor;
import com.example.shinabandudkani.DomainLayer.CityState;
import com.example.shinabandudkani.DomainLayer.InfoApp;
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.ServiceLayer.AmlakApiService;
import com.example.shinabandudkani.ServiceLayer.DataFakeGeneraTor;
import com.example.shinabandudkani.ServiceLayer.TextApiService;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StateCityActivity extends AppCompatActivity {

    AmlakApiService amlakapi;
    ImageView imgheader;
    TextApiService textApiService;
    SharePrefrence share;
    TextView txtheader,txtfooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_city);


        final String lang = getIntent().getExtras().getString("lang");

        Log.e("lang",lang);

        imgheader=(ImageView)findViewById(R.id.imgiconheader);
        txtheader=(TextView)findViewById(R.id.txtheaderstate);
        txtfooter=(TextView)findViewById(R.id.txtfooterstate);


        textApiService=new TextApiService(this);
        textApiService.gettexts(new TextApiService.OnTextReceived() {
            @Override
            public void onReceived(InfoApp infoapp) {
                Picasso.with(StateCityActivity.this).load(infoapp.getIcon()).into(imgheader);
                if (lang.equals("en")){
                    txtheader.setText(infoapp.getChoiseStateen());
                    txtfooter.setText(infoapp.getFooteren());
                }else if(lang.equals("ku")){
                    txtheader.setText(infoapp.getChoiseStateku());
                    txtfooter.setText(infoapp.getFooterku());
                }else if (lang.equals("ar")){
                    txtheader.setText(infoapp.getChoiseStatear());
                    txtfooter.setText(infoapp.getFooterar());
                }
            }
        });


        textApiService=new TextApiService(this);
        textApiService.gettexts(new TextApiService.OnTextReceived() {
            @Override
            public void onReceived(InfoApp infoapp) {
                Picasso.with(StateCityActivity.this).load(infoapp.getIcon()).placeholder(R.drawable.roof).into(imgheader);
            }
        });

        amlakapi=new AmlakApiService(this);
        amlakapi.getstate(lang, new AmlakApiService.OnACityStateReceived() {
            @Override
            public void onReceived(List<CityState> citystate) {
                RecyclerView rv=(RecyclerView)findViewById(R.id.state_recycle);
                StateAdaptor pa=new StateAdaptor(StateCityActivity.this, citystate,lang);
                rv.setLayoutManager(new LinearLayoutManager(StateCityActivity.this,LinearLayoutManager.VERTICAL,false));
                rv.setAdapter(pa);
            }
        });
    }
}
