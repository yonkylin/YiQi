package yonky.yiqi.http;

import android.content.Context;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yonky.yiqi.base.App;
import yonky.yiqi.util.SSLSocketClient;

/**
 * Created by Administrator on 2018/5/9.
 */

public class RetrofitHelper {
    private Context mContext;

    OkHttpClient client =new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20,TimeUnit.SECONDS)
//            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())全面更新为https
//            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
//            .addInterceptor(new RedirectInterceptor())
            .build();

    Retrofit mRetrofit;
    Retrofit mRetrofit2;

    private static RetrofitHelper instance = null;
    public static RetrofitHelper getInstance(){
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
        mRetrofit2 = new Retrofit.Builder()
                .baseUrl(RetrofitService.SEARCH)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }

    public RetrofitService getServer2(){
        return mRetrofit2.create(RetrofitService.class);
    }

    class RedirectInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl beforeUrl = request.url();
            Response response = chain.proceed(request);
            HttpUrl afterUrl = response.request().url();
            //1.根据url判断是否是重定向
            if(!beforeUrl.equals(afterUrl)) {
                //处理两种情况 1、跨协议 2、原先不是GET请求。
                if (!beforeUrl.scheme().equals(afterUrl.scheme())||!request.method().equals("GET")) {
                    //重新请求
                    Request newRequest = request.newBuilder().url(response.request().url()).build();
                    response = chain.proceed(newRequest);
                }
            }
            return response;
        }
    }
}
