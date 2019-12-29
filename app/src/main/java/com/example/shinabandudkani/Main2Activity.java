package com.example.shinabandudkani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.shinabandudkani.AdaptorLayer.EstateAdaptor;
import com.example.shinabandudkani.ServiceLayer.DataFakeGeneraTor;

import java.util.ArrayList;
import java.util.List;

import ir.apend.slider.model.Slide;
import ir.apend.slider.ui.Slider;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Slider slider = findViewById(R.id.slider);
        final List<Slide> slideList = new ArrayList<>();


        for (int i = 0; i < 6; i++) {
            slideList.add(new Slide(i,"http://shinaban.com/images/ads/26/962891ff-38dd-4c80-93a6-1bd9f4b12a55.jpg" , getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
        }

        slider.addSlides(slideList);

        RecyclerView rv=(RecyclerView)findViewById(R.id.recyclerView);
        EstateAdaptor estateadaptor=new EstateAdaptor(Main2Activity.this, DataFakeGeneraTor.getdata());
        rv.setLayoutManager(new LinearLayoutManager(Main2Activity.this,LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(estateadaptor);
    }
}
