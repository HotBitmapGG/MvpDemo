package io.netopen.hotbitmapgg.demo.mvp.presenter;

/**
 * Created by hcc on 2017/1/4 13:25
 * 100332338@qq.com
 * MVP架构的P层结构
 * 定义业务逻辑加载数据的方法
 * 这里是加载数据和加载更多
 *
 * @HotBitmapGG
 */

public interface IMeiziPresenter {

  /**
   * 加载数据
   */
  void loadData();

  /**
   * 加载更多
   */
  void loadMore();
}
