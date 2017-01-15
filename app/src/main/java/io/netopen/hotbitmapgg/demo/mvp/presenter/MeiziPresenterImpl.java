package io.netopen.hotbitmapgg.demo.mvp.presenter;

import io.netopen.hotbitmapgg.demo.mvp.bean.MeiziInfo;
import io.netopen.hotbitmapgg.demo.mvp.view.IMeiziView;
import io.netopen.hotbitmapgg.demo.network.RetrofitHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

import android.util.Log;

/**
 * Created by hcc on 2017/1/4 13:25
 * 100332338@qq.com
 * MVP架构P层实现类
 * 这里实现了定义好的P层接口
 * 方法具体实现在这里，加载数据，和上拉加载更多数据
 * 这里引入view层，在加载数据完成后通知ui更新
 *
 * @HotBitmapGG
 */

public class MeiziPresenterImpl implements IMeiziPresenter {

  //请求数据的页数
  private int page = 1;
  //请求数据的数量
  private int pageNum = 10;
  //view层接口
  private IMeiziView meiziView;


  /**
   * 通过构造函数传入view层
   */
  public MeiziPresenterImpl(IMeiziView mainView) {
    this.meiziView = mainView;
  }


  /**
   * 加载数据的实现方法
   */
  @Override public void loadData() {

    RetrofitHelper.createApiService()
        .getMeiziInfos(pageNum, page)
        .doOnSubscribe(subscription -> meiziView.showProgress())
        .delay(1000, TimeUnit.MILLISECONDS)
        .filter(meiziInfo -> !meiziInfo.isError())
        .map(MeiziInfo::getResults)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(resultsBeans -> {
          meiziView.fillData(resultsBeans);
          meiziView.hideProgress();
        }, throwable -> meiziView.hideProgress());
  }


  /**
   * 加载更多数据的实现方法
   */
  @Override public void loadMore() {
    page++;
    RetrofitHelper.createApiService()
        .getMeiziInfos(pageNum, page)
        .filter(meiziInfo -> !meiziInfo.isError())
        .map(MeiziInfo::getResults)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(resultsBeans -> meiziView.loadMoreData(page, pageNum, resultsBeans),
            throwable -> Log.e("tag", throwable.getMessage()));
  }
}
