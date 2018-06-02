package yonky.yiqi.v;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.yiqi.R;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.util.GlideUtil;

/**
 * Created by Administrator on 2018/6/2.
 */

public class SearchAdapter extends RecyclerView.Adapter {
    public static final int TYPE_ORIGINAL=0X01;
    public static final int TYPE_FILTER=0X02;
    public static final int TYPE_DETAIL=0X03;

    public static final int TYPE_NODATA=0X10;

    private Context mContext;
    private LayoutInflater mInflater;
    private List<GoodBean> mGoodBeanList;
    private GoodBean originalBean;
    private boolean isEmpty;

    public SearchAdapter(Context context,GoodBean bean) {
        mContext = context;
        originalBean = bean;
        mInflater=LayoutInflater.from(context);
        mGoodBeanList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_ORIGINAL;
        }else if(position==1){
            return TYPE_FILTER;
        }else if(isEmpty){
            return TYPE_NODATA;
        }else {
            return TYPE_DETAIL;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch(viewType){
            case TYPE_ORIGINAL:
                return new OriginalHolder(mInflater.inflate(R.layout.item_search_original,parent,false));
            case TYPE_FILTER:
                return new FilterHolder(mInflater.inflate(R.layout.item_search_filter,parent,false));
            case TYPE_DETAIL:
                return new DetailHolder(mInflater.inflate(R.layout.item_search_detail,parent,false));
            case TYPE_NODATA:
            return new NoDataHolder(LayoutInflater.from(mContext).inflate(R.layout.item_nodata,parent,false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof OriginalHolder){
            if(originalBean!=null){
                GlideUtil.loadImage(originalBean.getTb_img(),((OriginalHolder) holder).avatar);
                ((OriginalHolder) holder).originTitle.setText(originalBean.getTitle());
                ((OriginalHolder) holder).originPrice.setText(mContext.getResources().getString(R.string.price,originalBean.getPrice2()));
            }
        }else if(holder instanceof DetailHolder){
            GlideUtil.loadImage(mGoodBeanList.get(position-2).getTb_img(),((DetailHolder) holder).detailImage);
            ((DetailHolder) holder).detailTitle.setText(mGoodBeanList.get(position-2).getTitle());
            ((DetailHolder) holder).detailPrice.setText(mContext.getResources().getString(R.string.price,mGoodBeanList.get(position-2).getPrice2()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startDetail(position-2);
                }
            });
        }

    }

    private void startDetail(int position){
        GoodBean bean =mGoodBeanList.get(position);
        Intent intent = new Intent(mContext, GoodDetailActivity.class);
        intent.putExtra("goodbean",bean);
        mContext.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return isEmpty? 3:2+mGoodBeanList.size();
    }


    class OriginalHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_origin_avatar)
        ImageView avatar;
        @BindView(R.id.tv_origin_title)
        TextView originTitle;
        @BindView(R.id.tv_origin_price)
        TextView originPrice;
        public OriginalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class FilterHolder extends RecyclerView.ViewHolder{

        public FilterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class DetailHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_img)
        ImageView detailImage;
        @BindView(R.id.tv_title)
        TextView detailTitle;
        @BindView(R.id.tv_price)
        TextView detailPrice;

        public DetailHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class NoDataHolder extends RecyclerView.ViewHolder{

        public NoDataHolder(View itemView) {
            super(itemView);
        }
    }

    public List<GoodBean> getGoodBeanList() {
        return mGoodBeanList;
    }

    public void setGoodBeanList(List<GoodBean> goodBeanList) {

        mGoodBeanList = goodBeanList;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
