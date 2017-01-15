package io.netopen.hotbitmapgg.demo;

import android.app.Application;

/**
 * Created by hcc on 2017/1/8 15:57
 * 100332338@qq.com
 * MvpDemoApp
 *
 * @HotBitmapGG
 */

public class MvpDemoApp extends Application {

  private static MvpDemoApp mAppContext;


  @Override public void onCreate() {
    super.onCreate();
    mAppContext = this;
  }


  public static MvpDemoApp getAppContext() {
    return mAppContext;
  }
}
