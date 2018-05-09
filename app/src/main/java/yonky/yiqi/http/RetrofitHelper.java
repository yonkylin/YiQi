package yonky.yiqi.http;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/9.
 */

public class RetrofitHelper {
    private Context mContext;

    OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create();
    Retrofit mRetrofit;

    private static RetrofitHelper instance = null;
    public static RetrofitHelper getInstance(Context context){
        if(instance ==null){
            instance = new RetrofitHelper(context);
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
