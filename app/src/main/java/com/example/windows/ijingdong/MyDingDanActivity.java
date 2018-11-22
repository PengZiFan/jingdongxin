package com.example.windows.ijingdong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.windows.ijingdong.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDingDanActivity extends AppCompatActivity {

    @BindView(R.id.tb_news)
    TabLayout tbNews;
    @BindView(R.id.vp_news)
    ViewPager vpNews;
    private List<String> titles;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ding_dan);
        ButterKnife.bind(this);
        //tabLayout 滑动样式
        tbNews.setTabMode(TabLayout.MODE_SCROLLABLE);
        //添加标题
        titles = new ArrayList<>();
        titles.add("待付款");
        titles.add("待发货");
        titles.add("待评价");
        titles.add("退货/售后");
        fragmentList = new ArrayList<>();
        for (String titles:titles) {
            fragmentList.add(new NewsFragment());
        }
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        };
        vpNews.setAdapter(pagerAdapter);
        tbNews.setupWithViewPager(vpNews);
    }

    @OnClick({R.id.tb_news, R.id.vp_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tb_news:
                break;
            case R.id.vp_news:
                break;
        }
    }
}
