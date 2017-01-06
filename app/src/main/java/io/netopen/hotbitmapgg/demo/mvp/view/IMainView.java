package io.netopen.hotbitmapgg.demo.mvp.view;

import io.netopen.hotbitmapgg.demo.mvp.bean.MeiziInfo;
import java.util.List;

/**
 * Created by hcc on 2017/1/4 13:24
 * 100332338@qq.com
 * Demo
 *
 * @HotBitmapGG
 */

public interface IMainView {

  void showProgress();

  void hideProgress();

  void fillData(List<MeiziInfo.ResultsBean> resultsBeanList);
}
