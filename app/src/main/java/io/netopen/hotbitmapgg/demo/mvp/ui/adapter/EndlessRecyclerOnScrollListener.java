package io.netopen.hotbitmapgg.demo.mvp.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hcc on 2017/1/4 13:25
 * 100332338@qq.com
 * RecyclerView滑动监听事件
 * 主要实现上拉加载更多
 *
 * @HotBitmapGG
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

  private int previousTotal = 0;

  private boolean loading = true;

  private int lastCompletelyVisiableItemPosition, visibleItemCount, totalItemCount;

  private int currentPage = 1;

  private LinearLayoutManager mLinearLayoutManager;


  public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {

    this.mLinearLayoutManager = linearLayoutManager;
  }


  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

    super.onScrolled(recyclerView, dx, dy);

    visibleItemCount = recyclerView.getChildCount();
    totalItemCount = mLinearLayoutManager.getItemCount();
    lastCompletelyVisiableItemPosition
        = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();

    if (loading) {
      if (totalItemCount > previousTotal) {
        loading = false;
        previousTotal = totalItemCount;
      }
    }
    if (!loading
        && (visibleItemCount > 0)
        && (lastCompletelyVisiableItemPosition >= totalItemCount - 1)) {
      currentPage++;
      onLoadMore(currentPage);
      loading = true;
    }
  }


  public abstract void onLoadMore(int currentPage);
}