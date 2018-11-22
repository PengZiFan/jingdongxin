package com.example.windows.ijingdong.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.adapter.MyDingDanAdapter;
import com.example.windows.ijingdong.bean.MyDingDanBean;
import com.example.windows.ijingdong.mydingdan.presenter.MyDingDanPresenter;
import com.example.windows.ijingdong.mydingdan.view.IMyDingDanView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsFragment extends Fragment implements IMyDingDanView {

    @BindView(R.id.image_back_dingdan)
    ImageView imageBackDingdan;
    @BindView(R.id.rc_dingdan)
    RecyclerView rcDingdan;
    private List<MyDingDanBean.DataBean> list;
    private MyDingDanPresenter myDingDanPresenter;
    private MyDingDanAdapter myDingDanAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        myDingDanPresenter = new MyDingDanPresenter();
        myDingDanPresenter.attach(this);
        SharedPreferences sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        myDingDanPresenter.getMyDingDan(uid);
        myDingDanAdapter = new MyDingDanAdapter(getActivity(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcDingdan.setLayoutManager(layoutManager);
        rcDingdan.setAdapter(myDingDanAdapter);

    }

    @Override
    public void success(MyDingDanBean myDingDanBean) {
        list.clear();
        list.addAll(myDingDanBean.getData());
        myDingDanAdapter.notifyDataSetChanged();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(getActivity(), "请求失败" + e, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myDingDanPresenter.dettach();
    }


    @OnClick({R.id.image_back_dingdan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back_dingdan:
                getActivity().finish();
                break;
        }
    }
}
