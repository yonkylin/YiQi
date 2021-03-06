package yonky.yiqi.v.main.adapter;

import android.content.Context;
import android.content.Intent;
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
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.util.GlideUtil;
import yonky.yiqi.v.GoodsActivity;

/**
 * Created by Administrator on 2018/5/12.
 */

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketHolder> {
    private Context mContext;
    private List<ShopBean> mBeanList;

    public MarketAdapter(Context mContext, List<ShopBean> beanList){
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
    public void onBindViewHolder(@NonNull MarketHolder holder, final int position) {
        if(mBeanList!=null){
            ShopBean item = mBeanList.get(position);
            GlideUtil.loadImage(item.getSerller_head_original(),holder.shopImg);
            holder.discount.setText(item.getDiscount());
            holder.shopName.setText(item.getShop_name());
            holder.position.setText(item.getMarket()+"-"+item.getFloor()+"-"+item.getDangkou());
            holder.major.setText("主营:"+item.getMajor());
            holder.service.setText("服务:"+item.getS_service());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goGoodsActivity(position);
                }
            });
        }
    }
    private void goGoodsActivity(int position){
        ShopBean bean =mBeanList.get(position);
        Intent intent = new Intent(mContext, GoodsActivity.class);
        intent.putExtra("shopbean",bean);
        mContext.startActivity(intent);
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

    public List<ShopBean> getBeanList() {
        return mBeanList;
    }

    public void setBeanList(List<ShopBean> beanList) {
        mBeanList = beanList;
    }
}
