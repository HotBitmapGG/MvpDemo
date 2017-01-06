package io.netopen.hotbitmapgg.demo.mvp.ui;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.netopen.hotbitmapgg.demo.R;
import io.netopen.hotbitmapgg.demo.mvp.bean.MeiziInfo;
import io.netopen.hotbitmapgg.demo.mvp.presenter.MainPresenterImpl;
import io.netopen.hotbitmapgg.demo.mvp.ui.adapter.MainAdapter;
import io.netopen.hotbitmapgg.demo.mvp.view.IMainView;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements IMainView {

  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  @Bind(R.id.progress_bar) ProgressBar mProgressBar;
  private MainAdapter mAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initRecyclerView();
    initData();
  }


  private void initData() {
    MainPresenterImpl mainPresenter = new MainPresenterImpl(this);
    mainPresenter.loadData();
  }


  private void initRecyclerView() {

    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mAdapter = new MainAdapter();
    mRecyclerView.setAdapter(mAdapter);
  }


  @Override public void showProgress() {
    mProgressBar.setVisibility(View.VISIBLE);
  }


  @Override public void hideProgress() {
    mProgressBar.setVisibility(View.GONE);
  }


  @Override public void fillData(List<MeiziInfo.ResultsBean> resultsBeanList) {
    mAdapter.setDataSources(resultsBeanList);
    mAdapter.notifyDataSetChanged();
  }


  @Override protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }
}
