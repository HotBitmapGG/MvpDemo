package io.netopen.hotbitmapgg.demo.network;

import io.netopen.hotbitmapgg.demo.mvp.bean.MeiziInfo;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hcc on 2017/1/4 13:09
 * 100332338@qq.com
 * Demo
 *
 * @HotBitmapGG
 */

public interface ApiService {

  @GET("data/福利/{pageSize}/{page}")
  Observable<MeiziInfo> getMeiziInfos(
      @Path("pageSize") int pageSize, @Path("page") int page);
}
