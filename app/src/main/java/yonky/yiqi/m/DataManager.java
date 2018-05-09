package yonky.yiqi.m;

import android.content.Context;


import io.reactivex.Observable;
import retrofit2.Call;
import yonky.yiqi.bean.MainPageBean;
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

}
