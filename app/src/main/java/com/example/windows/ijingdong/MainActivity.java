package com.example.windows.ijingdong;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.windows.ijingdong.fragment.FenLeiFragment;
import com.example.windows.ijingdong.fragment.FirstFragment;
import com.example.windows.ijingdong.fragment.FoundFragment;
import com.example.windows.ijingdong.fragment.MineFragment;
import com.example.windows.ijingdong.fragment.CartFragment;
import com.hjm.bottomtabbar.BottomTabBar;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_bar)
    BottomTabBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity or Fragment instance
        rxPermissions
                .request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(
                );

        bottomBar.init(getSupportFragmentManager())
                //设置字体大小
                .setFontSize(10)
                .setImgSize(30,30)
                .setTabBarBackgroundResource(0)
                .setChangeColor(Color.RED,Color.GRAY)
                .addTabItem("首页",R.drawable.home, FirstFragment.class)
                .addTabItem("分类",R.drawable.classily, FenLeiFragment.class)
                .addTabItem("发现",R.drawable.sou, FoundFragment.class)
                .addTabItem("购物车",R.drawable.shop, CartFragment.class)
                .addTabItem("我的",R.drawable.my, MineFragment.class)
                .isShowDivider(false);
    }

}
