package yonky.yiqi.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yonky.yiqi.bean.MainPageBean;

/**
 * Created by Administrator on 2018/5/9.
 */

public interface RetrofitService {
//    http://api2.17zwd.com/rest/popularize/get_home_data?from=android&zdid=48&tag=A&debug=false
    public static final String HOST = "http://api2.17zwd.com/rest/";

    @GET("rest/popularize/get_home_data")
    Call<MainPageBean> getMainPageData(@Query("from")String platform,
                                       @Query("zdid")String zdid,
                                       @Query("tag")String tag,
                                       @Query("debug")String isDebug);
//    Observable<MainPageBean> getMainPageData(@Query("from")String platform,
//                                            @Query("zdid")String zdid,
//                                             @Query("tag")String tag,
//                                             @Query("debug")String isDebug);
}
