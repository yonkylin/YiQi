package yonky.yiqi.v;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseActivity;
import yonky.yiqi.base.contract.GoodContract;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.bean.ShopFilterBean;
import yonky.yiqi.p.GoodPresenter;
import yonky.yiqi.util.GlideUtil;
import yonky.yiqi.util.MyUtil;
import yonky.yiqi.v.main.adapter.StyleAdapter;
import yonky.yiqi.window.ConnectWindow;

import static yonky.yiqi.base.Constants.IS_FROM_AREA;
import static yonky.yiqi.base.Constants.IS_FROM_SHOP;
import static yonky.yiqi.v.main.adapter.StyleAdapter.TYPE_NODATA;

/**
 * Created by Administrator on 2018/5/14.
 */

public class GoodsActivity extends BaseActivity implements GoodContract.View{
    public static final String TAG=GoodsActivity.class.getSimpleName();


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.titile)
    TextView title;
    @BindView(R.id.rv_goods)
    RecyclerView mRecyclerView;
    @BindView(R.id.iv_shop)
    ImageView mShopBackground;
    @BindView(R.id.iv_avatar)
    ImageView mAvatar;
    @BindView(R.id.tv_shop_name)
    TextView mShopName;
    @BindView(R.id.tv_position)
    TextView mPosition;
    @BindView(R.id.fab)
        FloatingActionButton fab;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.ll)
    LinearLayout linearLayout;
//    @BindView(R.id.tab_layout)
//    TabLayout tabLayout;
    boolean isLoadingMore;
    StyleAdapter mAdapter;
    List<GoodBean> mGoodList;
    GoodFilterBean goodFilter;
    ShopFilterBean shopFilter;
    GoodPresenter mPresenter;
    ShopBean mShopBean;
    int comeFrom;

    int mdy;
    @Override
    protected int getLayout() {
        return R.layout.activity_goods;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initEventAndData() {
        mToolbar.getBackground().mutate().setAlpha(0);
        fab.hide();
        mPresenter = new GoodPresenter(mContext);
        mPresenter.attachView(this);



        AreaBean areaBean =(AreaBean) getIntent().getSerializableExtra("areabean");
        ShopBean shopBean=(ShopBean) getIntent().getSerializableExtra("shopbean");
        if(shopBean!=null){
            comeFrom=IS_FROM_SHOP;
        }else {
            comeFrom=IS_FROM_AREA;
        }

        mGoodList = new ArrayList<>();
        goodFilter = new GoodFilterBean();
        shopFilter= new ShopFilterBean();

        if(comeFrom==IS_FROM_AREA){
//            设置过滤条件
            goodFilter.setShop_id(areaBean.getShop_id());
            goodFilter.setSpm(areaBean.getSpm());
            goodFilter.setZdid(areaBean.getSite_id());
            shopFilter.setShop_id(areaBean.getShop_id());
            shopFilter.setSpm(areaBean.getSpm());
            shopFilter.setZdid(areaBean.getSite_id());

        }else{
            goodFilter.setShop_id(shopBean.getShop_id());
            goodFilter.setSpm(shopBean.getSpm());
            goodFilter.setZdid(shopBean.getSite_id());
            shopFilter.setShop_id(shopBean.getShop_id());
            shopFilter.setSpm(shopBean.getSpm());
            shopFilter.setZdid(shopBean.getSite_id());

        }



        mAdapter = new StyleAdapter(mContext,mGoodList);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if( mAdapter.getItemViewType(position)==TYPE_NODATA){
                    return 2;
                }else{
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mPresenter.loadGoods(goodFilter,false);
        mPresenter.loadShop(shopFilter);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstItemPosition =((GridLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                int lastItemPosition =((GridLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalCount =recyclerView.getLayoutManager().getItemCount();
                if(firstItemPosition<2||dy>0 ){
                    fab.hide();
                }else{
                    fab.show();
                }

//                最后一个显示时，加载更多
                if(lastItemPosition>=totalCount-1&&dy>0){
                    if(!isLoadingMore){
                        isLoadingMore=true;
                        int page=Integer.valueOf(goodFilter.getPindex())+1;
                        goodFilter.setPindex(String.valueOf(page));
                        mPresenter.loadGoods(goodFilter,true);
                    }
                }

                mdy+=dy;
                setToolbarTansparent(mdy,MyUtil.dp2px(mContext,250));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });



//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                doShow(tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                doShow(tab.getPosition());
//            }
//        });
    }


    @OnClick(R.id.tv_lxdk)
    void pop(){
        if(mShopBean!=null){
            int[] positions=new int[2];
            linearLayout.getLocationInWindow(positions);
//            PopupWindow popupWindow = new ConnectWindow(mContext,mShopBean).newWindow(positions[1]- MyUtil.dp2px(mContext,24));
            PopupWindow popupWindow = new ConnectWindow(mContext,mShopBean).newWindow();

            popupWindow.showAtLocation(mToolbar, Gravity.NO_GRAVITY,0,0);
        }
    }
    @OnClick(R.id.tv_dkjj)
    void dkjj(){
    Intent intent = new Intent(mContext,ActivityFragment.class);
    intent.putExtra("shopbean",mShopBean);
    mContext.startActivity(intent);
 }
    @OnClick(R.id.tv_bbfl)
    void alarm(){
        MyUtil.toast(mContext);
    }




//关于改变背景透明度，view.getBackground().setAlpha()，设置时会改变背景设置的资源文件的透明度。
//    这样导致使用到此资源文件的透明度都将会被改变。
//    如果想改变具体的view的背景，可以使用mustate()方法
//    即 view.getBackground().mustate().setAlpha()，这样就只是改变这个view的透明度


    private void setToolbarTansparent(int dy,int limit) {
        if (dy < limit && dy >= 0) {
            int fraction = dy * 255 / limit;
            int alpha=255*dy/limit;

            mToolbar.getBackground().mutate().setAlpha(alpha);
            title.setAlpha(fraction);

        }
    }
    @Override
    public void showShop(ShopBean shopBean) {
        this.mShopBean=shopBean;
        GlideUtil.loadRoundImage(shopBean.getSerller_head_original(),mAvatar);
        mShopName.setText(shopBean.getShop_name());
//        mToolbar.setTitle(shopBean.getShop_name());
        mPosition.setText(shopBean.getMarket()+"-"+shopBean.getFloor()+"-"+shopBean.getDangkou());
        BlurTransformation blurTransformation = new BlurTransformation(15,2);
//        MultiTransformation multi= new MultiTransformation(new BlurTransformation(15,2),new ColorFilterTransformation(R.color.light_gray));
        Glide.with(this).load(shopBean.getSerller_head_original())
                .apply(RequestOptions.bitmapTransform(blurTransformation))
                .into(mShopBackground);

        title.setText(mShopBean.getShop_name());
    }

    @Override
    public void showResult(List<GoodBean> list,boolean loadingMore) {
        if(loadingMore){
            isLoadingMore=false;
            mAdapter.getBeanList().addAll(list);
        }else{
            mAdapter.setBeanList(list);
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {
        mAdapter.setEmptyCount(1);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
