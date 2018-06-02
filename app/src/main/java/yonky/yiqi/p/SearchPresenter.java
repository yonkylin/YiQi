package yonky.yiqi.p;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import yonky.yiqi.base.contract.SearchContract;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.bean.StyleBean;
import yonky.yiqi.m.DataManager;
import yonky.yiqi.util.MyUtil;

/**
 * Created by Administrator on 2018/6/1.
 */

public class SearchPresenter implements SearchContract.Presenter {
    Context mContext;
    SearchContract.View mView;
    DataManager mDataManager;

    public SearchPresenter(Context context) {
        mContext = context;
        mDataManager =new DataManager();
    }

    @Override
    public void imgSearchData(GoodFilterBean filterBean) {
        Observable<StyleBean> observable=mDataManager.getImgSearch(filterBean.getUrl(),filterBean.getZdid());
       dodo(observable);

    }


    @Override
    public void titleSearchData(GoodFilterBean filterBean) {
        Observable<StyleBean> observable=mDataManager.getTitleSearch(filterBean.getPsize(),filterBean.getOrderby(),filterBean.getKeyword(),filterBean.getPindex(),filterBean.getZdid());
        dodo(observable);

    }
    private void dodo(Observable<StyleBean> observable){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<StyleBean>() {
                    @Override
                    public boolean test(StyleBean styleBean) throws Exception {
                        if(styleBean.getStatus_code()==201){
//                            返回空
                            mView.showNoData();
                        }
                        return styleBean.getStatus_code()==200;
                    }
                })
                .map(new Function<StyleBean, List<GoodBean>>() {
                    @Override
                    public List<GoodBean> apply(StyleBean styleBean) throws Exception {

                        return styleBean.getGoods_items_list_get_response().getItems();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<GoodBean>>() {
                    @Override
                    public void accept(List<GoodBean> goodBeans) throws Exception {
                        mView.showResult(goodBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
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
