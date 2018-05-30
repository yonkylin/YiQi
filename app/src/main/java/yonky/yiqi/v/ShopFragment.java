package yonky.yiqi.v;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;

/**
 * Created by Administrator on 2018/5/30.
 */

public class ShopFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.shopmain_fragment;
    }

    @Override
    protected void initEventAndData() {

    }
}
