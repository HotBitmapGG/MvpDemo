package io.netopen.hotbitmapgg.demo.mvp.bean;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by hcc on 2017/2/16 16:10
 * 100332338@qq.com
 * MvpDemo
 *
 * @HotBitmapGG http://www.jianshu.com/p/4986100eff90
 */

public class UserDao {
  @Id
  private Long id;

  private String name;

  @Transient
  private int tempUsageCount;
}
