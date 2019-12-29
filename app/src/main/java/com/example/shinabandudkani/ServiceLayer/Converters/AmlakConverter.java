package com.example.shinabandudkani.ServiceLayer.Converters;

import android.util.Log;

import com.example.shinabandudkani.DomainLayer.Amlak;
import com.example.shinabandudkani.DomainLayer.Category;
import com.example.shinabandudkani.DomainLayer.CityState;
import com.example.shinabandudkani.ViewModel.AmlakCategory;
import com.example.shinabandudkani.ViewModel.AmlakDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AmlakConverter {
    public AmlakCategory ConvertAmlakCategory(JSONObject response){
        AmlakCategory newamlakcat=new AmlakCategory();
        try {
            JSONArray amlak=response.getJSONArray("ads");
            newamlakcat.setAmlaks(ConverAmlak(amlak));
            JSONArray category=response.getJSONArray("categories");
            newamlakcat.setCategories(ConverCategory(category));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newamlakcat;
    }

    public AmlakCategory ConvertAmlakList(JSONObject response){
        AmlakCategory newamlakcat=new AmlakCategory();
        try {
            JSONArray amlak=response.getJSONArray("ads");
            newamlakcat.setAmlaks(ConverAmlak(amlak));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newamlakcat;
    }

    public AmlakDetail ConvertAmlakDetail(JSONObject response){
        Log.e("allinfo",response.toString());
        AmlakDetail newamlakdetail=new AmlakDetail();
        try {
            JSONObject amlak=response.getJSONObject("ad");
            JSONObject userinfo=response.getJSONObject("user");
            JSONArray similarpost=response.getJSONArray("similar_ads");
            JSONArray property=response.getJSONArray("properties");
            JSONArray gallery=amlak.getJSONArray("images");
            newamlakdetail.setAllimages(ConverListString(gallery));
            newamlakdetail.setId(1);
            newamlakdetail.setLat(amlak.getString("lat"));
            newamlakdetail.setLng(amlak.getString("lng"));
            newamlakdetail.setAllamlak(ConverAmlak(similarpost));
            newamlakdetail.setAllproperty(ConverListStringProperty(property));
            newamlakdetail.setTittle(amlak.getString("title"));
            newamlakdetail.setName(userinfo.getString("username"));
            newamlakdetail.setEmail(userinfo.getString("email"));
            newamlakdetail.setBoard_Name(userinfo.getString("board_name"));
            newamlakdetail.setTell(userinfo.getString("tell"));
            String ss=amlak.getString("description");
            ss=ss.replace("&nbsp;"," ");
            newamlakdetail.setDescription(ss);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newamlakdetail;
    }


    public List<Category> ConverCategory(JSONArray response){
        List<Category> categories=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            Category cat=new Category();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                cat.setTitle(jsonObject.getString("title"));
                cat.setId(jsonObject.getInt("id"));

                categories.add(cat);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    public List<Amlak> ConverAmlak(JSONArray response){
        List<Amlak> categories=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            Amlak amlak=new Amlak();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                amlak.setTitle(jsonObject.getString("title"));
                amlak.setId(jsonObject.getInt("id"));
                String ss=jsonObject.getString("description");
                ss=ss.replace("&nbsp;"," ");
                Log.e("ts",ss);
                amlak.setDescription(ss);
                amlak.setImages("http://shinaban.com"+jsonObject.getString("images"));
                categories.add(amlak);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    public List<String> ConverListString(JSONArray response){
        List<String> strings=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                String jsonObject="http://shinaban.com"+response.getString(i);
                Log.e("log",jsonObject.toString());
                strings.add(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return strings;
    }

    public List<CityState> ConverListStatecity(JSONArray response){
        List<CityState> cities=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            CityState city=new CityState();
            try {
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObject=response.getJSONObject(i);
                city.setName(jsonObject.getString("name"));
                city.setId(jsonObject.getInt("id"));
                cities.add(city);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return cities;
    }

    public List<String> ConverListStringProperty(JSONArray response){
        List<String> strings=new ArrayList<>();
        Log.e("type",response.toString());
        for (int i = 0; i < response.length(); i++) {

            try {
                JSONObject jsonObject=response.getJSONObject(i);
                try{
                    String text=jsonObject.getString("title")+" : "+jsonObject.getString("value")+ "  "+jsonObject.getString("price_type");
                    strings.add(text);
                }catch (Exception ex){
                    String text=jsonObject.getString("title")+" : "+jsonObject.getString("value")+ "  ";
                    strings.add(text);
                }




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return strings;
    }


}
