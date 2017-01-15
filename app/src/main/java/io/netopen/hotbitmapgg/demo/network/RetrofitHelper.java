package io.netopen.hotbitmapgg.demo.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.netopen.hotbitmapgg.demo.MvpDemoApp;
import io.netopen.hotbitmapgg.demo.network.api.ApiService;
import io.netopen.hotbitmapgg.demo.utils.NetworkUtil;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hcc on 2017/1/4 13:13
 * 100332338@qq.com
 * retrofit帮助类
 *
 * @HotBitmapGG
 */

public class RetrofitHelper {

  private static OkHttpClient mOkHttpClient;

  private static final String BASE_URL = "http://gank.io/api/";


  static {
    initOkHttpClient();
  }


  public static ApiService createApiService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(new OkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    return retrofit.create(ApiService.class);
  }


  /**
   * 初始化OKHttpClient
   */
  private static void initOkHttpClient() {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    if (mOkHttpClient == null) {
      synchronized (RetrofitHelper.class) {
        if (mOkHttpClient == null) {
          //设置Http缓存
          Cache cache = new Cache(
              new File(MvpDemoApp.getAppContext().getCacheDir(), "HttpCache"),
              1024 * 1024 * 100);

          mOkHttpClient = new OkHttpClient.Builder()
              .cache(cache)
              .addNetworkInterceptor(new CacheInterceptor())
              .addInterceptor(interceptor)
              .retryOnConnectionFailure(true)
              .connectTimeout(15, TimeUnit.SECONDS)
              .build();
        }
      }
    }
  }


  /**
   * 添加Okhttp缓存拦截器
   */
  private static class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

      // 有网络时 设置缓存超时时间1个小时
      int maxAge = 60 * 60;
      // 无网络时，设置超时为1天
      int maxStale = 60 * 60 * 24;
      Request request = chain.request();
      if (NetworkUtil.isNetworkConnected()) {
        //有网络时只从网络获取
        request = request.newBuilder()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build();
      } else {
        //无网络时只从缓存中读取
        request = request.newBuilder()
            .cacheControl(CacheControl.FORCE_CACHE)
            .build();
      }
      Response response = chain.proceed(request);
      if (NetworkUtil.isNetworkConnected()) {
        response = response.newBuilder()
            .removeHeader("Pragma")
            .header("Cache-Control", "public, max-age=" + maxAge)
            .build();
      } else {
        response = response.newBuilder()
            .removeHeader("Pragma")
            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
            .build();
      }
      return response;
    }
  }
}
