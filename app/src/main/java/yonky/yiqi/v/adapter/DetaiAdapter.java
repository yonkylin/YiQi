package yonky.yiqi.v.adapter;

import android.content.Context;
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
import yonky.yiqi.R;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.util.GlideUtil;

public class DetaiAdapter extends RecyclerView.Adapter {
    public static final int TYPE_BANNER=0X001;
    public static final int TYPE_SHOP=0X002;
    public static final int TYPE_PIC=0X003;
    public static final int TYPE_PARAM=0X004;
    public static final int TYPE_STYLE=0X005;



    private Context mContext;
    private GoodBean bean;
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
        if(bean!=null){
            j=bean.getAttributes().size();
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
        if(bean!=null){
            if(holder instanceof BannerHolder){
                String s= bean.getTb_imgs();
                String[] imgs= s.split(",");
                List<String> urls= new ArrayList<>();
                for(int i=0;i<imgs.length;i++) {
                    urls.add(imgs[i]);
                }
                ((BannerHolder) holder).banner.setImages(urls).setImageLoader(new GlideUtil()).start();


                ((BannerHolder) holder).title.setText(bean.getTitle());
                ((BannerHolder) holder).price2.setText(String.format("¥ %.1f",bean.getPrice2()));
//             删除线
                SpannableString spannableString = new SpannableString("淘宝价¥"+bean.getPrice1());
                StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                spannableString.setSpan(strikethroughSpan,0,spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                ((BannerHolder) holder).price1.setText(spannableString);

                ((BannerHolder) holder).goodsId.setText("货号"+bean.getGno());

            }else if(holder instanceof ShopHolder){

                ((ShopHolder) holder).shopName.setText(bean.getShop_name());
                ((ShopHolder) holder).discount.setText(bean.getShop_youhui());
                ((ShopHolder) holder).position.setText(bean.getShop_market()+"-"+bean.getShop_floor()+"-"+bean.getShop_dangkou());
//                ((ShopHolder) holder).major.setText(bean.get);
            }else if(holder instanceof PicTextHolder){
                if(type==TYPE_PIC &imgList!=null){
                    GlideUtil.loadImage(imgList.get(position-3),((PicTextHolder) holder).pic);
                }else{
                    ((PicTextHolder) holder).detail.setText(bean.getAttributes().get(position-3));
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
        @BindView(R.id.bt_img_search)
        Button imgSearch;
        @BindView(R.id.bt_search_titile)
        Button searchTitle;
        @BindView(R.id.bt_add_list)
        Button addList;
        @BindView(R.id.tv_goods_id)
        TextView goodsId;
        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
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

    public GoodBean getBean() {
        return bean;
    }

    public void setBean(GoodBean bean) {
        this.bean = bean;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
