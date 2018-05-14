package yonky.yiqi.p;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import yonky.yiqi.base.contract.StyleContract;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.bean.StyleBean;
import yonky.yiqi.m.DataManager;

public class StylePresenter implements StyleContract.Presenter {
    public static final String TAG = StylePresenter.class.getSimpleName();
    private StyleContract.View view;
    private Context mContext;
    DataManager dataManager;

    public StylePresenter(Context mContext) {
        this.mContext = mContext;

    }

    //    http://api2.17zwd.com/rest/goods/search?price1=0.0&psize=10&size=&seller_cid=&orderby=mr&color=&keyword=&pindex=1&from=android&price2=9999.0&dtype=sks&zdid=48
//搜款式
    @Override
    public void loadDatas(GoodFilterBean filter) {
        Observable<StyleBean> observable =dataManager.getStyleData(filter.getShop_id(),filter.getSize(),filter.getSeller_cid(),filter.getPindex(),filter.getFrom(),filter.getPrice2(),
                filter.getDtype(),filter.getZdid(), filter.getPrice1(),filter.getPsize(),filter.getOrderby(),filter.getColor() ,filter.getSpm(),filter.getKeyword());
        observable.subscribeOn(Schedulers.io())
                .filter(new Predicate<StyleBean>() {
                    @Override
                    public boolean test(StyleBean styleBean) throws Exception {
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
                        view.showResult(goodBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void attachView(StyleContract.View view) {
        this.view = view;
        dataManager = new DataManager(mContext);
    }

    @Override
    public void detachView() {
        view =null;
    }
}
