package io.netopen.hotbitmapgg.demo.mvp.ui.activity;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.netopen.hotbitmapgg.demo.R;
import io.netopen.hotbitmapgg.demo.mvp.bean.MeiziInfo;
import io.netopen.hotbitmapgg.demo.mvp.presenter.MeiziPresenterImpl;
import io.netopen.hotbitmapgg.demo.mvp.ui.adapter.EndlessRecyclerOnScrollListener;
import io.netopen.hotbitmapgg.demo.mvp.ui.adapter.MeiziAdapter;
import io.netopen.hotbitmapgg.demo.mvp.view.IMeiziView;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by hcc on 2017/1/8 16:45
 * 100332338@qq.com
 * 妹子图列表界面
 *
 * @HotBitmapGG
 */
public class MeiziActivity extends AppCompatActivity implements IMeiziView {

  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  @Bind(R.id.progress_bar) ProgressBar mProgressBar;
  private MeiziAdapter mAdapter;
  private MeiziPresenterImpl meiziPresenter;
  private List<MeiziInfo.ResultsBean> meizis = new ArrayList<>();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //绑定黄油刀注解控件
    ButterKnife.bind(this);

    //初始化toolbar
    initToolBar();
    //初始化recyclerView
    initRecyclerView();
    //初始化数据
    initData();
  }


  private void initToolBar() {
    //设置toolBar的标题
    mToolbar.setTitle(getResources().getString(R.string.app_name));
  }


  private void initData() {
    //实例化P层实现类
    meiziPresenter = new MeiziPresenterImpl(this);
    //调用加载数据的方法
    meiziPresenter.loadData();
  }


  private void initRecyclerView() {

    //设置item的宽高保持不变
    mRecyclerView.setHasFixedSize(true);
    //初始化LayoutManager
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    //设置layoutManager
    mRecyclerView.setLayoutManager(linearLayoutManager);
    //初始化Adapter
    mAdapter = new MeiziAdapter();
    //设置Adapter
    mRecyclerView.setAdapter(mAdapter);
    //设置item点击事件
    mAdapter.setOnItemClickListener(
        (position, holder) -> MeiziDetailsActivity.launch(MeiziActivity.this,
            meizis.get(position).getDesc(),
            meizis.get(position).getUrl()));
    //设置监听滑动事件，上拉加载更多数据
    mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
      @Override public void onLoadMore(int currentPage) {
        meiziPresenter.loadMore();
      }
    });
  }


  /**
   * 显示进度条
   */
  @Override public void showProgress() {
    mProgressBar.setVisibility(View.VISIBLE);
  }


  /**
   * 隐藏进度条
   */
  @Override public void hideProgress() {
    mProgressBar.setVisibility(View.GONE);
  }


  /**
   * P层加载完数据进行UI填充
   */
  @Override public void fillData(List<MeiziInfo.ResultsBean> resultsBeanList) {
    meizis.addAll(resultsBeanList);
    mAdapter.setDataSources(meizis);
    mAdapter.notifyDataSetChanged();
  }


  /**
   * 上拉加载更多数据完成
   */
  @Override
  public void loadMoreData(int page, int pageNum, List<MeiziInfo.ResultsBean> resultsBeanList) {
    meizis.addAll(resultsBeanList);
    //判断当前是否还有更多数据，进行recyclerView的局部item更新
    if (page * pageNum - pageNum - 1 > 0) {
      mAdapter.notifyItemRangeChanged(page * pageNum - pageNum - 1, pageNum);
    } else {
      mAdapter.notifyDataSetChanged();
    }
  }


  @Override protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }
}
