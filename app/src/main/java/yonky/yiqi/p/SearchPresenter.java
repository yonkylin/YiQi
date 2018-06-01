package yonky.yiqi.p;

import android.content.Context;

import yonky.yiqi.base.contract.SearchContract;
import yonky.yiqi.bean.GoodFilterBean;

/**
 * Created by Administrator on 2018/6/1.
 */

public class SearchPresenter implements SearchContract.Presenter {
    Context mContext;
    SearchContract.View mView;

    public SearchPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void loadData(GoodFilterBean filterBean, Boolean loadingMore) {

    }

    @Override
    public void attachView(SearchContract.View view) {
        this.mView=view;
    }

    @Override
    public void detachView() {
        mView=null;
    }
}
