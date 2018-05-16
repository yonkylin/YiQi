package yonky.yiqi.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import yonky.yiqi.bean.GoodDetailBean;
import yonky.yiqi.bean.MainPageBean;
import yonky.yiqi.bean.MarketBean;
import yonky.yiqi.bean.ShopPage;
import yonky.yiqi.bean.StyleBean;

/**
 * Created by Administrator on 2018/5/9.
 */

public interface RetrofitService {
//
//  http://api2.17zwd.com/rest/popularize/get_home_data?from=android&zdid=48&tag=A&debug=false  首页
     String HOST = "http://api2.17zwd.com/";

    @GET("rest/popularize/get_home_data")
    Observable<MainPageBean> getMainPageData(@Query("from")String platform,
                                       @Query("zdid")String zdid,
                                       @Query("tag")String tag,
                                       @Query("debug")String isDebug);
    //    http://api2.17zwd.com/rest/shop/search_shops/?psize=10&orderby=mr&keyword=&bq=&service=&pindex=1&from=android&zdid=48
//逛市场

    @GET("rest/shop/search_shops/")
    Observable<MarketBean> getSearchData(@Query("psize")String psize,
                                           @Query("orderby")String orderby,
                                           @Query("keyword")String keyword,
                                           @Query("bq")String bq,
                                           @Query("service")String service,
                                           @Query("pindex")String pindex,
                                           @Query("from")String from,
                                           @Query("zdid")String zdid);

// 搜款式
//    http://api2.17zwd.com/rest/goods/search?price1=0.0&psize=10&size=&seller_cid=&orderby=mr&color=&keyword=&pindex=1&from=android&price2=9999.0&dtype=sks&zdid=48



    @GET("/rest/goods/search")
    Observable<StyleBean> getStyleData(@Query("shop_id")String shop_id,
                                       @Query("size")String size,
                                       @Query("seller_cid") String seller_cid,
                                       @Query("pindex") String pindex,
                                       @Query("from") String from,
                                       @Query("price2") String price2,
                                       @Query("dtype") String dtype,
                                       @Query("zdid") String zdid,
                                       @Query("price1") String price1,
                                       @Query("psize") String psize,
                                       @Query("orderby") String orderby,
                                       @Query("color")  String color ,
                                       @Query("spm")String spm,
                                       @Query("keyword") String keyword);
           /* @Query("price1") String price1,
                                       @Query("psize") String psize,
                                       @Query("size")String size,
                                        @Query("seller_cid") String seller_cid,
                                        @Query("orderby") String orderby,
                                        @Query("color")  String color ,
                                        @Query("keyword") String keyword,
                                        @Query("pindex") String pindex,
                                        @Query("from") String from,
                                        @Query("price2") String price2,
                                        @Query("dtype") String dtype,
                                        @Query("zdid") String zdid);
*/

// http://api2.17zwd.com/rest/goods/search?shop_id=26974&size=&seller_cid=&pindex=1&from=android&price2=9999.0&dtype=sks&zdid=48&price1=0.0&psize=10&orderby=mr&color=&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D&keyword=

    @GET("/rest/goods/search")
    Observable<StyleBean> getGoods(@Query("shop_id")String shop_id,
                                   @Query("size")String size,
                                   @Query("seller_cid") String seller_cid,
                                   @Query("pindex") String pindex,
                                   @Query("from") String from,
                                   @Query("price2") String price2,
                                   @Query("dtype") String dtype,
                                   @Query("zdid") String zdid,
                                   @Query("price1") String price1,
                                       @Query("psize") String psize,
                                       @Query("orderby") String orderby,
                                       @Query("color")  String color ,
                                       @Query("spm")String spm,
                                       @Query("keyword") String keyword);
//获取店铺详情
//    http://api2.17zwd.com/rest/shop/get_shop?shop_id=26974&from=android&user_id=-1&zdid=48&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D
    @GET("/rest/shop/get_shop")
    Observable<ShopPage> getShop(@Query("shop_id")String shop_id,
                                 @Query("from")String from,
                                 @Query("user_id")String user_id,
                                 @Query("zdid")String zdid,
                                 @Query("spm")String spm
                                   );

//    http://api2.17zwd.com/rest/goods/get_item?goods_id=106373882&from=android&user_id=-1&zdid=48&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D

    @GET("/rest/goods/{item}")
    Observable<GoodDetailBean> getGoodDetail(@Path("item")String item,
                                             @Query("goods_id")String goods_id,
                                             @Query("from")String from,
                                             @Query("user_id")String user_id,
                                             @Query("zdid")String zdid,
                                             @Query("spm")String spm
    );







}







