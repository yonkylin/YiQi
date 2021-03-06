package yonky.yiqi.base.contract;

import java.util.List;

import yonky.yiqi.base.BasePresenter;
import yonky.yiqi.base.BaseView;
import yonky.yiqi.bean.GoodAttributeBean;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.bean.KVBean;
import yonky.yiqi.bean.RegionBean;
import yonky.yiqi.bean.ShopFilterBean;

public interface StyleContract {
    interface View extends BaseView{
        void showResult(List<GoodBean> beanList,boolean isLoadingMore);
        void showGoodAttr(GoodAttributeBean.GoodsItemGetResponseBean bean);
        void showRegion(List<RegionBean.ItemsBean> regionList,int type);
    }
    interface Presenter extends BasePresenter<View>{
        void loadDatas(GoodFilterBean filter,boolean isLoadingMore);
        void getGoodColor(String type );
    }

}
