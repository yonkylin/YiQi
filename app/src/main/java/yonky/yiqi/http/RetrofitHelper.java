package yonky.yiqi.http;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yonky.yiqi.base.App;

/**
 * Created by Administrator on 2018/5/9.
 */

public class RetrofitHelper {
    private Context mContext;

    OkHttpClient client =new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20,TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

    Retrofit mRetrofit;

    private static RetrofitHelper instance = null;
    public static RetrofitHelper getInstance(Context context){
        if(instance ==null){
            instance = new RetrofitHelper(App.getInstance());
        }
        return instance;
    }
    private RetrofitHelper(Context context){
        mContext = context;
        init();
    }
    private void init(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.HOST)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }
}
