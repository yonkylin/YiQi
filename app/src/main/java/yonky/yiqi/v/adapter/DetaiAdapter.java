package yonky.yiqi.v.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yonky.yiqi.R;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.util.GlideUtil;
import yonky.yiqi.util.MyUtil;
import yonky.yiqi.v.GoodsActivity;

public class DetaiAdapter extends RecyclerView.Adapter {
    public static final int TYPE_BANNER=0X001;
    public static final int TYPE_SHOP=0X002;
    public static final int TYPE_PIC=0X003;
    public static final int TYPE_PARAM=0X004;
    public static final int TYPE_STYLE=0X005;


    private Context mContext;
    private GoodBean goodBean;
    private ShopBean shopBean;
    private List<String> imgList;

    private LayoutInflater layoutInflater;
    private int type=TYPE_PIC;

    public DetaiAdapter(Context mContext) {
        this.mContext = mContext;
        layoutInflater=LayoutInflater.from(mContext);
    }

    @Override

    public int getItemCount() {
        int i=0;
        int j=0;
        if(imgList!=null){
            i=imgList.size();
        }
        if(goodBean !=null){
            j= goodBean.getAttributes().size();
        }
        return type==TYPE_PIC? 3+i: 3+j;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_BANNER;
        }else if(position==1){
            return TYPE_SHOP;
        }else if(position ==2){
            return TYPE_STYLE;
        }
        return type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_BANNER:
                return new BannerHolder(layoutInflater.inflate(R.layout.item_detail_banner,parent,false));
            case TYPE_SHOP:
                return new ShopHolder(layoutInflater.inflate(R.layout.item_detail_shop,parent,false));
            case TYPE_STYLE:
                return new TypeHolder(layoutInflater.inflate(R.layout.item_detail_type,parent,false));
                default:

                      return new PicTextHolder(layoutInflater.inflate(R.layout.item_detail_img,parent,false));

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(goodBean !=null){
            if(holder instanceof BannerHolder){
                String s= goodBean.getTb_imgs();
                String[] imgs= s.split(",");
                List<String> urls= new ArrayList<>();
                for(int i=0;i<imgs.length;i++) {
                    urls.add(imgs[i]);
                }
                ((BannerHolder) holder).banner.setImages(urls).setImageLoader(new GlideUtil()).start();


                ((BannerHolder) holder).title.setText(goodBean.getTitle());
                ((BannerHolder) holder).price2.setText(String.format("¥ %.1f", goodBean.getPrice2()));
//             删除线
                SpannableString spannableString = new SpannableString("淘宝价¥"+ goodBean.getPrice1());
                StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                spannableString.setSpan(strikethroughSpan,0,spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                ((BannerHolder) holder).price1.setText(spannableString);

                ((BannerHolder) holder).goodsId.setText("货号"+ goodBean.getGno());

            }else if(holder instanceof ShopHolder){
                if(shopBean!=null){
                    GlideUtil.loadImage(shopBean.getSerller_head_original(),((ShopHolder) holder).shopImg);
                    ((ShopHolder) holder).major.setText(shopBean.getMajor());
                    ((ShopHolder) holder).shopName.setText(shopBean.getShop_name());
                    ((ShopHolder) holder).discount.setText(shopBean.getDiscount());
                    ((ShopHolder) holder).position.setText(shopBean.getMarket()+"-"+ shopBean.getFloor()+"-"+ shopBean.getDangkou());
                    ((ShopHolder) holder).goShop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, GoodsActivity.class);
                            intent.putExtra("shopbean",shopBean);
                            mContext.startActivity(intent);
                        }
                    });
                }


            }else  if(holder instanceof TypeHolder){
                if(type==TYPE_PIC){
                    ((TypeHolder) holder).goodPic.setTextColor(Color.RED);
                    ((TypeHolder) holder).goodParam.setTextColor(Color.BLACK);
                    ((TypeHolder) holder).goodParam.setOnClickListener(new DetailClickListener());

                }else {
                    ((TypeHolder) holder).goodPic.setTextColor(Color.BLACK);
                    ((TypeHolder) holder).goodParam.setTextColor(Color.RED);
                    ((TypeHolder) holder).goodPic.setOnClickListener(new DetailClickListener());
                }


            } else if(holder instanceof PicTextHolder){
                if(type==TYPE_PIC &imgList!=null){
                    GlideUtil.loadImage(imgList.get(position-3),((PicTextHolder) holder).pic);
                }else{
                    ((PicTextHolder) holder).detail.setText(goodBean.getAttributes().get(position-3));
                }
            }
        }




    }

    class BannerHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.detail_banner)
        Banner banner;
        @BindView(R.id.tv_title)
        TextView title;
        @BindView(R.id.tv_price2)
        TextView price2;
        @BindView(R.id.tv_price1)
        TextView price1;
        @BindView(R.id.tv_img_search)
        TextView imgSearch;
        @BindView(R.id.tv_search_titile)
        TextView searchTitle;
        @BindView(R.id.tv_add_list)
        TextView addList;
        @BindView(R.id.tv_goods_id)
        TextView goodsId;
        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        @OnClick({R.id.tv_img_search,R.id.tv_search_titile,R.id.tv_add_list})
        void alarm(){
            MyUtil.toast(mContext);
        }
    }

    class ShopHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_img)
        ImageView shopImg;
        @BindView(R.id.tv_shop)
        TextView shopName;
        @BindView(R.id.tv_discount)
        TextView discount;
        @BindView(R.id.tv_position)
        TextView position;
        @BindView(R.id.tv_major)
        TextView major;
        @BindView(R.id.bt_go_shop)
        Button goShop;
        public ShopHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class TypeHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.good_pic)
        TextView goodPic;
        @BindView(R.id.good_param)
        TextView goodParam;
        public TypeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class PicTextHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_pic)
        ImageView pic;
        @BindView(R.id.tv_detail)
        TextView detail;
        public PicTextHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class DetailClickListener implements View.OnClickListener{
//        int type;

//        public DetailClickListener(int type) {
//            this.type = type;
//        }

        @Override
        public void onClick(View view) {
            if(type==TYPE_PIC){
                type=TYPE_PARAM;
            }else if(type==TYPE_PARAM){
                type=TYPE_PIC;
            }

            notifyDataSetChanged();
        }
    }
    public GoodBean getGoodBean() {
        return goodBean;
    }

    public void setGoodBean(GoodBean goodBean) {
        this.goodBean = goodBean;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public ShopBean getShopBean() {
        return shopBean;
    }

    public void setShopBean(ShopBean shopBean) {
        this.shopBean = shopBean;
    }

}
