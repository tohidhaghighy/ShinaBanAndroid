package com.example.shinabandudkani.AdaptorLayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shinabandudkani.ArabicLayout.MainArabicActivity;
import com.example.shinabandudkani.CityActivity;
import com.example.shinabandudkani.DomainLayer.Category;
import com.example.shinabandudkani.DomainLayer.CityState;
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.KurdishLayout.MainKurdishActivity;
import com.example.shinabandudkani.ListEStateActivity;
import com.example.shinabandudkani.MainActivity;
import com.example.shinabandudkani.R;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;
import com.example.shinabandudkani.StateCityActivity;

import java.util.List;

public class CityAdaptor extends RecyclerView.Adapter<CityAdaptor.CityViewHolder> {

    private Context context;
    private List<CityState> allcity;
    private SharePrefrence share;
    private String lang;

    public CityAdaptor(Context context, List<CityState> allcity,String lang){
        this.context = context;
        this.allcity = allcity;
        this.lang=lang;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_item_city,viewGroup,false);
        return new CityAdaptor.CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int i) {
        CityState cityState=allcity.get(i);
        cityViewHolder.txtname.setText(cityState.getName());
    }

    @Override
    public int getItemCount() {
        return allcity.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llbtnclick;
        private TextView txtname;
        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txttext);
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/KMIDYA.TTF");
            txtname.setTypeface(typeface);
            llbtnclick=(LinearLayout) itemView.findViewById(R.id.llbtnselect);
            llbtnclick.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    share=new SharePrefrence(context);
                    SaveSetting svset=new SaveSetting();
                    svset.setCityId(allcity.get(getAdapterPosition()).getId());
                    svset.setFirsttime(0);
                    svset.setLanguage(lang);
                    share.saveUser(svset);
                    if (lang.equals("en")){
                        Intent i = new Intent(context, MainActivity.class);
                        i.putExtra("id",allcity.get(getAdapterPosition()).getId());
                        context.startActivity(i);
                    }else if (lang.equals("ar")){
                        Intent i = new Intent(context, MainArabicActivity.class);
                        i.putExtra("id",allcity.get(getAdapterPosition()).getId());
                        context.startActivity(i);

                    }else if (lang.equals("ku")){
                        Intent i = new Intent(context, MainKurdishActivity.class);
                        i.putExtra("id",allcity.get(getAdapterPosition()).getId());
                        context.startActivity(i);
                    }

                    ((Activity)context).finish();
                }
            });
        }

    }
}
