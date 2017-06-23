package com.zonsim.yixue.net;

import com.zonsim.yixue.BuildConfig;
import com.zonsim.yixue.bean.MyExamsResp;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ^-^
 * Created by tang-jw on 2017/6/23.
 */

public class HttpMethods {
    
    public static final String HOST_URL = BuildConfig.API_HOST;
    
    private static volatile HttpMethods sInstance;
    private final Api mApi;
    
    
    private HttpMethods() {
        
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        
        if (BuildConfig.DEBUG) {
            builder = builder.addInterceptor(logInterceptor);
        }
        
        mApi = new Retrofit.Builder()
                .baseUrl(HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()
                .create(Api.class);
        
    }
    
    public static HttpMethods getInstance() {
        if (sInstance == null) {
            synchronized (HttpMethods.class) {
                if (sInstance == null) {
                    sInstance = new HttpMethods();
                }
            }
        }
        return sInstance;
    }
    
    public Observable<MyExamsResp> getMyExams(long uid) {
        return mApi.getMyExams(uid);
    }
    
}
