package com.example.windows.ijingdong.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.SearchActivity;
import com.example.windows.ijingdong.XiangQingActivity;
import com.example.windows.ijingdong.adapter.BottomDetailsAdapter;
import com.example.windows.ijingdong.adapter.GridAdapter;
import com.example.windows.ijingdong.bean.BottomDetailsBean;
import com.example.windows.ijingdong.bean.GridBean;
import com.example.windows.ijingdong.bottomdetails.presenter.BottomDetailsPresenter;
import com.example.windows.ijingdong.bottomdetails.view.IBottomDetailsView;
import com.example.windows.ijingdong.grid.presenter.GridPresenter;
import com.example.windows.ijingdong.grid.view.IGridView;
import com.recker.flybanner.FlyBanner;
import com.sunfusheng.marqueeview.MarqueeView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstFragment extends Fragment implements IGridView, IBottomDetailsView {


    @BindView(R.id.image_scan)
    ImageView imageScan;
    @BindView(R.id.rc_gege)
    RecyclerView rcGege;
    @BindView(R.id.rc_bottom)
    RecyclerView rcBottom;
    @BindView(R.id.vp_lunbo)
    FlyBanner vpLunbo;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.search_bar)
    SearchView searchBar;
    private int REQUEST_CODE = 1;
    private Intent intent;
    private List<GridBean.DataBean> list;
    private GridAdapter gridAdapter;
    private List<BottomDetailsBean.DataBean.TuijianBean.ListBeanX> beanList;
    private BottomDetailsAdapter bottomDetailsAdapter;
    private GridPresenter gridPresenter;
    private BottomDetailsPresenter bottomDetailsPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        //绑定presenter层
        initdata();
        lunbo();
        paomadeng();
        searchBarClick();
        grid();
        bottomdetails();
        setListener();
        return view;
    }

    private void setListener() {
        bottomDetailsAdapter.setOnItemClickListener(new BottomDetailsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(getActivity(), XiangQingActivity.class);
                intent.putExtra("pid", beanList.get(position).getPid());
                intent.putExtra("price",beanList.get(position).getPrice());
                startActivity(intent);
            }
        });
    }

    private void bottomdetails() {
        beanList = new ArrayList<>();
        bottomDetailsAdapter = new BottomDetailsAdapter(getContext(), beanList);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(),2);
        rcBottom.setLayoutManager(manager);
        rcBottom.setAdapter(bottomDetailsAdapter);
    }

    private void initdata() {
        gridPresenter = new GridPresenter();
        gridPresenter.attach(this);
        gridPresenter.getGrid();

        bottomDetailsPresenter = new BottomDetailsPresenter();
        bottomDetailsPresenter.attach(this);
        bottomDetailsPresenter.getBottomDetails();
    }

    private void grid() {
        list = new ArrayList<>();
        gridAdapter = new GridAdapter(getContext(), list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false);
        rcGege.setLayoutManager(layoutManager);
        rcGege.setAdapter(gridAdapter);
    }

    private void searchBarClick() {
        searchBar.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void paomadeng() {
        List<String> info = new ArrayList<>();
        info.add("欢迎访问京东app");
        info.add("大家有没有在 听课");
        info.add("是不是还有人在睡觉");
        info.add("你妈妈在旁边看着呢");
        info.add("赶紧的好好学习吧 马上毕业了");
        info.add("你没有事件睡觉了");
        marqueeView.startWithList(info);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void lunbo() {
        List<String> lunboList = new ArrayList<>();
        lunboList.add("http://www.zhaoapi.cn/images/quarter/ad1.png");
        lunboList.add("http://www.zhaoapi.cn/images/quarter/ad2.png");
        lunboList.add("http://www.zhaoapi.cn/images/quarter/ad3.png");
        lunboList.add("http://www.zhaoapi.cn/images/quarter/ad4.png");
        vpLunbo.setImagesUrl(lunboList);
    }

    @OnClick({R.id.image_scan, R.id.search_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_scan:
                intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    public void success(GridBean gridBean) {
        list.clear();
        list.addAll(gridBean.getData());
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void success(BottomDetailsBean bottomDetailsBean) {
        beanList.clear();
        beanList.addAll(bottomDetailsBean.getData().getTuijian().getList());
        bottomDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(getActivity(), "错误" + e, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gridPresenter.dettach();
        bottomDetailsPresenter.dettach();
    }
}
