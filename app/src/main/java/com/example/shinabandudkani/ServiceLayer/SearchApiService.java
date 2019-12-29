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
import com.example.shinabandudkani.ServiceLayer.Converters.AmlakConverter;
import com.example.shinabandudkani.ViewModel.AmlakCategory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SearchApiService {
    private static final String TAG = "UserApiService";
    private String Urls="http://shinaban.com";
    private Context context;
    private AmlakConverter ac;

    public SearchApiService(Context context){
        this.context=context;
    }


    public void getsearch1(final String lang,final Integer city,final Integer type,final Integer cat,final String text,final String minmetr,final String maxmetr,final String roomcount,final String emincost,final String emaxcost,final String pmincost,final String pmaxcost,final String minkhadamat,final String maxkhadamat,final String etype,final String ptype,final String khtype,final SearchApiService.OnDoctorReceived onDoctorReceived){

        HashMap<String,String> hashmap=new HashMap<String,String>();
        hashmap.put("text",text);
        hashmap.put("minmetr",minmetr);
        hashmap.put("maxmetr",maxmetr);
        hashmap.put("roomcount",roomcount);
        hashmap.put("emincost",emincost);
        hashmap.put("emaxcost",emaxcost);
        if (type==1){
            hashmap.put("pmincost",pmincost);
            hashmap.put("pmaxcost",pmaxcost);
            if (ptype.equals("Dollar")){
                hashmap.put("ptype","1");
            }else {
                hashmap.put("ptype","2");
            }
        }

        hashmap.put("minkhadamat",minkhadamat);
        hashmap.put("maxkhadamat",maxkhadamat);
        if (etype.equals("Dollar")){
            hashmap.put("etype","1");
        }else {
            hashmap.put("etype","2");
        }
        if (ptype.equals("Dollar")){
            hashmap.put("ptype","1");
        }else {
            hashmap.put("ptype","2");
        }
        if (khtype.equals("Dollar")){
            hashmap.put("khtype","1");
        }else {
            hashmap.put("khtype","2");
        }

        Log.e("error",hashmap.toString());
        JSONObject jsonobj=new JSONObject(hashmap);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, Urls+"/api/v1/ads/"+lang+"/"+city+"/"+type+"/"+cat,jsonobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("response",response.toString());
                ac=new AmlakConverter();
                onDoctorReceived.onReceived(ac.ConvertAmlakList(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());
                Toast.makeText(context,"error darad",Toast.LENGTH_LONG).show();
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public void getsearch2(final String lang,final Integer city,final Integer type,final Integer cat,final String text,final String minmetr,final String maxmetr,final String typeland,final String emincost,final String pmaxcost,final String pmincost,final String emaxcost,final String etype,final String ptype,final SearchApiService.OnDoctorReceived onDoctorReceived){
        HashMap<String,String> hashmap=new HashMap<String,String>();
        hashmap.put("text",text);
        hashmap.put("minmetr",minmetr);
        hashmap.put("maxmetr",maxmetr);
        hashmap.put("typeland",typeland);
        hashmap.put("emincost",emincost);
        hashmap.put("emaxcost",emaxcost);
        if (type == 1) {
            hashmap.put("pmincost",pmincost);
            hashmap.put("pmaxcost",pmaxcost);
            if (ptype.equals("Dollar")){
                hashmap.put("ptype","1");
            }else {
                hashmap.put("ptype","2");
            }
        }

        if (etype.equals("Dollar")){
            hashmap.put("etype","1");
        }else {
            hashmap.put("etype","2");
        }
        JSONObject jsonobj=new JSONObject(hashmap);


        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, Urls+"/api/v1/ads/"+lang+"/"+city+"/"+type+"/"+cat,jsonobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("response",response.toString());
                ac=new AmlakConverter();
                onDoctorReceived.onReceived(ac.ConvertAmlakList(response));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());
                Toast.makeText(context,"error darad",Toast.LENGTH_LONG).show();
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public void getsearch3(final String lang,final Integer city,final Integer type,final Integer cat,final String text,final String minmetr,final String maxmetr,final String emincost,final String emaxcost,final String pmincost,final String pmaxcost,final String etype,final String ptype,final SearchApiService.OnDoctorReceived onDoctorReceived){
        HashMap<String,String> hashmap=new HashMap<String,String>();
        hashmap.put("text",text);
        hashmap.put("minmetr",minmetr);
        hashmap.put("maxmetr",maxmetr);
        hashmap.put("emincost",emincost);
        hashmap.put("emaxcost",emaxcost);
        hashmap.put("pmincost",pmincost);
        hashmap.put("pmaxcost",pmaxcost);
        if (ptype.equals("Dollar")){
            hashmap.put("ptype","1");
        }else {
            hashmap.put("ptype","2");
        }
        if (etype.equals("Dollar")){
            hashmap.put("etype","1");
        }else {
            hashmap.put("etype","2");
        }



        JSONObject jsonobj=new JSONObject(hashmap);
        Log.e("params",jsonobj.toString());

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, Urls+"/api/v1/ads/"+lang+"/"+city+"/"+type+"/"+cat,jsonobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("response",response.toString());
                ac=new AmlakConverter();
                onDoctorReceived.onReceived(ac.ConvertAmlakList(response));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());
                Toast.makeText(context,"error darad",Toast.LENGTH_LONG).show();
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public interface OnDoctorReceived{
        void onReceived(AmlakCategory amlakcategory);
    }
}
