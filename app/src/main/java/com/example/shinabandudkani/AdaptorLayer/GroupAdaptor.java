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

import com.example.shinabandudkani.ArabicLayout.ListEStateArabicActivity;
import com.example.shinabandudkani.DomainLayer.Category;
import com.example.shinabandudkani.DomainLayer.Group;
import com.example.shinabandudkani.DomainLayer.SaveSetting;
import com.example.shinabandudkani.KurdishLayout.ListEStateKurdishActivity;
import com.example.shinabandudkani.ListEStateActivity;
import com.example.shinabandudkani.R;
import com.example.shinabandudkani.SharePrefrence.SharePrefrence;

import java.util.List;

public class GroupAdaptor extends RecyclerView.Adapter<GroupAdaptor.GroupViewHolder> {


    private Context context;
    private Integer type;
    private List<Category> allgroup;
    SharePrefrence share;
    SaveSetting svset;

    public GroupAdaptor(Context context, List<Category> allgroup,Integer type){
        this.context = context;
        this.allgroup = allgroup;
        this.type=type;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_item_group,viewGroup,false);
        return new GroupAdaptor.GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder groupViewHolder, int i) {
        Category group=allgroup.get(i);
        groupViewHolder.txtgroupname.setText(group.getTitle());
    }

    @Override
    public int getItemCount() {
        return allgroup.size();
    }


    public class GroupViewHolder extends RecyclerView.ViewHolder{

        private ImageButton btngroup;
        private TextView txtgroupname;
        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            txtgroupname=(TextView)itemView.findViewById(R.id.txtgroup_name);
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/KMIDYA.TTF");
            txtgroupname.setTypeface(typeface);
            btngroup=(ImageButton)itemView.findViewById(R.id.btngrouplist);
            btngroup.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    share=new SharePrefrence(context);
                    svset=new SaveSetting();
                    svset=share.getUser();
                    Intent i;
                    if (svset.getLanguage().equals("en")){
                        i = new Intent(context, ListEStateActivity.class);
                        i.putExtra("id",allgroup.get(getAdapterPosition()).getId());
                        i.putExtra("type",type);
                        i.putExtra("camefrom",0);
                    }else if(svset.getLanguage().equals("ku")){
                        i = new Intent(context, ListEStateKurdishActivity.class);
                        i.putExtra("id",allgroup.get(getAdapterPosition()).getId());
                        i.putExtra("type",type);
                        i.putExtra("camefrom",0);
                    }else {
                        i = new Intent(context, ListEStateArabicActivity.class);
                        i.putExtra("id",allgroup.get(getAdapterPosition()).getId());
                        i.putExtra("type",type);
                        i.putExtra("camefrom",0);
                    }

                    context.startActivity(i);
                }
            });
        }

    }
}

