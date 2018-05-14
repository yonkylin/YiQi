package yonky.yiqi.p;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import yonky.yiqi.base.contract.GoodContract;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.bean.StyleBean;
import yonky.yiqi.m.DataManager;

/**
 * Created by Administrator on 2018/5/14.
 */

public class GoodPresenter implements GoodContract.Presenter {
    GoodContract.View view;
    Context mContext;
    DataManager mDataManager;

    public GoodPresenter(Context context) {
        mContext = context;
        mDataManager = new DataManager(mContext);
    }

    @Override
    public void attachView(GoodContract.View view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void load(GoodFilterBean filter) {
        Observable<StyleBean> observable =mDataManager.getStyleData(filter.getPrice1(),filter.getPsize(),filter.getSize(),filter.getSeller_cid(),filter.getOrderby(),
                filter.getColor(),filter.getKeyword(),filter.getKeyword(),filter.getFrom(),filter.getPrice2(),filter.getDtype(),filter.getZdid());
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


}
