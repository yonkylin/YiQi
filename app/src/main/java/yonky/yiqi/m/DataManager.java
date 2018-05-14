package yonky.yiqi.m;

import android.content.Context;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Query;
import yonky.yiqi.bean.MainPageBean;
import yonky.yiqi.bean.MarketBean;
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

    //    http://api2.17zwd.com/rest/goods/search?price1=0.0&psize=10&size=&seller_cid=&orderby=mr&color=&keyword=&pindex=1&from=android&price2=9999.0&dtype=sks&zdid=48
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
                                               String keyword){
        return mRetrofitService.getStyleData(shop_id,size,seller_cid,pindex,from,price2,dtype,zdid, price1,psize,orderby,color ,spm,keyword);
    }
}
