package io.netopen.hotbitmapgg.demo.utils;

import io.netopen.hotbitmapgg.demo.MvpDemoApp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by hcc on 2016/12/28 13:35
 * 100332338@qq.com
 * 网络相关工具类
 *
 * @HotBitmapGG
 */
public class NetWorkUtil {

  private NetWorkUtil() {

  }


  public static boolean isNetworkConnected() {

    if (MvpDemoApp.getAppContext() != null) {
      ConnectivityManager mConnectivityManager
          = (ConnectivityManager) MvpDemoApp.getAppContext()
          .getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
      if (mNetworkInfo != null) {
        return mNetworkInfo.isAvailable();
      }
    }
    return false;
  }
}
