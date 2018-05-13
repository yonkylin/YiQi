package yonky.yiqi.base.contract;

import java.util.List;

import yonky.yiqi.base.BasePresenter;
import yonky.yiqi.base.BaseView;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.bean.ShopFilterBean;

public interface StyleContract {
    interface View extends BaseView{
        void showResult(List<GoodBean> beanList);
    }
    interface Presenter extends BasePresenter<View>{
        void loadDatas(GoodFilterBean filter);
    }

}
