package com.example.shinabandudkani;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.ServiceLayer.AmlakApiService;
import com.example.shinabandudkani.ServiceLayer.DataFakeGeneraTor;
import com.example.shinabandudkani.ServiceLayer.SearchApiService;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;
import com.example.shinabandudkani.ViewModel.AmlakCategory;

public class ListEStateActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AmlakApiService amlakapi;
    SearchApiService searchapi;
    RecyclerView searchlist;
    SharePrefrence share;
    SaveSetting svset;
    Integer id;
    Integer type,camefrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_estate);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        share=new SharePrefrence(this);
        svset=new SaveSetting();
        svset=share.getUser();


        id = getIntent().getExtras().getInt("id");
        type = getIntent().getExtras().getInt("type");
        camefrom=getIntent().getExtras().getInt("camefrom");


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotosearchpage(id,type);
            }
        });
        searchlist=(RecyclerView)findViewById(R.id.searchlistrecycleben);
        amlakapi=new AmlakApiService(this);
        searchapi=new SearchApiService(this);
        if (camefrom==0){
            amlakapi.getamlakcatZirDaste("en", type, id,svset.getCityId(), new AmlakApiService.OnDoctorReceived() {
                @Override
                public void onReceived(AmlakCategory amlakcategory) {
                    EstateAdaptor estateadaptor=new EstateAdaptor(ListEStateActivity.this, amlakcategory.getAmlaks());
                    searchlist.setLayoutManager(new LinearLayoutManager(ListEStateActivity.this,LinearLayoutManager.VERTICAL,false));
                    searchlist.setAdapter(estateadaptor);
                }
            });
        }else {
            Integer whichsearch=getIntent().getExtras().getInt("whichsearch");
            if (whichsearch==1){

                String language=getIntent().getExtras().getString("lang");
                String text=getIntent().getExtras().getString("text");
                String minmetr=getIntent().getExtras().getString("minmetr");
                String maxmetr=getIntent().getExtras().getString("maxmetr");
                String roomcount=getIntent().getExtras().getString("roomcount");
                String minbuy=getIntent().getExtras().getString("emincost");
                Log.e("ts1",language+" "+text+" "+minmetr+" "+maxmetr);
                String maxbuy=getIntent().getExtras().getString("emaxcost");
                String minrent=getIntent().getExtras().getString("pmincost");
                String maxrent=getIntent().getExtras().getString("pmaxcost");
                String minkhadamat=getIntent().getExtras().getString("minkhadamat");
                String maxkhadamat=getIntent().getExtras().getString("maxkhadamat");
                String etype=getIntent().getExtras().getString("etype");
                String ptype=getIntent().getExtras().getString("ptype");
                String khtype=getIntent().getExtras().getString("khtype");
                if (type==0){
                    Log.e("ts1type0",language+" "+text+" "+minmetr+" "+maxmetr);
                    searchapi.getsearch1(language, svset.getCityId(), type, id, text, minmetr, maxmetr, roomcount, minbuy, maxbuy, "", "", minkhadamat, maxkhadamat, etype, ptype, khtype, new SearchApiService.OnDoctorReceived() {
                        @Override
                        public void onReceived(AmlakCategory amlakcategory) {
                            EstateAdaptor estateadaptor=new EstateAdaptor(ListEStateActivity.this, amlakcategory.getAmlaks());
                            searchlist.setLayoutManager(new LinearLayoutManager(ListEStateActivity.this,LinearLayoutManager.VERTICAL,false));
                            searchlist.setAdapter(estateadaptor);
                        }
                    });

                }else {
                    searchapi.getsearch1(language, svset.getCityId(), type, id, text, minmetr, maxmetr, roomcount, minbuy, maxbuy, minrent, maxrent, minkhadamat, maxkhadamat, etype, ptype, khtype, new SearchApiService.OnDoctorReceived() {
                        @Override
                        public void onReceived(AmlakCategory amlakcategory) {
                            EstateAdaptor estateadaptor=new EstateAdaptor(ListEStateActivity.this, amlakcategory.getAmlaks());
                            searchlist.setLayoutManager(new LinearLayoutManager(ListEStateActivity.this,LinearLayoutManager.VERTICAL,false));
                            searchlist.setAdapter(estateadaptor);
                        }
                    });

                }
            }
            else if(whichsearch==2){
                String language=getIntent().getExtras().getString("lang");
                String text=getIntent().getExtras().getString("text");
                String minmetr=getIntent().getExtras().getString("minmetr");
                String maxmetr=getIntent().getExtras().getString("maxmetr");
                String typeland=getIntent().getExtras().getString("typeland");
                String minbuy=getIntent().getExtras().getString("emincost");
                String maxbuy=getIntent().getExtras().getString("emaxcost");
                String minrent=getIntent().getExtras().getString("pmincost");
                String maxrent=getIntent().getExtras().getString("pmaxcost");
                Log.e("ts2",language+" "+text+" "+minmetr+" "+maxmetr);
                String etype=getIntent().getExtras().getString("etype");
                String ptype=getIntent().getExtras().getString("ptype");

                if (type==0){
                    Log.e("ts2type1",language+" "+text+" "+minmetr+" "+maxmetr);
                    searchapi.getsearch2(language, svset.getCityId(), type, id, text, minmetr, maxmetr,typeland, minbuy,maxbuy,"","", etype, ptype, new SearchApiService.OnDoctorReceived() {
                        @Override
                        public void onReceived(AmlakCategory amlakcategory) {
                            EstateAdaptor estateadaptor=new EstateAdaptor(ListEStateActivity.this, amlakcategory.getAmlaks());
                            searchlist.setLayoutManager(new LinearLayoutManager(ListEStateActivity.this,LinearLayoutManager.VERTICAL,false));
                            searchlist.setAdapter(estateadaptor);
                        }
                    });

                }else {

                    searchapi.getsearch2(language, svset.getCityId(), type, id, text, minmetr, maxmetr,typeland, minbuy,maxbuy,minrent,maxrent, etype, ptype, new SearchApiService.OnDoctorReceived() {
                        @Override
                        public void onReceived(AmlakCategory amlakcategory) {
                            EstateAdaptor estateadaptor=new EstateAdaptor(ListEStateActivity.this, amlakcategory.getAmlaks());
                            searchlist.setLayoutManager(new LinearLayoutManager(ListEStateActivity.this,LinearLayoutManager.VERTICAL,false));
                            searchlist.setAdapter(estateadaptor);
                        }
                    });
                }
            }
            else if(whichsearch==3){
                String language=getIntent().getExtras().getString("lang");
                String text=getIntent().getExtras().getString("text");
                String minmetr=getIntent().getExtras().getString("minmetr");
                String maxmetr=getIntent().getExtras().getString("maxmetr");
                String minbuy=getIntent().getExtras().getString("emincost");
                String maxbuy=getIntent().getExtras().getString("emaxcost");
                String minrent=getIntent().getExtras().getString("pmincost");
                String maxrent=getIntent().getExtras().getString("pmaxcost");
                Log.e("ts3",language+" "+text+" "+minmetr+" "+maxmetr);
                String etype=getIntent().getExtras().getString("etype");
                String ptype=getIntent().getExtras().getString("ptype");

                if (type==0){
                    Log.e("ts3type0",language+" "+text+" "+minmetr+" "+maxmetr);
                    searchapi.getsearch3(language, svset.getCityId(), type, id, text, minmetr,maxmetr, minbuy, maxbuy, "", "",etype,ptype, new SearchApiService.OnDoctorReceived() {
                        @Override
                        public void onReceived(AmlakCategory amlakcategory) {
                            EstateAdaptor estateadaptor=new EstateAdaptor(ListEStateActivity.this, amlakcategory.getAmlaks());
                            searchlist.setLayoutManager(new LinearLayoutManager(ListEStateActivity.this,LinearLayoutManager.VERTICAL,false));
                            searchlist.setAdapter(estateadaptor);
                        }
                    });

                }else {

                    searchapi.getsearch3(language, svset.getCityId(), type, id, text,minmetr,maxmetr, minbuy, maxbuy, minrent, maxrent,etype,ptype, new SearchApiService.OnDoctorReceived() {
                        @Override
                        public void onReceived(AmlakCategory amlakcategory) {
                            EstateAdaptor estateadaptor=new EstateAdaptor(ListEStateActivity.this, amlakcategory.getAmlaks());
                            searchlist.setLayoutManager(new LinearLayoutManager(ListEStateActivity.this,LinearLayoutManager.VERTICAL,false));
                            searchlist.setAdapter(estateadaptor);
                        }
                    });
                }
            }
        }




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
        getMenuInflater().inflate(R.menu.list_estate, menu);
        return true;
    }

    private void gotosearchpage(Integer id,Integer type){
        if (id==5 || id==11){
            Intent i = new Intent(ListEStateActivity.this, Search2Activity.class);
            i.putExtra("id",id);
            i.putExtra("type",type);
            startActivity(i);
        }else if (id==7 || id==13){
            Intent i = new Intent(ListEStateActivity.this, Search3Activity.class);
            i.putExtra("id",id);
            i.putExtra("type",type);
            startActivity(i);
        }else {
            Intent i = new Intent(ListEStateActivity.this, Search1Activity.class);
            i.putExtra("id",id);
            i.putExtra("type",type);
            startActivity(i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int ids = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (ids == R.id.search_item) {
            gotosearchpage(id,type);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(ListEStateActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }else if (id == R.id.nav_city) {
            Intent i = new Intent(ListEStateActivity.this, StateCityActivity.class);
            i.putExtra("lang","en");
            startActivity(i);
            finish();

        }  else if (id == R.id.nav_tools) {
            Intent i = new Intent(ListEStateActivity.this, CityActivity.class);
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
