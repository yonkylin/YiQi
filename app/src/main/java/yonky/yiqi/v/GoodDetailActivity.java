package yonky.yiqi.v;

import android.animation.ObjectAnimator;
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
//    @BindView(R.id.tab_layout)
//    TabLayout tab;
    DetaiAdapter mAdapter;
    GoodDetailPresenter mPresenter;
    GoodFilterBean goodFilter;
    ShopFilterBean shopFilter;
    ShopBean mShopBean;

    boolean isLoadingMore;
    int mdy;

//    SharedPreferences preferences;
    @Override
    protected int getLayout() {
        return R.layout.activity_good_detail;
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void initEventAndData() {
        fab.hide();
//        preferences=mContext.getSharedPreferences("data",0);
//        String regionId=preferences.getString("regionId","42");
//        setTabs(tab,LayoutInflater.from(mContext),drawables,titles);

           AreaBean areaBean = (AreaBean)getIntent().getSerializableExtra("areabean");
      GoodBean goodBean =(GoodBean)getIntent().getSerializableExtra("goodbean");

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
                mdy+=dy;
                setToolbarTansparent(mdy,MyUtil.dp2px(mContext,250));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });

    }

    private void setToolbarTansparent(int dy,int limit){
        if(dy<limit&&dy>=0){
            int fraction=dy*255/limit;
//            int alpha=255*dy/limit;
//            String alpha=Integer.toHexString(255*dy/limit);
//            if(alpha.length()==1){
//                alpha="0"+alpha;
//            }
//            Log.e(TAG,"#"+alpha+"ffffff");
//            mToolbar.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(alpha)+"ffffff"));
            mToolbar.getBackground().mutate().setAlpha(fraction);
        }
    }

    @OnClick(R.id.connect) void setTvConnect(){
//        新建窗口是有状态栏的，大概25dp
        if(mShopBean!=null){
            int height =csLayout.getHeight()-mLinearLayout.getHeight()-MyUtil.dp2px(mContext,24);

            PopupWindow popupWindow = new ConnectWindow(mContext,mShopBean).newWindow(height);

            popupWindow.showAtLocation(mLinearLayout,Gravity.NO_GRAVITY,0,0);
        }


    }


    @Override
    public void showResult(GoodBean goodBean) {

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
     final  PopupWindow popupWindow = new PopupWindow(contentView,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true){
         @Override
         public void showAtLocation(View parent, int gravity, int x, int y) {
             super.showAtLocation(parent, gravity, x, y);
         }
     };
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




}
