package com.example.shinabandudkani.ServiceLayer.Converters;

import com.example.shinabandudkani.DomainLayer.InfoApp;
import com.example.shinabandudkani.ViewModel.AmlakDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TextConverter {

    private String Urls="http://shinaban.com";

    public InfoApp ConvertInfoApp(JSONObject response){
        InfoApp allinfo=new InfoApp();
        try {
            JSONObject info=response.getJSONObject("ui");
            allinfo.setIcon(Urls+info.getString("mainIcon"));
            JSONObject choiselanicon=info.getJSONObject("ChoseLangIcon");
            JSONObject choisedescriptionlang=choiselanicon.getJSONObject("description");
            allinfo.setDescriptionar(choisedescriptionlang.getString("ar"));
            allinfo.setDescriptionen(choisedescriptionlang.getString("en"));
            allinfo.setDescriptionku(choisedescriptionlang.getString("ku"));

            JSONObject choiseenimgandtexten=choiselanicon.getJSONObject("en");
            allinfo.setIconen(choiseenimgandtexten.getString("icon"));
            allinfo.setTitleen(choiseenimgandtexten.getString("title"));

            JSONObject choiseenimgandtextar=choiselanicon.getJSONObject("ar");
            allinfo.setIconar(choiseenimgandtextar.getString("icon"));
            allinfo.setTitlear(choiseenimgandtextar.getString("title"));

            JSONObject choiseenimgandtextku=choiselanicon.getJSONObject("ku");
            allinfo.setIconku(choiseenimgandtextku.getString("icon"));
            allinfo.setTitleku(choiseenimgandtextku.getString("title"));

            JSONObject choisestate=info.getJSONObject("ChoseState");
            allinfo.setChoiseStateen(choisestate.getString("en"));
            allinfo.setChoiseStateku(choisestate.getString("ku"));
            allinfo.setChoiseStatear(choisestate.getString("ar"));

            JSONObject choisecity=info.getJSONObject("ChoseCity");
            allinfo.setChoiseCityen(choisecity.getString("en"));
            allinfo.setChoiseCityku(choisecity.getString("ku"));
            allinfo.setChoiseCityar(choisecity.getString("ar"));


            JSONObject helpbuy=info.getJSONObject("HelpBuy");
            allinfo.setHelpbuyen(helpbuy.getString("en"));
            allinfo.setHelpbuyku(helpbuy.getString("ku"));
            allinfo.setHelpbuyar(helpbuy.getString("ar"));

            JSONObject helprent=info.getJSONObject("HelpRent");
            allinfo.setHelprenten(helprent.getString("en"));
            allinfo.setHelprentku(helprent.getString("ku"));
            allinfo.setHelprentar(helprent.getString("ar"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allinfo;
    }

}
