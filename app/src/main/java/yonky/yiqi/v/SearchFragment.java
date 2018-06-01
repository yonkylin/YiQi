package yonky.yiqi.v;

import java.util.List;

import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.base.contract.SearchContract;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.p.SearchPresenter;

/**
 * Created by Administrator on 2018/6/1.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View {
//    SearchPresenter mPresenter;
    @Override
    protected int getLayoutId() {
//        return R.layout.fragment_search;
        return R.layout.layout2;
    }

    @Override
    protected void initEventAndData() {
//        mPresenter = new SearchPresenter(mContext);
//        mPresenter.attachView(this);


    }

    @Override
    public void showResult(List<GoodBean> listBeans, Boolean loadingMore) {

    }
}
