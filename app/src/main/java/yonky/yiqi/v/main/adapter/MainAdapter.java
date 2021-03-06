package yonky.yiqi.v.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.yiqi.R;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.bean.AreaEBean;
import yonky.yiqi.listener.MyClickListener;
import yonky.yiqi.util.GlideUtil;
import yonky.yiqi.util.MyUtil;
import yonky.yiqi.v.GoodsActivity;

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


    public static final int TYPE_GOODS =0X20;    //打开店铺详情
    public static final int TYPE_GOOD_DETAIL =0X21;    //打开商品详情
    public static final int TYPE_SERVICE =0X22;    //打开每日推荐，模特实拍等




    private  List<AreaBean>bannerList;
    private List<AreaBean>b1List;
    private List<AreaBean>b2List;
    private List<AreaBean>c1List;
    private List<AreaBean>c2List;
    private List<AreaBean>dList;

    private List<AreaEBean>eList;

    private String[] titles;
    private MyClickListener mMyClickListener;



    private Context mContext;

    public MainAdapter(Context context){

        this.mContext = context;
        titles = new String[]{"推荐宝贝","精品热卖","每日新款"};

        bannerList= new ArrayList<>();
        b1List=new ArrayList<>();
        b2List=new ArrayList<>();
        c1List=new ArrayList<>();
        c2List=new ArrayList<>();
        dList=new ArrayList<>();
        eList=new ArrayList<>();

    }
//这里暂时还不是动态的，待修改
    @Override
    public int getItemViewType(int position) {
        switch(position){
            case 0:
                return TYPE_BANNER;
            case 1:
            case 4:
            case 14:
                return TYPE_MY;
            case 2:
            case 5:
            case 13:
                return TYPE_TITLE;
            case 3:
            case 6:
            case 15:
            case 20:
            case 25:
            case 30:
            case 35:
                return TYPE_SINGLE;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return TYPE_THREE;
                default:
                    return TYPE_TWO;

        }
    }


    @Override
    public int getItemCount() {
//        1个我的收藏那栏，3个标题
//        c2List 6个网格布局
//        bannerList,b1List,b2List,c1List,dList, 有数据的话只有1个item
//        eList 一个item包含5个

        int countBaner=bannerList.size()==0? 0:1;
        int countB1=b1List.size()==0? 0:1;
        int countB2=b2List.size()==0? 0:1;
        int countC1=c1List.size()==0? 0:1;
        int countC2=c2List.size();
        int countD=dList.size()==0? 0:1;
       int countE= eList.size()*5;
        return 1+3+countBaner+countB1+countB2+countC1+countC2+countD+countE;
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
            case TYPE_TWO:
                return new TwoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_vertical,parent,false));
                default:
                    return null;
        }
         }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         AreaBean bean = new AreaBean();
        ArrayList<String>images = new ArrayList<>();
        Log.d("yonky","onBindViewHoder"+position);

        if(holder instanceof BannerViewHolder && bannerList.size()!=0){
                for(int i=0;i<bannerList.size();i++){
                    images.add(bannerList.get(i).getImg_url());
                }
                ((BannerViewHolder) holder).banner.setImages(images).setImageLoader(new GlideUtil()).start();
            ((BannerViewHolder) holder).banner.setOnBannerListener(new OnBannerListener() {
                AreaBean bannerBean;
                @Override
                public void OnBannerClick(int position) {
                    bannerBean = bannerList.get(position);
                    Intent intent = new Intent(mContext,GoodsActivity.class);
                    intent.putExtra("areabean",bannerBean);
                    mContext.startActivity(intent);

                }
            });


        }else if(holder instanceof MyViewHolder) {

            if(position ==1) {
                ((MyViewHolder) holder).itemAdapter.setType(TYPE_ITEM_MY);
            }else if(b2List.size()!=0 && position==4){
                    ((MyViewHolder) holder).itemAdapter.setB2List(b2List);
                    ((MyViewHolder) holder).itemAdapter.setType(TYPE_ITEM_TJBB);
            }else if(dList.size()!=0 && position==14){
                ((MyViewHolder) holder).itemAdapter.setDList(dList);
                ((MyViewHolder) holder).itemAdapter.setType(TYPE_ITEM_MRXK);

            }
            ((MyViewHolder) holder).itemAdapter.notifyDataSetChanged();
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
// 单张图的
        }else if(holder instanceof SingleViewHolder ){

            ViewGroup.LayoutParams layoutParams =((SingleViewHolder) holder).iv.getLayoutParams();
            layoutParams.width=ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height=MyUtil.dp2px(mContext,140);
            ((SingleViewHolder) holder).iv.setLayoutParams(layoutParams);
            if(b1List.size()!=0 &&position==3){
                bean =b1List.get(0);
                GlideUtil.loadImage(bean.getImg_url(),((SingleViewHolder) holder).iv);

            }else if(c1List.size()!=0&&position==6){
                bean=c1List.get(0);
                GlideUtil.loadImage(bean.getImg_url(),((SingleViewHolder) holder).iv);
            }else if(c2List.size()!=0 && position>=7 &&position<=12){
                bean = c2List.get(position-7);
                layoutParams.height=MyUtil.dp2px(mContext,140);
                GlideUtil.loadImage(bean.getImg_url(),((SingleViewHolder) holder).iv);
            }else if(eList.size()!=0&&position>14){
              /* 对应位置为
               15 20 25 30 35
                    -15
                0  5  10 15 20
                        对5取整
                0  1  2  3  4
  */
               int i = (position-15)/5;
                bean =eList.get(i).getM_Item1().get(0);
                GlideUtil.loadImage(bean.getImg_url(),((SingleViewHolder) holder).iv);
            }
            holder.itemView.setOnClickListener(new MyClickListener(mContext,bean, TYPE_GOODS));

//两栏有文字的
        } else if(holder instanceof TwoViewHolder && position>14){

           /* 两列类型的位置为    转为elist对应的位置
            16 17 18 19               1  2  3  4                             0
            21 22 23 24               6  7  8  9                             1
            26 27 28 29       -15得   11 12 13 14      对5取整               2
            31 32 33 34               16 17 18 19                            3
            36 37 38 39               21 22 23 24                            4
          */
           if(eList.size()!=0){
              int i=(position-15)/5;
              int j=(position-15)%5 -1;
               bean = eList.get(i).getM_Item2().get(j);
            GlideUtil.loadImage(bean.getImg_url(),((TwoViewHolder) holder).iv);
              ((TwoViewHolder) holder).title.setText(bean.getTitle());
              ((TwoViewHolder) holder).price.setText(mContext.getResources().getString(R.string.price,bean.getPrice()));
              holder.itemView.setOnClickListener(new MyClickListener(mContext,bean,TYPE_GOOD_DETAIL));
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

    class TwoViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_img)
        ImageView iv;
        @BindView(R.id.tv_title)
        TextView title;
        @BindView(R.id.tv_price)
        TextView price;

        public TwoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public void stopBanner(){

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

    public List<AreaBean> getDList() {
        return dList;
    }

    public void setDList(List<AreaBean> dList) {
        this.dList = dList;
    }

    public List<AreaEBean> geteList() {
        return eList;
    }

    public void seteList(List<AreaEBean> eList) {
        this.eList = eList;
    }


}
