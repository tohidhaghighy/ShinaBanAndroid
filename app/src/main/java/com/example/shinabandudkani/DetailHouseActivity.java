package com.example.shinabandudkani;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.shinabandudkani.AdaptorLayer.EstateAdaptor;
import com.example.shinabandudkani.AdaptorLayer.PropertyAdaptor;
import com.example.shinabandudkani.ArabicLayout.DetailHouseArabicActivity;
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.ServiceLayer.AmlakApiService;
import com.example.shinabandudkani.ServiceLayer.DataFakeGeneraTor;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;
import com.example.shinabandudkani.ViewModel.AmlakDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ir.apend.slider.model.Slide;
import ir.apend.slider.ui.Slider;

public class DetailHouseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AmlakApiService amlakapi;
    private TextView txttitle,txtdescription,txtusernameinfo,txtemailinfo,txtboardnameinfo,txttellinfo;
    SharePrefrence share;
    SaveSetting svset;
    String callnum="",mailnum="";
    String lat,lng="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_house);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        share=new SharePrefrence(this);
        svset=new SaveSetting();
        svset=share.getUser();

        amlakapi=new AmlakApiService(this);
        final Integer id = getIntent().getExtras().getInt("id");

        txttitle=(TextView)findViewById(R.id.txttitleamlak);
        txtdescription=(TextView)findViewById(R.id.txtdescriptionamlak);
        txtusernameinfo=(TextView)findViewById(R.id.txtiusenameinfo);
        txtemailinfo=(TextView)findViewById(R.id.txtemailinfo);
        txtboardnameinfo=(TextView)findViewById(R.id.txtboardnameinfo);
        txttellinfo=(TextView)findViewById(R.id.txttellinfo);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+callnum));
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("Demo application", "Failed to invoke call", e);
                }
            }
        });

        FloatingActionButton fabmail = findViewById(R.id.fabemail);
        fabmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri data = Uri.parse("mailto:"+mailnum+"?subject=ارسال ایمیل به فروشنده&body=");
                    intent.setData(data);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("Demo application", "Failed to invoke call", e);
                }
            }
        });

        FloatingActionButton fabloc=findViewById(R.id.fabeloc);
        fabloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%s,%s?q=%s,%s", lat, lng, lat, lng);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/KMIDYA.TTF");
        txttitle.setTypeface(typeface);
        txtdescription.setTypeface(typeface);
        txttellinfo.setTypeface(typeface);
        txtusernameinfo.setTypeface(typeface);
        txtemailinfo.setTypeface(typeface);
        txtboardnameinfo.setTypeface(typeface);

        amlakapi.getamlakdetail("en", id,svset.getCityId(), new AmlakApiService.OnAmlakDetailReceived() {
            @Override
            public void onReceived(AmlakDetail amlakdetail) {
                txttitle.setText(amlakdetail.getTittle().toString());
                txtdescription.setText(amlakdetail.getDescription().toString());
                txtusernameinfo.setText("Name : "+amlakdetail.getName().toString());
                txtemailinfo.setText("Email : "+amlakdetail.getEmail().toString());
                mailnum=amlakdetail.getEmail().toString();
                txtboardnameinfo.setText("Board name : "+amlakdetail.getBoard_Name().toString());
                callnum=amlakdetail.getTell().toString();

                lat=amlakdetail.getLat();
                lng=amlakdetail.getLng();

                Slider slider = findViewById(R.id.slider);
                final List<Slide> slideList = new ArrayList<>();


                for (int i = 0; i < amlakdetail.getAllimages().size(); i++) {
                    slideList.add(new Slide(i,amlakdetail.getAllimages().get(i).toString() , getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
                }


                if (slideList.size()==0){
                    slideList.add(new Slide(1,"http://shinaban.com/images/uis/icons/roof.png" , getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
                }

                slider.addSlides(slideList);

                RecyclerView rv=(RecyclerView)findViewById(R.id.mortabet_product);
                EstateAdaptor pa=new EstateAdaptor(DetailHouseActivity.this, amlakdetail.getAllamlak());
                rv.setLayoutManager(new LinearLayoutManager(DetailHouseActivity.this,LinearLayoutManager.VERTICAL,false));
                rv.setAdapter(pa);

                RecyclerView rvproprety=(RecyclerView)findViewById(R.id.property_recycle);
                PropertyAdaptor propadaptor=new PropertyAdaptor(DetailHouseActivity.this, amlakdetail.getAllproperty());
                rvproprety.setLayoutManager(new LinearLayoutManager(DetailHouseActivity.this,LinearLayoutManager.VERTICAL,false));
                rvproprety.setAdapter(propadaptor);
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_house, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(DetailHouseActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }else if (id == R.id.nav_city) {
            Intent i = new Intent(DetailHouseActivity.this, StateCityActivity.class);
            i.putExtra("lang","en");
            startActivity(i);
            finish();

        }  else if (id == R.id.nav_tools) {
            Intent i = new Intent(DetailHouseActivity.this, CityActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_share_tele) {
            Uri uri = Uri.parse("https://t.me/websally");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.telegram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://t.me/websally")));
            }
        }else if (id==R.id.nav_share_insta){
            Uri uri = Uri.parse("https://www.instagram.com/tohid_haghighy");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/tohid_haghighy")));
            }
        }else if (id==R.id.nav_exit){
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
