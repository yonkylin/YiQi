package yonky.yiqi.v;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.util.MyUtil;
import yonky.yiqi.v.adapter.ShopAdapter;

/**
 * Created by Administrator on 2018/5/30.
 */

public class ShopFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.bt_tb)
    Button tbButton;
    private ShopBean mShopBean;
    private ShopAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.shopmain_fragment;
    }

    @Override
    protected void initEventAndData() {
        if(getArguments()!=null){
            mShopBean = (ShopBean)getArguments().getParcelable("shopbean");
//            mShopBean = new ShopBean();
            mAdapter= new ShopAdapter(mShopBean,mContext);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mRecyclerView.setAdapter(mAdapter);
        }

    }

@OnClick(R.id.bt_tb)
    void tbButton(){
    MyUtil.toast(mContext);
}
}
