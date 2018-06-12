package yonky.yiqi.v;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import yonky.yiqi.R;
import yonky.yiqi.base.App;
import yonky.yiqi.base.BaseActivity;
import yonky.yiqi.base.contract.GoodDetailContract;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.bean.ShopFilterBean;
import yonky.yiqi.p.GoodDetailPresenter;
import yonky.yiqi.util.MyUtil;
import yonky.yiqi.v.adapter.DetaiAdapter;
import yonky.yiqi.widget.TextViewBottomLine;
import yonky.yiqi.window.ConnectWindow;

/**
 * Created by Administrator on 2018/5/15.
 */

public class GoodDetailActivity extends BaseActivity implements GoodDetailContract.View {
    private static final String TAG=GoodDetailActivity.class.getSimpleName();
//    private static int[] drawables={R.drawable.dangkou,R.drawable.img_search_ic,R.drawable.collect_ic};
//    private static String[] titles={"档口","图搜","收藏","联系档口","一键上传"};
    @BindView(R.id.rv_detail)
    RecyclerView recyclerView;
    @BindView(R.id.cs_layout)
    ConstraintLayout csLayout;
    @BindView(R.id.connect)
    TextView tvConnect;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.title)
    TextView mTitle;
//    @BindView(R.id.tab_layout)
//    TabLayout tab;
    DetaiAdapter mAdapter;
    GoodDetailPresenter mPresenter;
    GoodFilterBean goodFilter;
    ShopFilterBean shopFilter;
    ShopBean mShopBean;
    GoodBean mGoodBean;

    boolean isLoadingMore;
    int mdy;

//    SharedPreferences preferences;
    @Override
    protected int getLayout() {
        return R.layout.activity_good_detail;
    }


    GoodBean goodBean;
    @Override
    protected void initEventAndData() {
        fab.hide();
//        preferences=mContext.getSharedPreferences("data",0);
//        String regionId=preferences.getString("regionId","42");
//        setTabs(tab,LayoutInflater.from(mContext),drawables,titles);

           AreaBean areaBean = (AreaBean)getIntent().getParcelableExtra("areabean");
     goodBean =(GoodBean)getIntent().getParcelableExtra("goodbean");

        mPresenter = new GoodDetailPresenter(mContext);
        mPresenter.attachView(this);
        goodFilter = new GoodFilterBean();
        shopFilter=new ShopFilterBean();

        if(areaBean!=null){
            goodFilter.setSpm(areaBean.getSpm());
            goodFilter.setZdid(areaBean.getSite_id());
            goodFilter.setGoods_id(areaBean.getGoods_id());
            shopFilter.setShop_id(areaBean.getShop_id());
            shopFilter.setZdid(areaBean.getSite_id());
            shopFilter.setSpm(areaBean.getSpm());
        }else {
            goodFilter.setSpm(goodBean.getSpm());
            goodFilter.setZdid(goodBean.getSite_id());
            goodFilter.setGoods_id(goodBean.getGoods_id());
            shopFilter.setShop_id(goodBean.getShop_id());
            shopFilter.setZdid(goodBean.getSite_id());
            shopFilter.setSpm(goodBean.getSpm());
        }


        mAdapter = new DetaiAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);
        mPresenter.loadImgs(goodFilter);
        mPresenter.loadGoodDetail(goodFilter);
        mPresenter.getShopDetail(shopFilter);
//        mToolbar置于最顶层
        mToolbar.bringToFront();
//        setToolbarTansparent(0,250);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);
                int firstItemPosition =((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                int lastItemPosition =((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalCount =recyclerView.getLayoutManager().getItemCount();
                if(firstItemPosition<3||dy>0 ){
                    fab.hide();
                }else{
                    fab.show();
                }
//      坑
//      快速滑动时，会因为先滑到底部，然后图片再加载，导致recyclerview长度于滑动距离不一致。
//          这里加上判断，只有在第一个item时计算滑动距离
//                但是这样快速滑动也会出现mdy为负的现象
//                所以在调用setToolbarTansparent时判断，如果mdy<0时，mdy归零；
                if(firstItemPosition==0){
                    mdy+=dy;
                    setToolbarTansparent(mdy,MyUtil.dp2px(mContext,250));
                    Log.e(TAG,"mdy:"+mdy);
                }

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });

    }

    private void setToolbarTansparent(int dy,float limit){
        if(dy<0){
            mdy=0;
            dy=0;
        }
        if(dy<=limit&&dy>=0){
            int fraction=(int)(dy*255/limit);
            float alpha=dy/limit;
//            String alpha=Integer.toHexString(255*dy/limit);
//            if(alpha.length()==1){
//                alpha="0"+alpha;
//            }
//            Log.e(TAG,"#"+alpha+"ffffff");
//            mToolbar.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(alpha)+"ffffff"));
            mToolbar.getBackground().mutate().setAlpha(fraction);
            mTitle.setAlpha(alpha);
        }else if(dy>limit){
            mToolbar.getBackground().mutate().setAlpha(255);
        }
    }

    @OnClick(R.id.connect) void setTvConnect(){
//        新建窗口是有状态栏的，大概25dp
        if(mShopBean!=null){
//            int height =csLayout.getHeight()-mLinearLayout.getHeight()-MyUtil.dp2px(mContext,24);

            PopupWindow popupWindow = new ConnectWindow(mContext,mShopBean).newWindow();

            popupWindow.showAtLocation(csLayout,Gravity.NO_GRAVITY,0,0);
        }
    }
    @OnClick(R.id.shop) void setLlShop() {
        if(mShopBean!=null){
            Intent intent = new Intent(mContext, GoodsActivity.class);
            intent.putExtra("shopbean",mShopBean);
            mContext.startActivity(intent);
        }
    }
    @OnClick({R.id.collect,R.id.upload})
    void alarm(){
        MyUtil.toast(mContext);
    }

    @OnClick(R.id.img_search)
    void imgsearch(){
        if(mGoodBean!=null){
            Intent intent = new Intent(mContext,ActivityFragment.class);
//            不知道为什么这句会闪退,在stylefragment也可以传递过来
//            原来是GoodBean里面含有非序列化的属性。置为null即可。
            mGoodBean.setSku_attributes(null);
            mGoodBean.setSkus(null);
            intent.putExtra("goodbean",mGoodBean);
            intent.putExtra("searchtype","img");

            mContext.startActivity(intent);
        }

    }



    @Override
    public void showResult(GoodBean goodBean) {
        this.mGoodBean = goodBean;
        mTitle.setText(goodBean.getTitle());
        Log.d(TAG,"show result"+goodBean.getShop_name());
        mAdapter.setGoodBean(goodBean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showImgs(List<String> imgList) {
        Log.d(TAG,"showImgs() is go");
        mAdapter.setImgList(imgList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showShop(ShopBean shopBean) {
        this.mShopBean = shopBean;
        Log.d(TAG,"showShop() is go");
        mAdapter.setShopBean(shopBean);
        mAdapter.notifyDataSetChanged();
    }
@Override
    public void showError(){
       Log.d(TAG,"showError() is go");
         View contentView = LayoutInflater.from(mContext).inflate(R.layout.window_no_data,null);
     final  PopupWindow popupWindow = new PopupWindow(contentView,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.setContentView(contentView);
        ImageView iv=contentView.findViewById(R.id.window_iv);
//
        Button bt=contentView.findViewById(R.id.window_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                finish();
            }
        });

    View rootview = LayoutInflater.from(this).inflate(getLayout(), null);
    popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv,"translationX",0, MyUtil.dp2px(mContext,10),-MyUtil.dp2px(mContext,10), MyUtil.dp2px(mContext,10),0);
//        objectAnimator.setInterpolator(new BounceInterpolator());
    objectAnimator.setDuration(300);
    objectAnimator.setStartDelay(200);
    objectAnimator.start();

    }


    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();

    }

}
