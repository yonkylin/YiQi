package yonky.yiqi.v.main;

import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseActivity;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.listener.MyListener;
import yonky.yiqi.util.MyUtil;
import yonky.yiqi.v.main.adapter.RegionAdapter;
import yonky.yiqi.v.main.adapter.ViewPagerAdaper;
import yonky.yiqi.widget.MyViewPager;


public class MainActivity extends BaseActivity implements MyListener {
//public class MainActivity extends BaseActivity  {
    @BindView(R.id.viewpager)
    MyViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.button)
    Button btRegion;

    int mCurrentPosition;

    MainFragment mainFragment;
    MarketFragment mainFragment2;
    StyleFragment mainFragment3;
    LoginFragment mainFragment4;
    MyFragment mainFragment5;

    List<BaseFragment> fragments;
    SharedPreferences preferences;
    PopupWindow mPopupWindow;
    RegionAdapter mRegionAdapter;
    String regionSelected;


    private String[] mTitles;
    private List<View> mViewList;
    int[] drawables = new int[]{R.drawable.tab_home_selector,R.drawable.tab_market_selector,R.drawable.tab_style_selector,
            R.drawable.tab_list_selector,R.drawable.tab_me_selector};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEventAndData();
//        设置离屏缓存个数，默认缓存左右的两个,
//        如下缓存数如果小于等于1的话是无效的。
//        mViewPager.setOffscreenPageLimit(0);
//       关于viewpager中fragment的生命周期请参考 https://blog.csdn.net/jemenchen/article/details/52645380
//      使用setUserVisibleHint()时，如果切换方式不是类似viewPager一样一页一页的切换，
//      而是通过点击底下tab跳跃式切换，会导致setUserVisibleHint不会被调用
//        设置缓存大小为全部缓存，然后使用了懒加载，可以避免此问题。（使每次切换都调用）

        mViewPager.setOffscreenPageLimit(4);
        setTabs(mTabLayout,getLayoutInflater(),drawables,mTitles);
        ViewPagerAdaper pagerAdapter = new ViewPagerAdaper(getSupportFragmentManager(),fragments);


        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                mCurrentPosition=mTabLayout.getSelectedTabPosition();
            }
        });

//        mPresenter.attachView(this);
//        mPresenter.loadDatas();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }
//设置底部Tab文字及图标
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] icons, String[] titles){
        for(int i =0;i<titles.length;i++){
            View view =inflater.inflate(R.layout.tab_item,null);
            TabLayout.Tab tab =tabLayout.newTab();
            tab.setCustomView(view);

            TextView title = view.findViewById(R.id.tv_tab);
            title.setText(titles[i]);

            ImageView icon=view.findViewById(R.id.iv_tab);
            icon.setImageResource(icons[i]);
            tabLayout.addTab(tab);

        }
    }

    @Override
    protected void initEventAndData() {
//        mPresenter = new MainPresenter(this);
        mViewList = new ArrayList<>();
        mTitles = new String[]{"首页","逛市场","搜款式","采购单","我的"};


        preferences= mContext.getSharedPreferences("data",0);
        regionSelected=preferences.getString("region","广州");
        btRegion.setText(regionSelected);



        fragments = new ArrayList<>();
        mainFragment = new MainFragment();
        mainFragment2 = new MarketFragment();
        mainFragment3 = new StyleFragment();
         mainFragment4 = new LoginFragment();
        mainFragment5 = new MyFragment();
//        MyFragment mainFragment = new MyFragment();
//        MyFragment mainFragment2 = new MyFragment();
//        MyFragment mainFragment3 = new MyFragment();
//        MyFragment mainFragment4 = new MyFragment();
//        MyFragment mainFragment5 = new MyFragment();
        fragments.add(mainFragment);
        fragments.add(mainFragment2);
        fragments.add(mainFragment3);
        fragments.add(mainFragment4);
        fragments.add(mainFragment5);
    }

    @OnClick(R.id.button)void regionButton(){
        showRegion();
    }
    private void showRegion(){
        View contentView= LayoutInflater.from(mContext).inflate(R.layout.window_region,null);
        mPopupWindow = new PopupWindow(contentView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT,true){
            @Override
            public void dismiss() {
                super.dismiss();
                updateButton();
            }
        };
        mPopupWindow.setContentView(contentView);

        RecyclerView rvRegion=contentView.findViewById(R.id.rv_region1);

        mRegionAdapter=new RegionAdapter(mContext,regionSelected);
        mRegionAdapter.setListener(this);
        FlexboxLayoutManager regionManager= new FlexboxLayoutManager();
        regionManager.setFlexDirection(FlexDirection.ROW);
        regionManager.setFlexWrap(FlexWrap.WRAP);

        rvRegion.setLayoutManager(regionManager);
        rvRegion.setAdapter(mRegionAdapter);
        mPopupWindow.showAsDropDown(btRegion,0, -MyUtil.dp2px(mContext,5));
    }
    private void updateButton(){
        String s=preferences.getString("region","广州");
        if(!s.equals(regionSelected)){
            regionSelected=s;
            btRegion.setText(s);
//            loadData();
//             fragments[mCurrentPosition].loadData();
        }

    }

    @Override
    public void onClick() {
        mPopupWindow.dismiss();
        updateButton();
    }
        @OnClick(R.id.imageView)
    void alarm(){
        MyUtil.toast(mContext);
    }

//    @Override
//    public void showResult(List<AreaBean> areaABeanList, int type) {
//        bt.setText(areaABeanList.get(0).getTitle());
//    }
}
