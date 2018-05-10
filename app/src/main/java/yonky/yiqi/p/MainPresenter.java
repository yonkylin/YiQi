package yonky.yiqi.p;

import android.content.Context;
import android.util.Log;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import yonky.yiqi.base.contract.MainContract;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.bean.MainPageBean;
import yonky.yiqi.m.DataManager;


/**
 * Created by Administrator on 2018/5/9.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final  String TAG = MainPresenter.class.getSimpleName();
    MainContract.View view;
    Context mContext;
    DataManager mDataManager;
//    String type;

    Observable<MainPageBean> observable;

    public MainPresenter(Context context) {
        mContext = context;
        Log.d(TAG,"new presenter");

    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
        mDataManager = new DataManager(mContext);
    }

    @Override
    public void detachView() {

    }

    @Override
    public void loadDatas(String tag) {
       String type= tag;
      Observable<MainPageBean> topObservable=  mDataManager.getMainPage("android","48",tag,"false");
            topObservable.subscribeOn(Schedulers.io())
                    .map(new Function<MainPageBean,MainPageBean.PopularizeItemsListGetResponseBean>() {
                        @Override
                        public MainPageBean.PopularizeItemsListGetResponseBean apply(MainPageBean mainPageBean) throws Exception {
                            return mainPageBean.getPopularize_items_list_get_response();
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<MainPageBean.PopularizeItemsListGetResponseBean>() {
                        @Override
                        public void accept(MainPageBean.PopularizeItemsListGetResponseBean popularItem) throws Exception {
                            if(popularItem.getAreaA()!=null){
                                view.showResult(popularItem.getAreaA(), "A");
                            }else if(popularItem.getAreaB1()!=null){
                                view.showResult(popularItem.getAreaB1(), "B1");
                            }else if(popularItem.getAreaB2()!=null){
                                view.showResult(popularItem.getAreaB1(), "B2");
                            }

                        }

                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                        }
                    });




    }

    @Override
    public void checkPermission() {

    }
}
