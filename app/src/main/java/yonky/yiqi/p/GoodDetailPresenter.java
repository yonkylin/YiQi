package yonky.yiqi.p;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import yonky.yiqi.base.contract.GoodDetailContract;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodDetailBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.m.DataManager;

public class GoodDetailPresenter implements GoodDetailContract.Presenter {
    private static final String TAG=GoodDetailPresenter.class.getSimpleName();
    GoodDetailContract.View view;
    DataManager dataManager;
    Context mContext;

    public GoodDetailPresenter(Context mContext) {
        this.mContext = mContext;
        dataManager = new DataManager(mContext);
    }

    @Override
    public void loadGoodDetail(GoodFilterBean filter) {
        Observable<GoodDetailBean> observable = dataManager.getGoodDetail(filter.getGoods_id(),filter.getFrom(),filter.getUser_id(),filter.getZdid(),filter.getSpm());
        observable.subscribeOn(Schedulers.io())
                .filter(new Predicate<GoodDetailBean>() {
                    @Override
                    public boolean test(GoodDetailBean goodDetailBean) throws Exception {
                        Log.e(TAG,"the status_cod is "+goodDetailBean.getStatus_code());
                        return goodDetailBean.getStatus_code()==200;
                    }
                })
                .map(new Function<GoodDetailBean, GoodBean>() {
                    @Override
                    public GoodBean apply(GoodDetailBean goodDetailBean) throws Exception {
                        return goodDetailBean.getGoods_item_get_response().getItem();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GoodBean>() {
                    @Override
                    public void accept(GoodBean goodBean) throws Exception {
                        view.showResult(goodBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void attachView(GoodDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if(view!=null){
            view =null;
        }
    }
}
