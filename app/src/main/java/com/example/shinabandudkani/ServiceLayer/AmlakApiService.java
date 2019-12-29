package com.example.shinabandudkani.ServiceLayer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shinabandudkani.DomainLayer.Amlak;
import com.example.shinabandudkani.DomainLayer.Category;
import com.example.shinabandudkani.DomainLayer.CityState;
import com.example.shinabandudkani.ServiceLayer.Converters.AmlakConverter;
import com.example.shinabandudkani.ViewModel.AmlakCategory;
import com.example.shinabandudkani.ViewModel.AmlakDetail;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmlakApiService {

    private static final String TAG = "UserApiService";
    private String Urls="http://shinaban.com";
    private String License="https://urumium.com/Home/licence_key_dudkani";
    private Context context;
    private AmlakConverter ac;

    public AmlakApiService(Context context){
        this.context=context;
    }

    public void getamlakcat(String lang,Integer type,Integer city,final AmlakApiService.OnDoctorReceived onUsersReceived){
        String url="";
        if (lang=="ku"){
            url=Urls+"/api/v1/ads/ku/"+city+"/"+type;
        }else if (lang=="ar"){
            url=Urls+"/api/v1/ads/ar/"+city+"/"+type;
        }else if (lang=="en"){
            url=Urls+"/api/v1/ads/en/"+city+"/"+type;
        }
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("log",response.toString());
                ac=new AmlakConverter();
                onUsersReceived.onReceived(ac.ConvertAmlakCategory(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error );
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public void getamlakcatZirDaste(String lang,Integer type,Integer id,Integer city,final AmlakApiService.OnDoctorReceived onUsersReceived){
        String url="";
        if (lang=="ku"){
            url=Urls+"/api/v1/ads/ku/"+city+"/"+type+"/"+id;
        }else if (lang=="ar"){
            url=Urls+"/api/v1/ads/ar/"+city+"/"+type+"/"+id;
        }else if (lang=="en"){
            url=Urls+"/api/v1/ads/en/"+city+"/"+type+"/"+id;
        }
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("logzir",response.toString());
                ac=new AmlakConverter();
                onUsersReceived.onReceived(ac.ConvertAmlakList(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error );
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public void getamlakdetail(String lang,Integer id,Integer city,final AmlakApiService.OnAmlakDetailReceived onamlakdetail){
        String url="";
        if (lang=="ku"){
            url=Urls+"/api/v1/ad/ku/"+id;
        }else if (lang=="ar"){
            url=Urls+"/api/v1/ad/ar/"+id;
        }else if (lang=="en"){
            url=Urls+"/api/v1/ad/en/"+id;
        }
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("log",response.toString());
                ac=new AmlakConverter();
                onamlakdetail.onReceived(ac.ConvertAmlakDetail(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error );
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public void getstate(String lang,final AmlakApiService.OnACityStateReceived oncityrecive){

            JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, Urls+"/api/v1/state/"+lang, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    ac=new AmlakConverter();
                    oncityrecive.onReceived(ac.ConverListStatecity(response));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "onErrorResponse: "+error );
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(context).add(request);
    }

    public void getcity(Integer state,String lang,final AmlakApiService.OnACityStateReceived oncityrecive){

        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, Urls+"/api/v1/city/"+lang+"/"+state, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ac=new AmlakConverter();
                oncityrecive.onReceived(ac.ConverListStatecity(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error );
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public void getlicense(final AmlakApiService.Onlinceseactive onamlakdetail){
        StringRequest request=new StringRequest(Request.Method.GET, License, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("log",response.toString());
                onamlakdetail.onReceived(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error );
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public interface OnDoctorReceived{
        void onReceived(AmlakCategory amlakcategory);
    }

    public interface OnAmlakDetailReceived{
        void onReceived(AmlakDetail amlakdetail);
    }

    public interface OnACityStateReceived{
        void onReceived(List<CityState> citystate);
    }

    public interface Onlinceseactive{
        void onReceived(String active);
    }
}
