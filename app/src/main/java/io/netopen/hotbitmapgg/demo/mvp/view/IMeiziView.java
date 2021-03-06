package io.netopen.hotbitmapgg.demo.mvp.view;

import io.netopen.hotbitmapgg.demo.mvp.bean.MeiziInfo;
import java.util.List;

/**
 * Created by hcc on 2017/1/4 13:24
 * 100332338@qq.com
 * MVP架构View层接口
 * 主要是定义了跟UI交互的方法
 *
 * @HotBitmapGG
 */

public interface IMeiziView {

  void showProgress();

  void hideProgress();

  void fillData(List<MeiziInfo.ResultsBean> resultsBeanList);

  void loadMoreData(int page, int pageNum, List<MeiziInfo.ResultsBean> resultsBeanList);
}
