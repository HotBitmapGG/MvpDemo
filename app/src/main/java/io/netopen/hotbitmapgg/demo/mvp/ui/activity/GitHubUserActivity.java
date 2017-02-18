package io.netopen.hotbitmapgg.demo.mvp.ui.activity;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.netopen.hotbitmapgg.demo.R;
import io.netopen.hotbitmapgg.demo.mvp.bean.UserInfo;
import io.netopen.hotbitmapgg.demo.network.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by hcc on 2017/2/16 11:47
 * 100332338@qq.com
 * MvpDemo
 *
 * @HotBitmapGG
 */

public class GitHubUserActivity extends AppCompatActivity {

  @Bind(R.id.tv) TextView tv;


  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user);
    ButterKnife.bind(this);

    RetrofitHelper.createGitHubService()
        .getUserInfo("Guolei1130")
        .enqueue(new Callback<UserInfo>() {
          @Override public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
            tv.setText(response.body().getLogin());
          }


          @Override public void onFailure(Call<UserInfo> call, Throwable t) {
            tv.setText(t.getMessage());
          }
        });



  }



}
