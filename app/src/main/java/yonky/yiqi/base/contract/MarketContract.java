package yonky.yiqi.base.contract;

import java.util.List;

import yonky.yiqi.base.BasePresenter;
import yonky.yiqi.base.BaseView;
import yonky.yiqi.bean.ItemsBean;

/**
 * Created by Administrator on 2018/5/12.
 */

public interface MarketContract {
    interface View extends BaseView{
       void showResult(List<ItemsBean> listBeans);
    }
    interface Presenter extends BasePresenter<View>{
        void loadData();
        void loadMore();
    }
}
