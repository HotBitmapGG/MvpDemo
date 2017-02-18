package io.netopen.hotbitmapgg.demo.network.api;

import io.netopen.hotbitmapgg.demo.mvp.bean.UserInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hcc on 2017/2/16 11:43
 * 100332338@qq.com
 * MvpDemo
 *
 * @HotBitmapGG
 */

public interface GitHubService {

  @GET("users/{name}")
  Call<UserInfo> getUserInfo(@Path("name") String name);
}
