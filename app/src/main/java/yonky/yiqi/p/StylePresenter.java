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
import yonky.yiqi.bean.GoodAttributeBean;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.bean.RegionBean;
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
    public void loadDatas(GoodFilterBean filter, final boolean isLoadMore) {

        Observable<StyleBean> observable =dataManager.getStyleData(filter.getShop_id(),filter.getSize(),filter.getSeller_cid(),filter.getPindex(),filter.getFrom(),filter.getPrice2(),
                filter.getDtype(),filter.getZdid(), filter.getPrice1(),filter.getPsize(),filter.getOrderby(),filter.getColor() ,filter.getSpm(),filter.getKeyword(),filter.getMid(),filter.getFid());
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
                        view.showResult(goodBeans,isLoadMore);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
@Override
    public void getGoodColor(String type ){
        Observable<GoodAttributeBean>  observable=dataManager.getGoodAttr(type);
        observable.subscribeOn(Schedulers.io())
                .filter(new Predicate<GoodAttributeBean>() {
                    @Override
                    public boolean test(GoodAttributeBean goodAttributeBean) throws Exception {
                        return goodAttributeBean.getStatus_code()==200;
                    }
                })
                .map(new Function<GoodAttributeBean,GoodAttributeBean.GoodsItemGetResponseBean>() {
                    @Override
                    public GoodAttributeBean.GoodsItemGetResponseBean apply(GoodAttributeBean goodAttributeBean) throws Exception {
                        return goodAttributeBean.getGoods_item_get_response();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GoodAttributeBean.GoodsItemGetResponseBean>() {
                    @Override
                    public void accept(GoodAttributeBean.GoodsItemGetResponseBean goodsItemGetResponseBean) throws Exception {
                        view.showGoodAttr(goodsItemGetResponseBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
    //    http://api2.17zwd.com/rest/region/get_list?from=android&shadow$_klass_=class+com.hanyun.onlineproject.entity.NetRequest&zdid=52&fid=899
    public void getRegionData(String zdid,String fid,final int type){
        Observable<RegionBean> observable= dataManager.getRegionData("android","class+com.hanyun.onlineproject.entity.NetRequest",zdid,fid);
        observable.subscribeOn(Schedulers.io())
                .filter(new Predicate<RegionBean>() {
                    @Override
                    public boolean test(RegionBean regionBean) throws Exception {
                        return regionBean.getStatus_code()==200;
                    }
                })
                .map(new Function<RegionBean,List<RegionBean.ItemsBean>>() {
                    @Override
                    public List<RegionBean.ItemsBean> apply(RegionBean regionBean) throws Exception {
                        return regionBean.getRegion_items_list_get_response().getItems();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RegionBean.ItemsBean>>() {
                    @Override
                    public void accept(List<RegionBean.ItemsBean> itemsBeans) throws Exception {
                        view.showRegion(itemsBeans,type);
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
