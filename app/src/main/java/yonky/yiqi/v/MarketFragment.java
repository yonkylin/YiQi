package yonky.yiqi.v;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.base.contract.MarketContract;
import yonky.yiqi.bean.ItemsBean;
import yonky.yiqi.m.DataManager;
import yonky.yiqi.p.MarketPresenter;
import yonky.yiqi.v.adapter.MarketAdapter;

/**
 * Created by Administrator on 2018/5/12.
 */

public class MarketFragment extends BaseFragment implements MarketContract.View{
    @BindView(R.id.rv_market)
    RecyclerView recyclerView;
    MarketAdapter mMarketAdapter;
    List<ItemsBean> itemsBean;

    MarketPresenter mPresenter;

        @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    protected void initEventAndData() {
        mPresenter =new MarketPresenter(mContext);
        mPresenter.attachView(this);

        itemsBean = new ArrayList<>();
        mMarketAdapter = new MarketAdapter(mContext,itemsBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mMarketAdapter);
        mPresenter.loadData();
    }

    @Override
    public void showResult(List<ItemsBean> beanList) {
        mMarketAdapter.setBeanList(beanList);
        mMarketAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        if(mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }
}