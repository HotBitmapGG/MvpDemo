package io.netopen.hotbitmapgg.demo.network.api;

import io.netopen.hotbitmapgg.demo.mvp.bean.MeiziInfo;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hcc on 2017/1/4 13:09
 * 100332338@qq.com
 * 妹子Api请求接口
 *
 * @HotBitmapGG
 */

public interface ApiService {

  @GET("data/福利/{pageSize}/{page}")
  Flowable<MeiziInfo> getMeiziInfos(
      @Path("pageSize") int pageSize, @Path("page") int page);
}
