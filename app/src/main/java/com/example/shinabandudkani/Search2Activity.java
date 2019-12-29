package com.example.shinabandudkani;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shinabandudkani.ArabicLayout.ListEStateArabicActivity;
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.KurdishLayout.ListEStateKurdishActivity;
import com.example.shinabandudkani.ServiceLayer.AmlakApiService;
import com.example.shinabandudkani.ServiceLayer.SearchApiService;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;
import com.example.shinabandudkani.ViewModel.AmlakCategory;

public class Search2Activity extends AppCompatActivity {

    Button btnsearch;
    EditText txtsearch,txtmetrazmin,txtmetrazmax,txtmincostbuy,txtmaxcostbuy,txtmincostrent,txtmaxcostrent;
    Spinner txttypeland,spncostbuy,spncostrent;
    TextView txtbuy,txtrent,txtsearchchangetext,txtsizeofcostchange,txtchangelangtypeland;
    LinearLayout llrent;
    private String[] typeland_number={"all","مزرعه","مسکونی - تجاری","صنعتی","ویلا"};
    private String[] typeland_numberku={"all","مەزرەعە","نیشتەجێبوون – بازرگانی","پیشەسازی","ڤیلا"};
    private String[] typeland_numberar={"all","مزرعة","سكني – تجاري","صناعة","فيلله"};
    private String[] typeland_numberen={"all","Farm","Residential Commercial","Industrial","Villa"};
    private String[] typeland_numberid={"1","2","3","4","5"};
    private String[] typepay_number={"Dollar","Dinar"};
    private String typeland="1";
    private String etype,ptype="Dollar";
    Integer id,type;
    SharePrefrence share;
    SaveSetting svset;
    SearchApiService searchapi;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        btnsearch=(Button)findViewById(R.id.btnsearchfinal);
        txtsearch=(EditText)findViewById(R.id.txtsearch);
        txtmetrazmin=(EditText)findViewById(R.id.txtmetrmin);
        txtmetrazmax=(EditText)findViewById(R.id.txtmetrmax);
        txttypeland=(Spinner)findViewById(R.id.txttypeland);
        txtmincostbuy=(EditText)findViewById(R.id.txtmincostbuy);
        txtmaxcostbuy=(EditText)findViewById(R.id.txtmaxcostbuy);
        txtmincostrent=(EditText)findViewById(R.id.txtmincostrent);
        txtmaxcostrent=(EditText)findViewById(R.id.txtmaxcostrent);
        txtbuy=(TextView)findViewById(R.id.lbltextbuycost);
        txtrent=(TextView)findViewById(R.id.lbltextrentcost);
        llrent=(LinearLayout)findViewById(R.id.lblalltxtboxrent);
        spncostbuy=(Spinner)findViewById(R.id.spnlistcostbuy);
        spncostrent=(Spinner)findViewById(R.id.spnlistcostrent);
        txtsearchchangetext=(TextView)findViewById(R.id.txtsearchchangelang);
        txtsizeofcostchange=(TextView)findViewById(R.id.txtsizeofhousechangelang);
        txtchangelangtypeland=(TextView)findViewById(R.id.txtchangelantypeland);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/KMIDYA.TTF");
        txtsearch.setTypeface(typeface);
        txtmetrazmax.setTypeface(typeface);
        txtmetrazmin.setTypeface(typeface);
        txtmincostbuy.setTypeface(typeface);
        txtmaxcostbuy.setTypeface(typeface);
        txtmincostrent.setTypeface(typeface);
        txtmaxcostrent.setTypeface(typeface);
        txtsearchchangetext.setTypeface(typeface);


        id = getIntent().getExtras().getInt("id");
        type = getIntent().getExtras().getInt("type");

        share=new SharePrefrence(this);
        svset=new SaveSetting();
        svset=share.getUser();

        ArrayAdapter aa = null;
        if (svset.getLanguage().equals("en")){
             aa = new ArrayAdapter(Search2Activity.this,android.R.layout.simple_spinner_item,typeland_numberen);

        }else if (svset.getLanguage().equals("ku")){
             aa = new ArrayAdapter(Search2Activity.this,android.R.layout.simple_spinner_item,typeland_numberku);

        }else if (svset.getLanguage().equals("ar")){
             aa = new ArrayAdapter(Search2Activity.this,android.R.layout.simple_spinner_item,typeland_numberar);

        }
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txttypeland.setAdapter(aa);

        ArrayAdapter aa1 = new ArrayAdapter(Search2Activity.this,android.R.layout.simple_spinner_item,typepay_number);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spncostbuy.setAdapter(aa1);
        spncostrent.setAdapter(aa1);


        txttypeland.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeland=typeland_numberid[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spncostbuy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etype=typepay_number[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spncostrent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ptype=typepay_number[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        if (svset.getLanguage().equals("ku")){
            txtsearchchangetext.setText("گەڕان");
            txtmincostrent.setHint("لە");
            txtmincostbuy.setHint("لە");
            txtmaxcostrent.setHint("بۆ");
            txtmaxcostbuy.setHint("بۆ");
            txtmetrazmin.setHint("لە");
            txtmetrazmax.setHint("بۆ");
            if (type==0){
                llrent.setVisibility(View.INVISIBLE);
                txtrent.setVisibility(View.INVISIBLE);
                txtbuy.setText("نرخی فرۆش");
            }else {
                txtbuy.setText("نرخی کرێ");
                txtrent.setText("نرخی کرێی پێشەکی");
            }
            txtsizeofcostchange.setText("ڕووبەر");
            txtchangelangtypeland.setText("جۆری مولک");
            btnsearch.setText("گەڕان");
        }
        else if (svset.getLanguage().equals("ar")){
            txtsearchchangetext.setText("بحث");
            txtmincostrent.setHint("من");
            txtmincostbuy.setHint("من");
            txtmaxcostrent.setHint("إلى");
            txtmaxcostbuy.setHint("إلى");
            txtmetrazmin.setHint("من");
            txtmetrazmax.setHint("إلى");
            if (type==0){
                llrent.setVisibility(View.INVISIBLE);
                txtrent.setVisibility(View.INVISIBLE);
                txtbuy.setText("سعر البيع");
            }else {
                txtbuy.setText("سعر استئجار");
                txtrent.setText("سعر الأجرة سلفا");
            }
            txtsizeofcostchange.setText("المساحة");
            txtchangelangtypeland.setText("نوع الاستخدام");
            btnsearch.setText("بحث");
        }
        else if (svset.getLanguage().equals("en")){
            if (type==0){
                llrent.setVisibility(View.INVISIBLE);
                txtrent.setVisibility(View.INVISIBLE);
                txtbuy.setText("Range of Buy Cost");
            }else {
                txtbuy.setText("Range of Rent Cost");
                txtrent.setText("Range of Pishpardakht Cost");
            }
        }

        searchapi=new SearchApiService(this);


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (svset.getLanguage().toString().equals("en")){
                    i = new Intent(Search2Activity.this, ListEStateActivity.class);
                }else if (svset.getLanguage().toString().equals("ku")){
                    i = new Intent(Search2Activity.this, ListEStateKurdishActivity.class);
                }else if (svset.getLanguage().toString().equals("ar")){
                    i = new Intent(Search2Activity.this, ListEStateArabicActivity.class);
                }
                if (type==0){
                    i.putExtra("lang",svset.getLanguage().toString());
                    i.putExtra("type",type);
                    i.putExtra("id",id);
                    i.putExtra("text",txtsearch.getText().toString());
                    i.putExtra("minmetr",txtmetrazmin.getText().toString());
                    i.putExtra("maxmetr",txtmetrazmax.getText().toString());
                    i.putExtra("typeland",typeland);
                    i.putExtra("emincost",txtmincostbuy.getText().toString());
                    i.putExtra("emaxcost",txtmaxcostbuy.getText().toString());
                    i.putExtra("pmincost","");
                    i.putExtra("pmaxcost","");
                    i.putExtra("etype",etype);
                    i.putExtra("ptype",ptype);
                    i.putExtra("camefrom",1);
                    i.putExtra("whichsearch",2);
                    startActivity(i);
                    finish();


                }else {
                    i.putExtra("lang",svset.getLanguage().toString());
                    i.putExtra("type",type);
                    i.putExtra("id",id);
                    i.putExtra("text",txtsearch.getText().toString());
                    i.putExtra("minmetr",txtmetrazmin.getText().toString());
                    i.putExtra("maxmetr",txtmetrazmax.getText().toString());
                    i.putExtra("typeland",typeland);
                    i.putExtra("emincost",txtmincostbuy.getText().toString());
                    i.putExtra("emaxcost",txtmaxcostbuy.getText().toString());
                    i.putExtra("pmincost",txtmincostrent.getText().toString());
                    i.putExtra("pmaxcost",txtmaxcostrent.getText().toString());
                    i.putExtra("etype",etype);
                    i.putExtra("ptype",ptype);
                    i.putExtra("camefrom",1);
                    i.putExtra("whichsearch",2);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
