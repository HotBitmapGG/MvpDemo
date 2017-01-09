package io.netopen.hotbitmapgg.demo.mvp.ui.activity;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import io.netopen.hotbitmapgg.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * Created by hcc on 2017/1/8 16:45
 * 100332338@qq.com
 * MvpDemo
 *
 * @HotBitmapGG
 */

public class MeiziDetailsActivity extends AppCompatActivity {

  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.image_view) ImageView mImageView;

  private static final String EXTRA_TITLE = "extra_title";
  private static final String EXTRA_URL = "extra_url";
  private String title;
  private String url;


  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_meizi_details);
    ButterKnife.bind(this);

    Intent intent = getIntent();
    if (intent != null) {
      title = intent.getStringExtra(EXTRA_TITLE);
      url = intent.getStringExtra(EXTRA_URL);
    }

    initToolBar();
    initImage();
  }


  private void initImage() {
    Glide.with(MeiziDetailsActivity.this)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(mImageView);
  }


  private void initToolBar() {

    mToolbar.setTitle(title);
    setSupportActionBar(mToolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }


  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }


  public static void launch(Activity activity, String title, String url) {
    Intent intent = new Intent(activity, MeiziDetailsActivity.class);
    intent.putExtra(EXTRA_TITLE, title);
    intent.putExtra(EXTRA_URL, url);
    activity.startActivity(intent);
  }
}
