package yonky.yiqi.base.contract;

import yonky.yiqi.base.BasePresenter;
import yonky.yiqi.base.BaseView;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;

public interface GoodDetailContract {
    interface View extends BaseView{
        void showResult(GoodBean goodBean);
    }
    interface Presenter extends BasePresenter<View>{
        void loadGoodDetail(GoodFilterBean filter);
    }
}
