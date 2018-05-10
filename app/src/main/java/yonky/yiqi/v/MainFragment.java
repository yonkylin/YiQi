package yonky.yiqi.v;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.base.contract.MainContract;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.p.MainPresenter;
import yonky.yiqi.v.adapter.MainAdapter;

public class MainFragment extends BaseFragment implements MainContract.View{
    private static final String TAG = MainFragment.class.getSimpleName();
    @BindView(R.id.rv_main)
    RecyclerView recyclerView;
    MainContract.Presenter mPresenter;

    List<AreaBean> listItem;

    MainAdapter Adapter_linear;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void showResult(List<AreaBean> areaABeanList, String type) {
        if("A".equals(type)&& Adapter_linear.getBannerList().isEmpty() ){
            Adapter_linear.setBannerList(areaABeanList);
            Adapter_linear.notifyDataSetChanged();
        }

    }

    @Override
    protected void initEventAndData() {
        mPresenter = new MainPresenter(mContext);
        mPresenter.attachView(this);
        mPresenter.loadDatas("A");

        listItem = new ArrayList<>();
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager );

        RecyclerView.RecycledViewPool viewPool  =new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,4);

        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setItemCount(1);

        Adapter_linear=new MainAdapter(mContext,linearLayoutHelper,3,listItem);

        recyclerView.setAdapter(Adapter_linear);

    }
}
