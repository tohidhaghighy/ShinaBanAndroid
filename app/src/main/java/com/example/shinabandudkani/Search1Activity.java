package com.example.shinabandudkani;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
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

import com.example.shinabandudkani.AdaptorLayer.CityAdaptor;
import com.example.shinabandudkani.AdaptorLayer.StateAdaptor;
import com.example.shinabandudkani.ArabicLayout.ListEStateArabicActivity;
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.KurdishLayout.ListEStateKurdishActivity;
import com.example.shinabandudkani.ServiceLayer.AmlakApiService;
import com.example.shinabandudkani.ServiceLayer.DataFakeGeneraTor;
import com.example.shinabandudkani.ServiceLayer.SearchApiService;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;
import com.example.shinabandudkani.ViewModel.AmlakCategory;

public class Search1Activity extends AppCompatActivity {

    Button btnsearch;
    EditText txtsearch,txtmetrazmin,txtmetrazmax,txtroomcount,txtmincostbuy,txtmaxcostbuy,txtmincostrent,txtmaxcostrent,txtminkhadamat,txtmaxkhadamat;
    TextView txtbuy,txtrent,txtsearchchangetext,txtsizeofcostchange,txtcostofkhadamatchange,txtviewroomcountchangetext;
    LinearLayout llrent;
    Spinner spnroomcount,spncostbuy,spncostrent,spncostkhadamat;
    private String[] roomcount_number={"all","1","2","3","4","5","5+"};
    String roomcountselected="all";
    private String[] typepay_number={"Dollar","Dinar"};
    String etype,ptype,khtype="Dollar";
    Integer id,type=0;
    SharePrefrence share;
    SaveSetting svset;
    SearchApiService searchapi;
    Intent listi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search1);
        btnsearch=(Button)findViewById(R.id.btnsearchfinal);
        txtsearch=(EditText)findViewById(R.id.txtsearch);
        txtmetrazmin=(EditText)findViewById(R.id.txtmetrmin);
        txtmetrazmax=(EditText)findViewById(R.id.txtmetrmax);
        spnroomcount=(Spinner)findViewById(R.id.txtroomcount);
        txtmincostbuy=(EditText)findViewById(R.id.txtmincostbuy);
        txtmaxcostbuy=(EditText)findViewById(R.id.txtmaxcostbuy);
        txtmincostrent=(EditText)findViewById(R.id.txtmincostrent);
        txtmaxcostrent=(EditText)findViewById(R.id.txtmaxcostrent);
        txtbuy=(TextView)findViewById(R.id.lbltextbuycost);
        txtrent=(TextView)findViewById(R.id.lbltextrentcost);
        llrent=(LinearLayout)findViewById(R.id.lblalltxtboxrent);
        spncostbuy=(Spinner)findViewById(R.id.spnlistcostbuy);
        spncostrent=(Spinner)findViewById(R.id.spnlistcostrent);
        spncostkhadamat=(Spinner)findViewById(R.id.spnlistcostkhadamat);
        txtminkhadamat=(EditText)findViewById(R.id.txtmincostkhadamat);
        txtmaxkhadamat=(EditText)findViewById(R.id.txtmaxcostkhadamat);
        txtsearchchangetext=(TextView)findViewById(R.id.txtsearchchangelang);
        txtcostofkhadamatchange=(TextView)findViewById(R.id.txtrangeofkhadamatchangelang);
        txtsizeofcostchange=(TextView)findViewById(R.id.txtsizeofhousechangelang);
        txtviewroomcountchangetext=(TextView)findViewById(R.id.txtviewroomcountchangetext);


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


        ArrayAdapter aa = new ArrayAdapter(Search1Activity.this,android.R.layout.simple_spinner_item,roomcount_number);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnroomcount.setAdapter(aa);

        ArrayAdapter aa1 = new ArrayAdapter(Search1Activity.this,android.R.layout.simple_spinner_item,typepay_number);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spncostbuy.setAdapter(aa1);
        spncostrent.setAdapter(aa1);
        spncostkhadamat.setAdapter(aa1);


        spnroomcount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roomcountselected=roomcount_number[position];
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

        spncostkhadamat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                khtype=typepay_number[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        share=new SharePrefrence(this);
        svset=new SaveSetting();
        svset=share.getUser();
        if (svset.getLanguage().equals("ku")){
            txtsearchchangetext.setText("گەڕان");
            txtminkhadamat.setHint("لە");
            txtmaxkhadamat.setHint("بۆ");
            txtmincostrent.setHint("لە");
            txtmincostbuy.setHint("لە");
            txtmaxcostrent.setHint("بۆ");
            txtmaxcostbuy.setHint("بۆ");
            txtmetrazmin.setHint("لە");
            txtmetrazmax.setHint("بۆ");
            txtviewroomcountchangetext.setText("ژمارەی ژوور");
            if (type==0){
                llrent.setVisibility(View.INVISIBLE);
                txtrent.setVisibility(View.INVISIBLE);
                txtbuy.setText("نرخی فرۆش");
            }else {
                txtbuy.setText("نرخی کرێ");
                txtrent.setText("نرخی کرێی پێشەکی");
            }
            txtsizeofcostchange.setText("ڕووبەر");
            txtcostofkhadamatchange.setText("تێچووی خزمەتگوزاری");
            btnsearch.setText("گەڕان");
        }
        else if (svset.getLanguage().equals("ar")){
            txtsearchchangetext.setText("بحث");
            txtminkhadamat.setHint("من");
            txtmaxkhadamat.setHint("إلى");
            txtmincostrent.setHint("من");
            txtmincostbuy.setHint("من");
            txtmaxcostrent.setHint("إلى");
            txtmaxcostbuy.setHint("إلى");
            txtmetrazmin.setHint("من");
            txtmetrazmax.setHint("إلى");
            txtviewroomcountchangetext.setText("عدد الغرف");
            if (type==0){
                llrent.setVisibility(View.INVISIBLE);
                txtrent.setVisibility(View.INVISIBLE);
                txtbuy.setText("سعر البيع");
            }else {
                txtbuy.setText("سعر استئجار");
                txtrent.setText("سعر الأجرة سلفا");
            }
            txtsizeofcostchange.setText("المساحة");
            txtcostofkhadamatchange.setText("تكلفة الخدمات");
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
                    listi = new Intent(Search1Activity.this, ListEStateActivity.class);
                }else if (svset.getLanguage().toString().equals("ku")){
                    listi = new Intent(Search1Activity.this, ListEStateKurdishActivity.class);
                }else if (svset.getLanguage().toString().equals("ar")){
                    listi = new Intent(Search1Activity.this, ListEStateArabicActivity.class);
                }
                if (type==0){

                    listi.putExtra("lang",svset.getLanguage().toString());
                    listi.putExtra("type",type);
                    listi.putExtra("id",id);
                    listi.putExtra("text",txtsearch.getText().toString());
                    listi.putExtra("minmetr",txtmetrazmin.getText().toString());
                    listi.putExtra("maxmetr",txtmetrazmax.getText().toString());
                    listi.putExtra("roomcount",roomcountselected);
                    listi.putExtra("emincost",txtmincostbuy.getText().toString());
                    listi.putExtra("emaxcost",txtmaxcostbuy.getText().toString());
                    listi.putExtra("pmincost","");
                    listi.putExtra("pmaxcost","");
                    listi.putExtra("minkhadamat",txtminkhadamat.getText().toString());
                    listi.putExtra("maxkhadamat",txtmaxkhadamat.getText().toString());
                    listi.putExtra("etype",etype);
                    listi.putExtra("ptype",ptype);
                    listi.putExtra("khtype",khtype);
                    listi.putExtra("camefrom",1);
                    listi.putExtra("whichsearch",1);
                    startActivity(listi);
                    finish();


                }else {
                    listi.putExtra("lang",svset.getLanguage().toString());
                    listi.putExtra("type",type);
                    listi.putExtra("id",id);
                    listi.putExtra("text",txtsearch.getText().toString());
                    listi.putExtra("minmetr",txtmetrazmin.getText().toString());
                    listi.putExtra("maxmetr",txtmetrazmax.getText().toString());
                    listi.putExtra("roomcount",roomcountselected);
                    listi.putExtra("emincost",txtmincostbuy.getText().toString());
                    listi.putExtra("emaxcost",txtmaxcostbuy.getText().toString());
                    listi.putExtra("pmincost",txtmincostrent.getText().toString());
                    listi.putExtra("pmaxcost",txtmaxcostrent.getText().toString());
                    listi.putExtra("minkhadamat",txtminkhadamat.getText().toString());
                    listi.putExtra("maxkhadamat",txtmaxkhadamat.getText().toString());
                    listi.putExtra("etype",etype);
                    listi.putExtra("ptype",ptype);
                    listi.putExtra("khtype",khtype);
                    listi.putExtra("camefrom",1);
                    listi.putExtra("whichsearch",1);
                    startActivity(listi);
                    finish();

                }
            }
        });
    }
}
