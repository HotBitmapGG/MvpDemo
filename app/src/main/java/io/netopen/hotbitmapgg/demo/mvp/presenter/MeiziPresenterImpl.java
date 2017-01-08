package io.netopen.hotbitmapgg.demo.mvp.presenter;

import io.netopen.hotbitmapgg.demo.mvp.bean.MeiziInfo;
import io.netopen.hotbitmapgg.demo.mvp.view.IMeiziView;
import io.netopen.hotbitmapgg.demo.network.RetrofitHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

/**
 * Created by hcc on 2017/1/4 13:25
 * 100332338@qq.com
 * Demo
 *
 * @HotBitmapGG
 */

public class MeiziPresenterImpl implements IMeiziPresenter {

  private IMeiziView mainView;


  public MeiziPresenterImpl(IMeiziView mainView) {
    this.mainView = mainView;
  }


  @Override public void loadData() {

    RetrofitHelper.createApiService()
        .getMeiziInfos(10, 1)
        .doOnSubscribe(subscription -> mainView.showProgress())
        .delay(1000, TimeUnit.MILLISECONDS)
        .filter(meiziInfo -> !meiziInfo.isError())
        .map(MeiziInfo::getResults)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(resultsBeans -> {
          mainView.fillData(resultsBeans);
          mainView.hideProgress();
        }, throwable -> mainView.hideProgress());
  }
}
