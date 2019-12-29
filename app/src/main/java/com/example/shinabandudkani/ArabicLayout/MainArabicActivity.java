package com.example.shinabandudkani.ArabicLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
import android.widget.Toast;

import com.example.shinabandudkani.AdaptorLayer.EstateAdaptor;
import com.example.shinabandudkani.AdaptorLayer.GroupAdaptor;
import com.example.shinabandudkani.CityActivity;
import com.example.shinabandudkani.DomainLayer.InfoApp;
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.KurdishLayout.MainKurdishActivity;
import com.example.shinabandudkani.ListEStateActivity;
import com.example.shinabandudkani.MainActivity;
import com.example.shinabandudkani.R;
import com.example.shinabandudkani.ServiceLayer.AmlakApiService;
import com.example.shinabandudkani.ServiceLayer.DataFakeGeneraTor;
import com.example.shinabandudkani.ServiceLayer.TextApiService;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;
import com.example.shinabandudkani.StateCityActivity;
import com.example.shinabandudkani.ViewModel.AmlakCategory;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;

public class MainArabicActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AmlakApiService amlakapi;
    RecyclerView recyclegroup,recycleestate;
    SharePrefrence share;
    SaveSetting svset;
    SaveSetting svuser;
    TextApiService textApiService;
    String helpbuy,helprent="";


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_buy:
                    amlakapi=new AmlakApiService(MainArabicActivity.this);
                    amlakapi.getamlakcat("ar",0,svset.getCityId(),new AmlakApiService.OnDoctorReceived() {
                        @Override
                        public void onReceived(AmlakCategory amlakcategory) {
                            Log.e("fiinal",amlakcategory.toString());
                            GroupAdaptor pa1=new GroupAdaptor(MainArabicActivity.this, amlakcategory.getCategories(),0);
                            recyclegroup.setLayoutManager(new LinearLayoutManager(MainArabicActivity.this,LinearLayoutManager.HORIZONTAL,false));
                            recyclegroup.setAdapter(pa1);

                            EstateAdaptor estateadaptor=new EstateAdaptor(MainArabicActivity.this, amlakcategory.getAmlaks());
                            recycleestate.setLayoutManager(new LinearLayoutManager(MainArabicActivity.this,LinearLayoutManager.VERTICAL,false));
                            recycleestate.setAdapter(estateadaptor);
                        }
                    });
                    return true;
                case R.id.navigation_rent:
                    amlakapi=new AmlakApiService(MainArabicActivity.this);
                    amlakapi.getamlakcat("ar",1,svset.getCityId(),new AmlakApiService.OnDoctorReceived() {
                        @Override
                        public void onReceived(AmlakCategory amlakcategory) {
                            Log.e("fiinal",amlakcategory.toString());
                            GroupAdaptor pa1=new GroupAdaptor(MainArabicActivity.this, amlakcategory.getCategories(),1);
                            recyclegroup.setLayoutManager(new LinearLayoutManager(MainArabicActivity.this,LinearLayoutManager.HORIZONTAL,false));
                            recyclegroup.setAdapter(pa1);

                            EstateAdaptor estateadaptor=new EstateAdaptor(MainArabicActivity.this, amlakcategory.getAmlaks());
                            recycleestate.setLayoutManager(new LinearLayoutManager(MainArabicActivity.this,LinearLayoutManager.VERTICAL,false));
                            recycleestate.setAdapter(estateadaptor);
                        }
                    });
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_arabic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        share=new SharePrefrence(this);
        svset=new SaveSetting();
        svset=share.getUser();

        textApiService=new TextApiService(this);
        textApiService.gettexts(new TextApiService.OnTextReceived() {
            @Override
            public void onReceived(InfoApp infoapp) {
                helpbuy=infoapp.getHelpbuyar();
                helprent=infoapp.getHelprentar();
            }
        });


        if (svset.getFirsttime().equals(0)){
            showhelp1();
        }
        BottomNavigationView navView = findViewById(R.id.nav_view_list);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recyclegroup=(RecyclerView)findViewById(R.id.grouprecycleben);
        recycleestate=(RecyclerView)findViewById(R.id.productrecycleben);
        amlakapi=new AmlakApiService(this);
        amlakapi.getamlakcat("ar",0,svset.getCityId(), new AmlakApiService.OnDoctorReceived() {
            @Override
            public void onReceived(AmlakCategory amlakcategory) {
                GroupAdaptor pa1=new GroupAdaptor(MainArabicActivity.this, amlakcategory.getCategories(),0);
                recyclegroup.setLayoutManager(new LinearLayoutManager(MainArabicActivity.this,LinearLayoutManager.HORIZONTAL,false));
                recyclegroup.setAdapter(pa1);

                EstateAdaptor estateadaptor=new EstateAdaptor(MainArabicActivity.this, amlakcategory.getAmlaks());
                recycleestate.setLayoutManager(new LinearLayoutManager(MainArabicActivity.this,LinearLayoutManager.VERTICAL,false));
                recycleestate.setAdapter(estateadaptor);
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

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "2 مرات للخروج", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_arabic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.help) {
            svset=share.getUser();
            svuser=new SaveSetting();
            svuser.setCityId(svset.getCityId());
            svuser.setFirsttime(0);
            svuser.setLanguage(svset.getLanguage());
            share.saveUser(svuser);
            Intent i = new Intent(MainArabicActivity.this, MainArabicActivity.class);
            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showhelp1(){
        svset=share.getUser();
        svuser=new SaveSetting();
        svuser.setCityId(svset.getCityId());
        svuser.setFirsttime(1);
        svuser.setLanguage(svset.getLanguage());
        share.saveUser(svuser);

        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.navigation_rent), "", helprent)
                        // All options below are optional
                        .outerCircleColor(R.color.colorPrimary)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(10)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(R.color.white)  // Specify the color of the description text
                        .textColor(R.color.white)            // Specify a color for both the title and description text
                        .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                        .targetRadius(60),                  // Specify the target radius (in dp)
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                        showhelp2();
                    }
                });
    }

    private void showhelp2(){
        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.navigation_buy), "", helpbuy)
                        // All options below are optional
                        .outerCircleColor(R.color.colorPrimary)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(10)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(R.color.white)  // Specify the color of the description text
                        .textColor(R.color.white)            // Specify a color for both the title and description text
                        .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                        .targetRadius(60),                  // Specify the target radius (in dp)
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                    }
                });
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(MainArabicActivity.this, MainArabicActivity.class);
            startActivity(i);
            finish();
        }else if (id == R.id.nav_city) {
            Intent i = new Intent(MainArabicActivity.this, StateCityActivity.class);
            i.putExtra("lang","ar");
            startActivity(i);
            finish();

        }  else if (id == R.id.nav_tools) {
            Intent i = new Intent(MainArabicActivity.this, CityActivity.class);
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
