package yonky.yiqi.v;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseActivity;
import yonky.yiqi.base.contract.MainContract;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.bean.MainPageBean;
import yonky.yiqi.p.MainPresenter;
import yonky.yiqi.v.adapter.ViewPagerAdaper;
import yonky.yiqi.widget.MyViewPager;


public class MainActivity extends BaseActivity implements MainContract.View{
    @BindView(R.id.viewpager)
    MyViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.button)
    Button bt;

    List<Fragment> fragments;


    private String[] mTitles;
    private List<View> mViewList;
    int[] drawables = new int[]{R.drawable.tab_home_selector,R.drawable.tab_market_selector,R.drawable.tab_style_selector,
            R.drawable.tab_list_selector,R.drawable.tab_me_selector};


    MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();


        setTabs(mTabLayout,getLayoutInflater(),drawables,mTitles);
        ViewPagerAdaper pagerAdapter = new ViewPagerAdaper(getSupportFragmentManager(),fragments);


        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mPresenter.attachView(this);
        mPresenter.loadDatas();

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
    public void initView() {
        mPresenter = new MainPresenter(this);
        mViewList = new ArrayList<>();
        mTitles = new String[]{"首页","逛市场","搜款式","采购单","我的"};


        fragments = new ArrayList<>();
        MainFragment mainFragment = new MainFragment();
        FragmentTest mainFragment2 = new FragmentTest();
        MainFragment mainFragment3 = new MainFragment();
        MainFragment mainFragment4 = new MainFragment();
        MainFragment mainFragment5 = new MainFragment();
        fragments.add(mainFragment);
        fragments.add(mainFragment2);
        fragments.add(mainFragment3);
        fragments.add(mainFragment4);
        fragments.add(mainFragment5);
    }

    @Override
    public void showResult(List<AreaBean> areaABeanList, int type) {
        bt.setText(areaABeanList.get(0).getTitle());
    }
}
