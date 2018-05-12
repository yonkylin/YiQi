package yonky.yiqi.v.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.yiqi.R;
import yonky.yiqi.bean.ItemsBean;
import yonky.yiqi.bean.MarketBean;
import yonky.yiqi.util.GlideUtil;

/**
 * Created by Administrator on 2018/5/12.
 */

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketHolder> {
    private Context mContext;
    private List<ItemsBean> mBeanList;

    public MarketAdapter(Context mContext, List<ItemsBean> beanList){
        this.mContext = mContext;
        this.mBeanList = beanList;
    }
    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    @NonNull
    @Override
    public MarketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MarketHolder(LayoutInflater.from(mContext).inflate(R.layout.item_market,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MarketHolder holder, int position) {
        if(mBeanList!=null){
            ItemsBean item = mBeanList.get(position);
            GlideUtil.loadImage(item.getSerller_head_original(),holder.shopImg);
            holder.discount.setText(item.getDiscount());
            holder.shopName.setText(item.getShop_name());
            holder.position.setText(item.getMarket()+"-"+item.getFloor()+"-"+item.getDangkou());
            holder.major.setText("主营:"+item.getMajor());
            holder.service.setText("服务:"+item.getS_service());


        }
    }

    class MarketHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_position)
        TextView position;
        @BindView(R.id.tv_discount)
        TextView discount;
        @BindView(R.id.tv_shop)
        TextView shopName;
        @BindView(R.id.iv_img)
        ImageView shopImg;
        @BindView(R.id.tv_major)
        TextView major;
        @BindView(R.id.tv_service)
        TextView service;

        public MarketHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public List<ItemsBean> getBeanList() {
        return mBeanList;
    }

    public void setBeanList(List<ItemsBean> beanList) {
        mBeanList = beanList;
    }
}
