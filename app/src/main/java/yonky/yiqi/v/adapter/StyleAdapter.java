package yonky.yiqi.v.adapter;

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
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.util.GlideUtil;
import yonky.yiqi.v.GoodDetailActivity;

public class StyleAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    public static final int TYPE_NORMAL=0X01;
    public static final int TYPE_NODATA=0X02;
    private int emptyCount;

    List<GoodBean> beanList;
    Context mContext;

    public StyleAdapter(Context mContext,List<GoodBean> beanList ) {
        this.beanList = beanList;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if(beanList.size()==0){
            return TYPE_NODATA;  //加载失败或返回201 没有数据
        }else {
            return TYPE_NORMAL; //正常情况下
        }
    }

    @Override
    public int getItemCount() {
        return beanList.size()==0? emptyCount:beanList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_NODATA){
           return new NoDataHolder(LayoutInflater.from(mContext).inflate(R.layout.item_nodata,parent,false));
        }else{
            return new StyleHolder(LayoutInflater.from(mContext).inflate(R.layout.item_style,parent,false));
        }
        }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(beanList.size()!=0){
            GoodBean bean = beanList.get(position);
            GlideUtil.loadImage(bean.getTb_img(),((StyleHolder)holder).imageView);
            ((StyleHolder)holder).title.setText(bean.getTitle());
            ((StyleHolder)holder).price.setText(mContext.getResources().getString(R.string.price,bean.getPrice2()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startDetail(position);
            }
        });
        }


    }
    private void startDetail(int position){
        GoodBean bean =beanList.get(position);
        Intent intent = new Intent(mContext, GoodDetailActivity.class);
        intent.putExtra("goodbean",bean);
        mContext.startActivity(intent);

    }

    class StyleHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView title;
        @BindView(R.id.iv_img)
        ImageView imageView;
        @BindView(R.id.tv_price)
        TextView price;

        public StyleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class NoDataHolder extends RecyclerView.ViewHolder{

        public NoDataHolder(View itemView) {
            super(itemView);
        }
    }

    public List<GoodBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<GoodBean> beanList) {
        this.beanList = beanList;
    }


    public void setEmptyCount(int emptyCount) {
        this.emptyCount = emptyCount;
    }
}
