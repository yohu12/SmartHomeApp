package com.yohu.smarthomeapp.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yohu.smarthomeapp.base.Constance;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 获取Retrofit实例
 */
public class RetrofitFactory {
    private static String TAG = RetrofitFactory.class.getSimpleName();
    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            // 添加通用的Header
//            .addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request originalRequest = chain.request();
//                    if (Application.token == null /*|| alreadyHasAuthorizationHeader(originalRequest)*/) {
//                        return chain.proceed(originalRequest);
//                    }
//                    Request authorised = originalRequest.newBuilder()
//                            .header("X-Auth-Token", Application.token)
//                            .build();
//                    Logger.d(authorised.tag().toString());
//                    return chain.proceed(authorised);
//                }
//            })
            /*
            这里可以添加一个HttpLoggingInterceptor，因为Retrofit封装好了从Http请求到解析，
            出了bug很难找出来问题，添加HttpLoggingInterceptor拦截器方便调试接口
             */
//            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                @Override
//                public void log(String message) {
//                    Logger.d(message);
//                }
//            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(Constance.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constance.TIMEOUT, TimeUnit.SECONDS)
            .build();

    private static RequestInterface requestInterface = new Retrofit.Builder()
            .baseUrl(Constance.API_URL)
            // 添加Gson转换器
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            // 添加Retrofit到RxJava的转换器
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(RequestInterface.class);

    public static RequestInterface getInstance() {
        return requestInterface;
    }

    private static Gson buildGson() {
        return new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                // 此处可以添加Gson 自定义TypeAdapter
//                .registerTypeAdapter(User.class, new UserTypeAdapter())
                .create();
    }

}
