package yonky.yiqi.p;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Callback;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import yonky.yiqi.base.contract.MainContract;
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
    public void loadDatas() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<MainPageBean> call=mDataManager.getMainPage("android","48","A","false");

               call.enqueue(new retrofit2.Callback<MainPageBean>() {
                   @Override
                   public void onResponse(Call<MainPageBean> call, Response<MainPageBean> response) {
                       Log.d(TAG,"DATA:"+response.code());
                   }

                   @Override
                   public void onFailure(Call<MainPageBean> call, Throwable t) {
                        Log.d(TAG,t.toString());
                   }
               });

            }
        }).start();


//                .subscribeOn(Schedulers.io())
//                .map(new Function<MainPageBean, List<MainPageBean.PopularizeItemsListGetResponseBean.AreaABean>>(){
//                    @Override
//                    public List<MainPageBean.PopularizeItemsListGetResponseBean.AreaABean> apply(MainPageBean mainPageBean) throws Exception {
//                        if(mainPageBean.getStatus_code()==200){
//                            return  mainPageBean.getPopularize_items_list_get_response().getAreaA();
//
//                        }
//                        Log.e(TAG,"response code is not 200");
//                        return null;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new Observer<List<MainPageBean.PopularizeItemsListGetResponseBean.AreaABean>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<MainPageBean.PopularizeItemsListGetResponseBean.AreaABean> areaABeans) {
//                        view.showResult(areaABeans,0);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });




    }

    @Override
    public void checkPermission() {

    }
}
