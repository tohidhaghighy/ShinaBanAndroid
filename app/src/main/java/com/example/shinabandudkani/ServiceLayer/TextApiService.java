package com.example.shinabandudkani.ServiceLayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shinabandudkani.DomainLayer.InfoApp;
import com.example.shinabandudkani.ServiceLayer.Converters.AmlakConverter;
import com.example.shinabandudkani.ServiceLayer.Converters.TextConverter;
import com.example.shinabandudkani.ViewModel.AmlakCategory;

import org.json.JSONObject;

public class TextApiService {

    private static final String TAG = "TextApiService";
    private String Urls="http://shinaban.com";
    private Context context;
    private TextConverter tc;

    public TextApiService(Context context){
        this.context=context;
    }

    public void gettexts(final TextApiService.OnTextReceived onTextReceived){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, Urls+"/api/v1/uis", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("log",response.toString());
                tc=new TextConverter();
                onTextReceived.onReceived(tc.ConvertInfoApp(response));
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


    public interface OnTextReceived{
        void onReceived(InfoApp infoapp);
    }

}
