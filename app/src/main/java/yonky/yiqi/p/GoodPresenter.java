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
import yonky.yiqi.bean.MarketBean;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.bean.ShopFilterBean;
import yonky.yiqi.bean.ShopPage;
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
    public void loadGoods(GoodFilterBean filter,final boolean loadingMore) {
        Observable<StyleBean> observable =mDataManager.getStyleData(filter.getShop_id(),filter.getSize(),filter.getSeller_cid(),filter.getPindex(),filter.getFrom(),filter.getPrice2(),
                filter.getDtype(),filter.getZdid(), filter.getPrice1(),filter.getPsize(),filter.getOrderby(),filter.getColor() ,filter.getSpm(),filter.getKeyword(),filter.getMid(),filter.getFid());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                                .filter(new Predicate<StyleBean>() {
                    @Override
                    public boolean test(StyleBean styleBean) throws Exception {
                        if(styleBean.getStatus_code()==201){
                            view.showEmpty();
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
                        view.showResult(goodBeans,loadingMore);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    //获取店铺详情
//    http://api2.17zwd.com/rest/shop/get_shop?shop_id=26974&from=android&user_id=-1&zdid=48&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D

    @Override
    public void loadShop(ShopFilterBean filter) {
        Observable<ShopPage> observable =mDataManager.getShopData(filter.getShop_id(),filter.getFrom(),filter.getUser_id(),filter.getZdid(),filter.getSpm());
        observable.subscribeOn(Schedulers.io())
                .filter(new Predicate<ShopPage>() {
                    @Override
                    public boolean test(ShopPage shopPageBean) throws Exception {
                        return shopPageBean.getStatus_code()==200;
                    }
                })
                .map(new Function<ShopPage, ShopBean>() {
                    @Override
                    public ShopBean apply(ShopPage shopPageBean) throws Exception {
                        return shopPageBean.getShop_item_get_response().getItem();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShopBean>() {
                    @Override
                    public void accept(ShopBean shopBean) throws Exception {
                        view.showShop(shopBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
}
