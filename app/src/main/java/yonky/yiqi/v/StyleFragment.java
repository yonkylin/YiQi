package yonky.yiqi.v;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.base.contract.StyleContract;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.p.StylePresenter;
import yonky.yiqi.v.adapter.StyleAdapter;

public class StyleFragment extends BaseFragment implements StyleContract.View {
    @BindView(R.id.rv_style)
    RecyclerView recyclerView;
     StyleAdapter styleAdapter;
     StylePresenter mPresenter;
    List<GoodBean> goodBeans;

    GoodFilterBean filterBean;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_style;
    }

    @Override
    protected void initEventAndData() {
        mPresenter= new StylePresenter(mContext);
        mPresenter.attachView(this);

        goodBeans = new ArrayList<>();
        filterBean = new GoodFilterBean();
        styleAdapter= new StyleAdapter(mContext,goodBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerView.setAdapter(styleAdapter);

        mPresenter.loadDatas(filterBean);
    }

    @Override
    public void showResult(List<GoodBean> beanList) {
        styleAdapter.setBeanList(beanList);
        styleAdapter.notifyDataSetChanged();
    }
}
