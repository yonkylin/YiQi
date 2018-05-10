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
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.yiqi.R;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.util.GlideImageLoader;

/**
 * Created by Administrator on 2018/5/10.
 */

public class MainAdapter extends DelegateAdapter.Adapter {
    public static final int TYPE_BANNER =0x01;   //轮播
    public static final int TYPE_MY=0X02;   //第二行
    public static final int TYPE_TITLE=0x03;//标题
    public static final int TYPE_C=0X04;    //推荐宝贝
    public static final int TYPE_D=0X05;    //精品热卖
    public static final int TYPE_E=0X06;    //每日新款


    private  List<AreaBean>bannerList;
    private String[] titles;


    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private RecyclerView.LayoutParams mLayoutParams;
    private int count =0;


    public MainAdapter(Context context, LayoutHelper layoutHelper, int count, List<AreaBean> bannerList){
        this(context,layoutHelper,count,new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300),bannerList);
    }
    public MainAdapter(Context context,LayoutHelper layoutHelper,int count, @NonNull RecyclerView.LayoutParams layoutParams,List<AreaBean>bannerList){
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.count = count;
        this.mLayoutParams = layoutParams;
        this.bannerList = bannerList;
        titles = new String[]{"推荐宝贝","精品热卖","每日新款"};
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_BANNER;
        }else if(position ==1){
            return TYPE_MY;
        }else if(position==2){
            return TYPE_TITLE;
        }
        return super.getItemViewType(position);
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
                default:
                    return null;
        }
         }

    public void setBannerList(List<AreaBean> bannerList) {
        this.bannerList = bannerList;
    }
    public List<AreaBean> getBannerList(){
       return this.bannerList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArrayList<String>images = new ArrayList<>();
        Log.d("yonky","onBindViewHoder");
        for(int i=0;i<bannerList.size();i++){
            images.add(bannerList.get(i).getImg_url());
        }
        if(holder instanceof BannerViewHolder){
            ((BannerViewHolder) holder).banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        }else if(holder instanceof TitleViewHolder){
            ((TitleViewHolder) holder).title.setText(titles[1]);

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }
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
            itemAdapter = new ItemAdapter();
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

}
