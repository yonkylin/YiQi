package yonky.yiqi.v;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.base.contract.MarketContract;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.bean.ShopFilterBean;
import yonky.yiqi.p.MarketPresenter;
import yonky.yiqi.v.adapter.MarketAdapter;

/**
 * Created by Administrator on 2018/5/12.
 */

public class MarketFragment extends BaseFragment implements MarketContract.View{
    @BindView(R.id.rv_market)
    RecyclerView recyclerView;
    MarketAdapter mMarketAdapter;
    List<ShopBean> shopBean;
    ShopFilterBean shopFilter;

    MarketPresenter mPresenter;

        @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    protected void initEventAndData() {
        mPresenter =new MarketPresenter(mContext);
        mPresenter.attachView(this);

        shopBean = new ArrayList<>();
        shopFilter= new ShopFilterBean();

        mMarketAdapter = new MarketAdapter(mContext, shopBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mMarketAdapter);
        mPresenter.loadData(shopFilter);
    }

    @Override
    public void showResult(List<ShopBean> beanList) {
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
