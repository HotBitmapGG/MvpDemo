package io.netopen.hotbitmapgg.demo.mvp.ui.adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import io.netopen.hotbitmapgg.demo.R;
import io.netopen.hotbitmapgg.demo.mvp.bean.MeiziInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by hcc on 2017/1/4 13:37
 * 100332338@qq.com
 * Demo
 *
 * @HotBitmapGG
 */

public class MeiziAdapter extends AbsRecyclerViewAdapter<MeiziInfo.ResultsBean> {

  @Override
  public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    bindContext(parent.getContext());
    return new ItemViewHolder(
        LayoutInflater.from(getContext()).inflate(R.layout.item_main, parent, false));
  }


  @Override
  public void onBindViewHolder(ClickableViewHolder holder, int position) {
    if (holder instanceof ItemViewHolder) {
      ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      MeiziInfo.ResultsBean resultsBean = mDataSources.get(position);

      Glide.with(getContext())
          .load(resultsBean.getUrl())
          .centerCrop()
          .diskCacheStrategy(DiskCacheStrategy.ALL)
          .into(itemViewHolder.imageView);
    }
    super.onBindViewHolder(holder, position);
  }


  private class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

    ImageView imageView;


    ItemViewHolder(View itemView) {
      super(itemView);
      imageView = $(R.id.item_img);
    }
  }
}
