package yonky.yiqi.p;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import yonky.yiqi.base.contract.MarketContract;
import yonky.yiqi.bean.ItemsBean;
import yonky.yiqi.bean.MarketBean;
import yonky.yiqi.m.DataManager;
import yonky.yiqi.v.adapter.MarketAdapter;

/**
 * Created by Administrator on 2018/5/12.
 */

public class MarketPresenter implements MarketContract.Presenter {
    private static final String TAG = MarketPresenter.class.getSimpleName();
    Context mContext;
    MarketContract.View mView;
    CompositeDisposable mCompositeDisposable;
    DataManager mDataManager;

    public MarketPresenter(Context context){
        this.mContext = context;
        mDataManager = new DataManager(mContext);
    }

    @Override
    public void attachView(MarketContract.View view) {
        this.mView = view;
        mDataManager = new DataManager(mContext);
    }
//    http://api2.17zwd.com/rest/shop/search_shops/?psize=10&orderby=mr&keyword=&bq=&service=&pindex=1&from=android&zdid=48  逛市场

    @Override
    public void loadData() {

        Observable<MarketBean> observable = mDataManager.getSearchData("10","mr","","","","1","android","48");
        observable.subscribeOn(Schedulers.io())
                .filter(new Predicate<MarketBean>() {
                    @Override
                    public boolean test(MarketBean marketBean) throws Exception {
                        return marketBean.getStatus_code()==200;
                    }
                })
                .map(new Function<MarketBean, List<ItemsBean>>() {
                    @Override
                    public List<ItemsBean> apply(MarketBean marketBean) throws Exception {

                       return marketBean.getShop_items_list_get_response().getItems();

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ItemsBean>>() {
                    @Override
                    public void accept(List<ItemsBean> beanList) throws Exception {
                        mView.showResult(beanList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG,"loadData error");
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void loadMore() {

    }


    public void add(CompositeDisposable compositeDisposable){
        if(mCompositeDisposable==null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(compositeDisposable);
    }
    public void unSubscribe(){
        if(mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void detachView() {
        mView = null;
        unSubscribe();
    }
}
