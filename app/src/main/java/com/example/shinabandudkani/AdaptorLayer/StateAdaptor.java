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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shinabandudkani.CityActivity;
import com.example.shinabandudkani.CitySelectorActivity;
import com.example.shinabandudkani.DomainLayer.CityState;
import com.example.shinabandudkani.R;
import com.example.shinabandudkani.StateCityActivity;

import java.util.List;

public class StateAdaptor extends RecyclerView.Adapter<StateAdaptor.StateViewHolder> {

    private Context context;
    private List<CityState> allcity;
    private String Lang;

    public StateAdaptor(Context context, List<CityState> allcity,String lang){
        this.context = context;
        this.allcity = allcity;
        this.Lang=lang;
    }

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_item_city,viewGroup,false);
        return new StateAdaptor.StateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder stateViewHolder, int i) {
        CityState cityState=allcity.get(i);
        stateViewHolder.txtname.setText(cityState.getName());
    }

    @Override
    public int getItemCount() {
        return allcity.size();
    }


    public class StateViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llbtnclick;
        private TextView txtname;
        public StateViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txttext);
            llbtnclick=(LinearLayout) itemView.findViewById(R.id.llbtnselect);
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/KMIDYA.TTF");
            txtname.setTypeface(typeface);
            llbtnclick.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent i = new Intent(context, CitySelectorActivity.class);
                    i.putExtra("id",allcity.get(getAdapterPosition()).getId());
                    i.putExtra("lang",Lang);
                    context.startActivity(i);
                    ((Activity)context).finish();
                }
            });
        }

    }
}

