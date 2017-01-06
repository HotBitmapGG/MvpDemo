package io.netopen.hotbitmapgg.demo.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hcc on 2017/1/4 13:13
 * 100332338@qq.com
 * Demo
 *
 * @HotBitmapGG
 */

public class RetrofitHelper {

  public static final String BASE_URL = "http://gank.io/api/";


  public static ApiService createApiService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(new OkHttpClient())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    return retrofit.create(ApiService.class);
  }
}
