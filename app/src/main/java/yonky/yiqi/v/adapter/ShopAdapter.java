package yonky.yiqi.v.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yonky.yiqi.R;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.util.GlideUtil;
import yonky.yiqi.util.MyUtil;

/**
 * Created by Administrator on 2018/5/31.
 */

public class ShopAdapter extends RecyclerView.Adapter {
    private static final int TYPE_MYHOLDER=0X01;
    private static final int TYPE_TITLEHOLDER=0X02;

    private ShopBean mShopBean;
    private Context mContext;
    String[] tags;
    String[] contents;
    SharedPreferences mPreferences;
    public ShopAdapter(ShopBean shopBean, Context context) {
        mShopBean = shopBean;
        mContext = context;
        mPreferences = mContext.getSharedPreferences("data",0);
        init(mShopBean);
    }

    private void init(ShopBean shopBean){
        tags = new String[]{"档口信息","地区","商城","主营","服务","联系方式","QQ","旺旺","手机","档口名片"};
        contents= new String[]{"",
                mPreferences.getString("region","广州"),
                shopBean.getMarket()+"-"+shopBean.getFloor()+"-"+shopBean.getDangkou(),
                shopBean.getMajor(),
                shopBean.getS_service(),
                "",
                shopBean.getQq(),
                shopBean.getTb_nick(),
                shopBean.getPhone(),
                ""};
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_TITLEHOLDER;
        }else {
            return TYPE_MYHOLDER;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_MYHOLDER:
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_shop,parent,false));

            case TYPE_TITLEHOLDER:
            return new TitleHolder(LayoutInflater.from(mContext).inflate(R.layout.item_shop2,parent,false));
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            ((MyViewHolder)holder).tag.setText(tags[position-1]);
            ((MyViewHolder) holder).content.setText(contents[position-1]);
            if(position==1 ||position==6 || position==10){
                ((MyViewHolder) holder).tag.setTextColor(Color.BLACK);
            }

        }else if(holder instanceof TitleHolder){
            GlideUtil.loadImage(mShopBean.getSerller_head_original(),((TitleHolder) holder).avatar);
            ((TitleHolder) holder).title.setText(mShopBean.getShop_name());

        }




    }

    @Override
    public int getItemCount() {
        return tags.length+1;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.tag)
        TextView tag;
        @BindView(R.id.content)
        TextView content;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
     class TitleHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_avatar)
        ImageView avatar;
        @BindView(R.id.tv_title)
        TextView title;
        @BindView(R.id.bt_gz)
        Button mButton;
        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
            @OnClick(R.id.bt_gz)
            void gz(){
            MyUtil.toast(mContext);
            }
    }
}
