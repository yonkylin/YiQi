package yonky.yiqi.base.contract;

import java.util.List;

import yonky.yiqi.base.BasePresenter;
import yonky.yiqi.base.BaseView;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;

public interface GoodDetailContract {
    interface View extends BaseView{
        void showResult(GoodBean goodBean);
        void showImgs(List<String> imgList);
    }
    interface Presenter extends BasePresenter<View>{
        void loadGoodDetail(GoodFilterBean filter);
        void loadImgs(GoodFilterBean filter);
    }
}
