package yonky.yiqi;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.yiqi.widget.MyViewPager;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.viewpager)
    MyViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;


    private String[] mTitles;
    private List<View> mViewList;
    int[] drawables = new int[]{R.drawable.tab_home_selector,R.drawable.tab_market_selector,R.drawable.tab_style_selector,
            R.drawable.tab_list_selector,R.drawable.tab_me_selector};

//    待删除
    private View view1,view2,view3,view4,view5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mViewList = new ArrayList<>();
        mTitles = new String[]{"首页","逛市场","搜款式","采购单","我的"};

        LayoutInflater inflater = getLayoutInflater();
        view1 =inflater.inflate(R.layout.layout1,null);
        view2 =inflater.inflate(R.layout.layout2,null);
        view3 =inflater.inflate(R.layout.layout3,null);
        view4=inflater.inflate(R.layout.layout4,null);
        view5 =inflater.inflate(R.layout.layout5,null);
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        mViewList.add(view4);
        mViewList.add(view5);

        setTabs(mTabLayout,getLayoutInflater(),drawables,mTitles);

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mViewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(mViewList.get(position));
                return mViewList.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(mViewList.get(position));
            }
        };
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    private void setTabs(TabLayout tabLayout,LayoutInflater inflater,int[] icons,String[] titles){
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

}
