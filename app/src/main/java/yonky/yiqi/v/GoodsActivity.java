package yonky.yiqi.v;

import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import yonky.yiqi.R;
import yonky.yiqi.base.BaseActivity;
import yonky.yiqi.base.contract.GoodContract;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.p.GoodPresenter;
import yonky.yiqi.v.adapter.StyleAdapter;

/**
 * Created by Administrator on 2018/5/14.
 */

public class GoodsActivity extends BaseActivity implements GoodContract.View{
    RecyclerView mRecyclerView;
    StyleAdapter mAdapter;
    List<GoodBean> mGoodList;
    GoodFilterBean filter;
    GoodPresenter mPresenter;
    @Override
    protected int getLayout() {
        return R.layout.activity_goods;
    }

    @Override
    protected void initEventAndData() {
        mPresenter = new GoodPresenter(mContext);
        mPresenter.attachView(this);

        mGoodList = new ArrayList<>();
        filter  = new GoodFilterBean();

        mAdapter = new StyleAdapter(mContext,mGoodList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.load(filter);

    }

    @Override
    public void showResult(List<GoodBean> list) {
        this.mGoodList=list;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
