package yonky.yiqi.v.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.yiqi.R;

/**
 * Created by Administrator on 2018/5/17.
 */

public class RegionAdapter extends RecyclerView.Adapter <RegionAdapter.RegionViewHolder>{
    Context mContext;
    SharedPreferences.Editor editor;
    String region;
    String[] regions={
            "广州","42",
            "潮汕","48",
            "新塘","52",
            "杭州","43",
            "白沟","55",
            "株洲","46",
            "花都","44",
            "郑州","47",
            "东莞","50",
            "深圳","53",
            "北京","45",
            "揭阳","54",
            "葫芦岛","56"
           };

    int isSelected;

    public RegionAdapter(Context context,String region) {
        mContext = context;
        this.region = region;
        editor=mContext.getSharedPreferences("region",0).edit();
        isSelected= searchString(regions,region)/2;
    }

    @Override
    public int getItemCount() {
        return regions.length/2;
    }



    @NonNull
    @Override
    public RegionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RegionViewHolder(LayoutInflater.from(mContext).inflate(R.layout.region_text,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull RegionViewHolder holder, final int position) {
//        isSelected= Arrays.binarySearch(regions,region)/2;

        Log.d("RegionAdapter",""+isSelected);
        if(position==isSelected){
            holder.region.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        }else {
            holder.region.setTextColor(mContext.getResources().getColor(R.color.gray));
        }
            holder.region.setText(regions[position*2]);
            holder.region.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelect(position);
                }
            });
    }


    class RegionViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_region)
        TextView region;
        public RegionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private void setSelect(int position){
        isSelected=position;
        editor.putString("region",regions[isSelected*2]);
        editor.putString("regionId",regions[isSelected+1]);
        notifyDataSetChanged();
    }
    private int searchString(String[] strings,String s){
        for(int i=0;i<strings.length;i++){
            if(s.equals(strings[i])){
                return i;
            }
        }
        return -1;
    }
}
