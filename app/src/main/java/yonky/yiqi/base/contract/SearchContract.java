package yonky.yiqi.base.contract;

import java.util.List;

import yonky.yiqi.base.BasePresenter;
import yonky.yiqi.base.BaseView;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;

/**
 * Created by Administrator on 2018/6/1.
 */

public interface SearchContract {
    interface View extends BaseView {
        void showResult(List<GoodBean> listBeans, Boolean loadingMore);
    }
    interface Presenter extends BasePresenter<View> {
        void loadData(GoodFilterBean filterBean, Boolean loadingMore);
    }
}
