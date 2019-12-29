package com.example.shinabandudkani.AdaptorLayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.shinabandudkani.DomainLayer.Category;
import com.example.shinabandudkani.ListEStateActivity;
import com.example.shinabandudkani.R;

import java.util.List;

public class PropertyAdaptor extends RecyclerView.Adapter<PropertyAdaptor.PropertyViewHolder> {
    private Context context;
    private List<String> allproperty;

    public PropertyAdaptor(Context context, List<String> allproperty){
        this.context = context;
        this.allproperty = allproperty;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_item_properti,viewGroup,false);
        return new PropertyAdaptor.PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder propertyViewHolder, int i) {
        String text=allproperty.get(i);
        propertyViewHolder.txttitle.setText(text);
    }

    @Override
    public int getItemCount() {
        return allproperty.size();
    }


    public class PropertyViewHolder extends RecyclerView.ViewHolder{

        private TextView txttitle;
        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle=(TextView)itemView.findViewById(R.id.txttext);
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/KMIDYA.TTF");
            txttitle.setTypeface(typeface);
        }

    }
}
