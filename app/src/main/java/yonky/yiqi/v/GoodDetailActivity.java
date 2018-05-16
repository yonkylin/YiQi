package yonky.yiqi.v;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseActivity;
import yonky.yiqi.base.contract.GoodDetailContract;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.p.GoodDetailPresenter;
import yonky.yiqi.v.adapter.DetaiAdapter;

/**
 * Created by Administrator on 2018/5/15.
 */

public class GoodDetailActivity extends BaseActivity implements GoodDetailContract.View {
    private static final String TAG=GoodDetailActivity.class.getSimpleName();
    private static int[] drawables={R.drawable.dangkou,R.drawable.img_search_ic,R.drawable.collect_ic};
    private static String[] titles={"档口","图搜","收藏","联系档口","一键上传"};
    @BindView(R.id.rv_detail)
    RecyclerView recyclerView;
    @BindView(R.id.tab_layout)
    TabLayout tab;
    DetaiAdapter mAdapter;
    GoodDetailPresenter mPresenter;
    GoodFilterBean filterBean;
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
        setTabs(tab,LayoutInflater.from(mContext),drawables,titles);
        AreaBean bean = (AreaBean) getIntent().getSerializableExtra("areabean");
        mPresenter = new GoodDetailPresenter(mContext);
        mPresenter.attachView(this);
        filterBean = new GoodFilterBean();
        filterBean.setSpm(bean.getSpm());
        filterBean.setGoods_id(String.valueOf(bean.getGoods_id()));
        Log.d(TAG,bean.getSpm());
        mAdapter = new DetaiAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);

        mPresenter.loadGoodDetail(filterBean);
        mPresenter.loadImgs(filterBean);
    }

    @Override
    public void showResult(GoodBean goodBean) {
        Log.d(TAG,"show result"+goodBean.getShop_name());
        mAdapter.setBean(goodBean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showImgs(List<String> imgList) {
        mAdapter.setImgList(imgList);
        mAdapter.notifyDataSetChanged();
    }

    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] icons, String[] titles){
        for(int i =0;i<titles.length;i++){

            View view =inflater.inflate(R.layout.item_test,null);
            TabLayout.Tab tab =tabLayout.newTab();
            tab.setCustomView(view);
            LinearLayout parent=view.findViewById(R.id.ll);

//            parent.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1f));

            TextView title = view.findViewById(R.id.tv_tab);
            title.setText(titles[i]);

            if(i<drawables.length){
                ImageView icon=view.findViewById(R.id.iv_tab);
                icon.setImageResource(icons[i]);
//                parent.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
//            }else{
//                parent.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,2f));
            }
            tabLayout.addTab(tab);

        }
    }

}
