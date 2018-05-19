package yonky.yiqi.m;

import android.content.Context;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Query;
import yonky.yiqi.bean.GoodAttributeBean;
import yonky.yiqi.bean.GoodDetailBean;
import yonky.yiqi.bean.MainPageBean;
import yonky.yiqi.bean.MarketBean;
import yonky.yiqi.bean.ShopPage;
import yonky.yiqi.bean.StyleBean;
import yonky.yiqi.http.RetrofitHelper;
import yonky.yiqi.http.RetrofitService;

/**
 * Created by Administrator on 2018/5/9.
 */

public class DataManager {
    RetrofitService mRetrofitService;
    public DataManager(Context context){
        mRetrofitService=RetrofitHelper.getInstance(context).getServer();
    }
    public Observable<MainPageBean> getMainPage(String platform, String zdid , String tag, String isDebug){
        return mRetrofitService.getMainPageData(platform,zdid,tag,isDebug);
    }

    //    http://api2.17zwd.com/rest/shop/search_shops/?psize=10&orderby=mr&keyword=&bq=&service=&pindex=1&from=android&zdid=48  逛市场

    public Observable<MarketBean>getSearchData(String psize,String orderby,String keyword,String bq,String service,String pindex,String from,String zdid){
        return mRetrofitService.getSearchData(psize,orderby,keyword,bq,service,pindex,from,zdid);
    }

    //    http://api2.17zwd.com/rest/goods/search?price1=0.0&psize=10&size=&seller_cid=&orderby=mr&color=&keyword=&pindex=1&from=android&price2=9999.0&dtype=sks&zdid=48&mid=&fid=
//搜款式

    public  Observable<StyleBean> getStyleData(String shop_id,
                                               String size,
                                               String seller_cid,
                                               String pindex,
                                               String from,
                                               String price2,
                                               String dtype,
                                               String zdid,
                                               String price1,
                                               String psize,
                                               String orderby,
                                               String color ,
                                               String spm,
                                               String keyword,
                                               String mid,
                                               String fid){
        return mRetrofitService.getStyleData(shop_id,size,seller_cid,pindex,from,price2,dtype,zdid, price1,psize,orderby,color ,spm,keyword,mid,fid);
    }

    //获取店铺详情
//    http://api2.17zwd.com/rest/shop/get_shop?shop_id=26974&from=android&user_id=-1&zdid=48&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D
    public Observable<ShopPage> getShopData(String shop_id, String from, String user_id, String zdid, String spm){
        return mRetrofitService.getShop(shop_id, from, user_id, zdid, spm);
    }

//    http://api2.17zwd.com/rest/goods/get_item?goods_id=106373882&from=android&user_id=-1&zdid=48&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D

    public Observable<GoodDetailBean> getGoodDetail(String type,String goods_id, String from, String user_id, String zdid, String spm){
        return mRetrofitService.getGoodDetail(type,goods_id, from, user_id, zdid, spm);
    }
    //    http://api2.17zwd.com/rest/goods/get_colors?from=android&shadow$_klass_=class+com.hanyun.onlineproject.entity.NetRequest
//    http://api2.17zwd.com/rest/goods/get_sizes?from=android&shadow$_klass_=class+com.hanyun.onlineproject.entity.NetRequest
    public Observable<GoodAttributeBean> getGoodAttr(String type){
        return mRetrofitService.getGoodAttribute(type);
    }
}
