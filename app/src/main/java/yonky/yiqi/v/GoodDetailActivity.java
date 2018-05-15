package yonky.yiqi.v;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
    @BindView(R.id.rv_detail)
    RecyclerView recyclerView;
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

    }

    @Override
    public void showResult(GoodBean goodBean) {
        Log.d(TAG,"show result"+goodBean.getShop_name());
        mAdapter.setBean(goodBean);
        mAdapter.notifyDataSetChanged();
    }
}
