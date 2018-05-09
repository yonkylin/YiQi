package yonky.yiqi.base.contract;

import java.util.List;

import yonky.yiqi.base.BasePresenter;
import yonky.yiqi.base.BaseView;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.bean.MainPageBean;

/**
 * Created by Administrator on 2018/5/9.
 */

public interface MainContract {
    interface View extends BaseView{
        void showResult(List<AreaBean> areaABeanList, int type);
    }
    interface Presenter extends BasePresenter<View>{
        void checkPermission();
        void loadDatas();

    }
}
