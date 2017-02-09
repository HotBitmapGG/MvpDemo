package io.netopen.hotbitmapgg.demo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by hcc on 2017/2/8 21:07
 * 100332338@qq.com
 * MvpDemo
 *
 * @HotBitmapGG
 */

public class DownloadService extends IntentService {

  public DownloadService(String name) {
    super(name);
  }


  @Override protected void onHandleIntent(Intent intent) {

    Log.e("tag", Thread.currentThread().getName());
  }


  @Override public void onStart(Intent intent, int startId) {
    super.onStart(intent, startId);
  }


  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    return super.onStartCommand(intent, flags, startId);
  }


  @Override public void onDestroy() {
    super.onDestroy();
  }
}
