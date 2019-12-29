package com.example.shinabandudkani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shinabandudkani.ArabicLayout.MainArabicActivity;
import com.example.shinabandudkani.DomainLayer.InfoApp;
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.KurdishLayout.MainKurdishActivity;
import com.example.shinabandudkani.ServiceLayer.TextApiService;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;
import com.squareup.picasso.Picasso;

public class CityActivity extends AppCompatActivity {

    private ImageView imgku,imgar,imgen,imgicon;
    private SharePrefrence usermanager;
    SharePrefrence share;
    SaveSetting svuser;
    SaveSetting newsvuser;
    private TextView txtku,txtar,txten,txtheader,txtfooter;
    TextApiService textApiService;

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        imgku=(ImageView)findViewById(R.id.btnkurdish);
        imgar=(ImageView)findViewById(R.id.btnarabic);
        imgen=(ImageView)findViewById(R.id.btnenglish);

        txtku=(TextView)findViewById(R.id.txtkurdish);
        txtar=(TextView)findViewById(R.id.txtarabic);
        txten=(TextView)findViewById(R.id.txtenglish);
        txtheader=(TextView)findViewById(R.id.txtheadertext);
        txtfooter=(TextView)findViewById(R.id.txtfooter);
        imgicon=(ImageView)findViewById(R.id.imgheadericon);

        textApiService=new TextApiService(this);
        textApiService.gettexts(new TextApiService.OnTextReceived() {
            @Override
            public void onReceived(InfoApp infoapp) {
                Picasso.with(CityActivity.this).load(infoapp.getIcon()).into(imgicon);
                Log.e("ku",infoapp.getIconku());
                Picasso.with(CityActivity.this).load("http://shinaban.com/"+infoapp.getIconku()).placeholder(R.drawable.ku).into(imgku);
                Picasso.with(CityActivity.this).load("http://shinaban.com/"+infoapp.getIconar()).placeholder(R.drawable.ar).into(imgar);
                Picasso.with(CityActivity.this).load("http://shinaban.com/"+infoapp.getIconen()).placeholder(R.drawable.en).into(imgen);
                txtku.setText(infoapp.getTitleku());
                txtar.setText(infoapp.getTitlear());
                txten.setText(infoapp.getTitleen());
                txtheader.setText(infoapp.getDescriptionen());
                txtfooter.setText(infoapp.getFooteren());
            }
        });


        usermanager=new SharePrefrence(this);
        svuser=new SaveSetting();
        share=new SharePrefrence(this);
        try{
            svuser=share.getUser();
        }catch (Exception e){
            svuser.setCityId(0);
            svuser.setFirsttime(0);
            svuser.setLanguage("");
            share.saveUser(svuser);
            svuser=share.getUser();
        }


        imgku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (svuser.getCityId()==0){
                    i = new Intent(CityActivity.this, StateCityActivity.class);
                    i.putExtra("lang","ku");
                    startActivity(i);

                    finish();
                }else {
                    i = new Intent(CityActivity.this, MainKurdishActivity.class);
                    i.putExtra("lang","ku");
                    startActivity(i);
                    newsvuser=new SaveSetting();
                    newsvuser.setCityId(svuser.getCityId());
                    newsvuser.setFirsttime(svuser.getFirsttime());
                    newsvuser.setLanguage("ku");
                    share.saveUser(newsvuser);
                    finish();
                }

            }
        });
        imgar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (svuser.getCityId()==0){
                    i = new Intent(CityActivity.this, StateCityActivity.class);
                    i.putExtra("lang","ar");
                    startActivity(i);
                    finish();
                }else {
                    i = new Intent(CityActivity.this, MainArabicActivity.class);
                    i.putExtra("lang","ar");
                    startActivity(i);
                    newsvuser=new SaveSetting();
                    newsvuser.setCityId(svuser.getCityId());
                    newsvuser.setFirsttime(svuser.getFirsttime());
                    newsvuser.setLanguage("ar");
                    share.saveUser(newsvuser);
                    finish();
                }

            }
        });

        imgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (svuser.getCityId()==0){
                    i = new Intent(CityActivity.this, StateCityActivity.class);
                    i.putExtra("lang","en");
                    startActivity(i);
                    finish();
                }else {
                    i = new Intent(CityActivity.this, MainActivity.class);
                    i.putExtra("lang","en");
                    startActivity(i);
                    newsvuser=new SaveSetting();
                    newsvuser.setCityId(svuser.getCityId());
                    newsvuser.setFirsttime(svuser.getFirsttime());
                    newsvuser.setLanguage("en");
                    share.saveUser(newsvuser);
                    finish();
                }

            }
        });

    }
}
