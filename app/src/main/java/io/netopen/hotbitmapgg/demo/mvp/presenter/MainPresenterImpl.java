package io.netopen.hotbitmapgg.demo.mvp.presenter;

import io.netopen.hotbitmapgg.demo.mvp.bean.MeiziInfo;
import io.netopen.hotbitmapgg.demo.mvp.view.IMainView;
import io.netopen.hotbitmapgg.demo.network.RetrofitHelper;
import java.util.concurrent.TimeUnit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hcc on 2017/1/4 13:25
 * 100332338@qq.com
 * Demo
 *
 * @HotBitmapGG
 */

public class MainPresenterImpl implements IMainPresenter {

  private IMainView mainView;


  public MainPresenterImpl(IMainView mainView) {
    this.mainView = mainView;
  }


  @Override public void loadData() {

    RetrofitHelper.createApiService()
        .getMeiziInfos(10, 1)
        .doOnSubscribe(() -> mainView.showProgress())
        .delay(1000, TimeUnit.MILLISECONDS)
        .filter(meiziInfo -> !meiziInfo.isError())
        .map(MeiziInfo::getResults)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(resultsBeen -> {
          mainView.fillData(resultsBeen);
          mainView.hideProgress();
        }, throwable -> {
          mainView.hideProgress();
        });
  }
}
