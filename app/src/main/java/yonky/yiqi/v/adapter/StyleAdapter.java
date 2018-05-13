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
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.util.GlideUtil;

public class StyleAdapter extends RecyclerView.Adapter <StyleAdapter.StyleHolder>{
    List<GoodBean> beanList;
    Context mContext;

    public StyleAdapter(Context mContext,List<GoodBean> beanList ) {
        this.beanList = beanList;
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        return beanList==null? 0:beanList.size();
    }

    @NonNull
    @Override
    public StyleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StyleHolder(LayoutInflater.from(mContext).inflate(R.layout.item_style,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull StyleHolder holder, int position) {
        if(beanList!=null){
            GoodBean bean = beanList.get(position);
            GlideUtil.loadImage(bean.getTb_img(),holder.imageView);
            holder.title.setText(bean.getTitle());
            holder.price.setText(mContext.getResources().getString(R.string.price,bean.getPrice2()));
        }

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

    public List<GoodBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<GoodBean> beanList) {
        this.beanList = beanList;
    }

}
