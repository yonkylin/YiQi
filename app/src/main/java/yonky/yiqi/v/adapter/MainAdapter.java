package yonky.yiqi.v.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.yiqi.R;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.util.GlideImageLoader;
import yonky.yiqi.util.MyUtil;

/**
 * Created by Administrator on 2018/5/10.
 */

//public class MainAdapter extends DelegateAdapter.Adapter {
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = MainAdapter.class.getSimpleName();

    public static final int TYPE_BANNER =0x01;   //轮播
    public static final int TYPE_MY=0X02;   //第二行
    public static final int TYPE_TITLE=0x03;//标题
    public static final int TYPE_SINGLE=0X04;    //推荐宝贝 单栏
    public static final int TYPE_TWO=0X05;    //分2栏
    public static final int TYPE_THREE=0X06;    //分3栏

    public static final int TYPE_ITEM_MY=0X10;    //嵌套recyclerview 我的
    public static final int TYPE_ITEM_TJBB=0X11;    //嵌套recyclerview 推荐宝贝
    public static final int TYPE_ITEM_MRXK=0X12;    //嵌套recyclerview 每日新款


    private  List<AreaBean>bannerList;
    private List<AreaBean>b1List;
    private List<AreaBean>b2List;
    private List<AreaBean>c1List;
    private List<AreaBean>c2List;
    private String[] titles;


    private Context mContext;
//    private LayoutHelper mLayoutHelper;
//    private RecyclerView.LayoutParams mLayoutParams;
//    private int count =0;


//    public MainAdapter(Context context, LayoutHelper layoutHelper, int count){
    public MainAdapter(Context context){
//        this(context,layoutHelper,count,new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300));
//    }
//    public MainAdapter(Context context,LayoutHelper layoutHelper,int count, @NonNull RecyclerView.LayoutParams layoutParams){
        this.mContext = context;
//        this.mLayoutHelper = layoutHelper;
//        this.count = count;
//        this.mLayoutParams = layoutParams;


        titles = new String[]{"推荐宝贝","精品热卖","每日新款"};
    }

    @Override
    public int getItemViewType(int position) {
        switch(position){
            case 0:
                return TYPE_BANNER;
            case 1:
            case 4:
                return TYPE_MY;
            case 2:
            case 5:
            case 13:
                return TYPE_TITLE;
            case 3:
            case 6:
                return TYPE_SINGLE;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:

                return TYPE_THREE;

        }
//        if(position==0){
//            return TYPE_BANNER;
//        }else if(position ==1 || position==4){
//            return TYPE_MY;
//        }else if(position==2){
//            return TYPE_TITLE;
//        }else if(position ==3){
//            return TYPE_SINGLE;
//        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 14;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_BANNER:
                return new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_banner,parent,false));
            case TYPE_MY:
                return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_my,parent,false));
            case TYPE_TITLE:
                return new TitleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_title,parent,false));
            case TYPE_SINGLE:
            case TYPE_THREE:
                return new SingleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_single_image,parent,false));
                default:
                    return null;
        }
         }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArrayList<String>images = new ArrayList<>();
        Log.d("yonky","onBindViewHoder");

        if(holder instanceof BannerViewHolder && bannerList!=null){
                for(int i=0;i<bannerList.size();i++){
                    images.add(bannerList.get(i).getImg_url());
                }
                ((BannerViewHolder) holder).banner.setImages(images).setImageLoader(new GlideImageLoader()).start();


        }else if(holder instanceof MyViewHolder) {

            if(position ==1) {
                ((MyViewHolder) holder).itemAdapter.setType(TYPE_ITEM_MY);
            }else if(b2List!=null && position==4){
                    ((MyViewHolder) holder).itemAdapter.setB2List(b2List);
                    ((MyViewHolder) holder).itemAdapter.setType(TYPE_ITEM_TJBB);
            }

        }else if(holder instanceof TitleViewHolder){
            switch(position){
                case 2:
                    ((TitleViewHolder) holder).title.setText(titles[0]);
                    break;
                case 5:
                    ((TitleViewHolder) holder).title.setText(titles[1]);
                    break;
                case 13:
                    ((TitleViewHolder) holder).title.setText(titles[2]);
                    break;
                    default:
            }

        }else if(holder instanceof SingleViewHolder ){
//            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,MyUtil.dp2px(mContext,140));
            ViewGroup.LayoutParams layoutParams =((SingleViewHolder) holder).iv.getLayoutParams();
            layoutParams.width=ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height=MyUtil.dp2px(mContext,140);
            ((SingleViewHolder) holder).iv.setLayoutParams(layoutParams);
            if(b1List!=null &&position==3){
                Glide.with(mContext).load(b1List.get(0).getImg_url()).into(((SingleViewHolder) holder).iv);
            }
            if(c1List!=null&&position==6){
                Glide.with(mContext).load(c1List.get(0).getImg_url()).into(((SingleViewHolder) holder).iv);
            }
            if(c2List!=null && position>=7 &&position<=12){
                layoutParams.height=MyUtil.dp2px(mContext,140);
                Glide.with(mContext).load(c2List.get(position-7).getImg_url()).into(((SingleViewHolder) holder).iv);
            }

        }
    }


//    @Override
//    public LayoutHelper onCreateLayoutHelper() {
//        return mLayoutHelper;
//    }
    class BannerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.banner)
        Banner banner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_rv)
        RecyclerView rv;
        RecyclerView.LayoutManager itemLayoutManager;
        ItemAdapter itemAdapter;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
            itemAdapter = new ItemAdapter(mContext);
            rv.setLayoutManager(itemLayoutManager);
            rv.setAdapter(itemAdapter);

        }


    }
    class TitleViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView title;
        public TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class SingleViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_single)
        ImageView iv;
        public SingleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }


    public void setBannerList(List<AreaBean> bannerList) {
        this.bannerList = bannerList;
    }
    public List<AreaBean> getBannerList(){
        return this.bannerList;
    }

    public List<AreaBean> getB1List() {
        return b1List;
    }

    public void setB1List(List<AreaBean> b1List) {
        this.b1List = b1List;
    }

    public List<AreaBean> getB2List() {
        return b2List;
    }

    public void setB2List(List<AreaBean> b2List) {
        this.b2List = b2List;
    }

    public List<AreaBean> getC1List() {
        return c1List;
    }

    public void setC1List(List<AreaBean> c1List) {
        this.c1List = c1List;
    }

    public List<AreaBean> getC2List() {
        return c2List;
    }

    public void setC2List(List<AreaBean> c2List) {
        this.c2List = c2List;
    }
}
