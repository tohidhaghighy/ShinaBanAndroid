package com.example.shinabandudkani.ServiceLayer;

import com.example.shinabandudkani.DomainLayer.Amlak;
import com.example.shinabandudkani.DomainLayer.Category;
import com.example.shinabandudkani.DomainLayer.CityState;
import com.example.shinabandudkani.DomainLayer.Group;
import com.example.shinabandudkani.DomainLayer.eState;

import java.util.ArrayList;
import java.util.List;

public class DataFakeGeneraTor {
    public static List<Amlak> getdata(){
    List<Amlak> allpro=new ArrayList<>();
        for (int i=0;i<10;i++){
        Amlak newproduct=new Amlak();
        newproduct.setId(i);
        newproduct.setTitle("group "+i);
        newproduct.setDescription("arbil street number 9 between 2 squre "+i);
        newproduct.setImages("http://i.imgur.com/DvpvklR.png");
        allpro.add(newproduct);
    }

        return allpro;
}

    public static List<Category> getdatagroup(){
        List<Category> allpro=new ArrayList<>();

        Category newproduct=new Category();
            newproduct.setId(1);
            newproduct.setTitle("اداری تجاری");
        allpro.add(newproduct);
        return allpro;
    }
    public static List<Category> getdatagroupenglish(){
        List<Category> allpro=new ArrayList<>();

        Category newproduct=new Category();
        newproduct.setId(1);
        newproduct.setTitle("Commercial office");
        allpro.add(newproduct);
        return allpro;
    }
    public static List<Category> getdatagrouparabic(){
        List<Category> allpro=new ArrayList<>();

        Category newproduct=new Category();
        newproduct.setId(1);
        newproduct.setTitle("المكتب التجاري");
        allpro.add(newproduct);
        return allpro;
    }

    public static List<CityState> getcitystate(){
        List<CityState> allpro=new ArrayList<>();
        for (int i=0;i<10;i++){
            CityState newproduct=new CityState();
            newproduct.setId(1);
            newproduct.setName("ارومیه");
            allpro.add(newproduct);
        }

        return allpro;
    }
}
