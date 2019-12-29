package com.example.shinabandudkani.AdaptorLayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shinabandudkani.ArabicLayout.DetailHouseArabicActivity;
import com.example.shinabandudkani.DetailHouseActivity;
import com.example.shinabandudkani.DomainLayer.Amlak;
import com.example.shinabandudkani.DomainLayer.Group;
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.DomainLayer.eState;
import com.example.shinabandudkani.KurdishLayout.DetailHouseKurdishActivity;
import com.example.shinabandudkani.R;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;
import com.squareup.picasso.Picasso;

import java.util.List;


public class EstateAdaptor extends RecyclerView.Adapter<EstateAdaptor.EstateViewHolder> {


    private Context context;
    private List<Amlak> allestate;
    private SharePrefrence usermanager;

    public EstateAdaptor(Context context, List<Amlak> allestate){
        this.context = context;
        this.allestate = allestate;
    }

    @NonNull
    @Override
    public EstateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_item_estate,viewGroup,false);
        return new EstateAdaptor.EstateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstateViewHolder estateViewHolder, int i) {
        Amlak state=allestate.get(i);

        estateViewHolder.txtaddress.setText(state.getDescription());
        estateViewHolder.txtcost.setText("");
        estateViewHolder.txttittle.setText(state.getTitle());
        Picasso.with(context).load(state.getImages()).placeholder(R.drawable.roof).into(estateViewHolder.circleimageview);
    }

    @Override
    public int getItemCount() {
        return allestate.size();
    }


    public class EstateViewHolder extends RecyclerView.ViewHolder{

        private ImageView circleimageview;
        private TextView txttittle;
        private TextView txtcost;
        private TextView txtaddress;
        private LinearLayout llbtnbuyitem;
        public EstateViewHolder(@NonNull View itemView) {
            super(itemView);
            txttittle=(TextView)itemView.findViewById(R.id.txttittle);
            txtcost=(TextView)itemView.findViewById(R.id.txtcost);
            txtaddress=(TextView)itemView.findViewById(R.id.txtaddress);
            circleimageview=(ImageView)itemView.findViewById(R.id.imgimageviewamlak);
            llbtnbuyitem=(LinearLayout)itemView.findViewById(R.id.btnadddoctoritem);

            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/KMIDYA.TTF");
            txttittle.setTypeface(typeface);
            txtaddress.setTypeface(typeface);
            txtcost.setTypeface(typeface);
            llbtnbuyitem.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    usermanager=new SharePrefrence(context);
                    SaveSetting ss=usermanager.getUser();
                    if (ss!=null){
                        Log.e("lang",ss.getLanguage());
                        if (ss.getLanguage().equals("en")){
                            Intent i = new Intent(context, DetailHouseActivity.class);
                            i.putExtra("id",allestate.get(getAdapterPosition()).getId());
                            context.startActivity(i);
                        }else if(ss.getLanguage().equals("ar")){
                            Intent i = new Intent(context, DetailHouseArabicActivity.class);
                            i.putExtra("id",allestate.get(getAdapterPosition()).getId());
                            context.startActivity(i);
                        }else if(ss.getLanguage().equals("ku")){
                            Intent i = new Intent(context, DetailHouseKurdishActivity.class);
                            i.putExtra("id",allestate.get(getAdapterPosition()).getId());
                            context.startActivity(i);
                        }
                    }

                }
            });
        }

    }
}
