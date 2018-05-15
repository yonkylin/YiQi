package yonky.yiqi.base.contract;

import java.util.List;

import yonky.yiqi.base.BasePresenter;
import yonky.yiqi.base.BaseView;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.bean.ShopFilterBean;

/**
 * Created by Administrator on 2018/5/14.
 */

public interface GoodContract {
    interface View extends BaseView{
        void showResult(List<GoodBean> list);
        void showShop(ShopBean shopBean);
    }
    interface Presenter extends BasePresenter<View>{
        void loadGoods(GoodFilterBean filter);
        void loadShop(ShopFilterBean filter);
    }
}
