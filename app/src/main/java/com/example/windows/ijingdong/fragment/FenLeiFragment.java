package com.example.windows.ijingdong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.XiangQingActivity;
import com.example.windows.ijingdong.adapter.LeftAdapter;
import com.example.windows.ijingdong.adapter.RightAdapter;
import com.example.windows.ijingdong.bean.GridBean;
import com.example.windows.ijingdong.bean.RightBean;
import com.example.windows.ijingdong.grid.presenter.GridPresenter;
import com.example.windows.ijingdong.grid.view.IGridView;
import com.example.windows.ijingdong.sort.right.presenter.RightPresenter;
import com.example.windows.ijingdong.sort.right.view.IRightView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FenLeiFragment extends Fragment implements IGridView, IRightView {

    public static final String TAG = "FenLeiFragment";
    @BindView(R.id.rc_left)
    RecyclerView rcLeft;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.vp_lunbo)
    FlyBanner vpLunbo;
    private List<GridBean.DataBean> list;
    private LeftAdapter leftAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private GridPresenter gridPresenter;
    private List<RightBean.DataBean.ListBean> listBeans;
    private RightPresenter rightPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fen_lei, container, false);
        ButterKnife.bind(this, view);
        lunbo();
        return view;
    }

    private void lunbo() {
        List<String> lunboList = new ArrayList<>();
        lunboList.add("http://www.zhaoapi.cn/images/quarter/ad1.png");
        lunboList.add("http://www.zhaoapi.cn/images/quarter/ad2.png");
        lunboList.add("http://www.zhaoapi.cn/images/quarter/ad3.png");
        lunboList.add("http://www.zhaoapi.cn/images/quarter/ad4.png");
        vpLunbo.setImagesUrl(lunboList);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();

        gridPresenter = new GridPresenter();
        gridPresenter.attach(this);
        gridPresenter.getGrid();

        leftAdapter = new LeftAdapter(getActivity(), list);
        layoutManager = new LinearLayoutManager(getActivity());
        rcLeft.setLayoutManager(layoutManager);
        rcLeft.setAdapter(leftAdapter);
        rightPresenter = new RightPresenter();
        rightPresenter.attah(this);
        leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                GridBean.DataBean dataBean = list.get(position);
                rightPresenter.getRight(dataBean.getCid());
            }
        });
    }

    @Override
    public void success(GridBean gridBean) {
        list.clear();
        list.addAll(gridBean.getData());
        leftAdapter.notifyDataSetChanged();
    }

    @Override
    public void success(final RightBean rightBean) {
        llRight.removeAllViews();
        for (  int i = 0; i < rightBean.getData().size(); i++) {
            TextView textView = new TextView(getActivity());
            textView.setText(rightBean.getData().get(i).getName());
            llRight.addView(textView);

            listBeans = new ArrayList<>();
            RecyclerView recyclerView = new RecyclerView(getActivity());
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
            RightAdapter rightAdapter = new RightAdapter(getActivity(), listBeans);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(rightAdapter);

            final int finalI = i;
            rightAdapter.setOnRightItemClickListener(new RightAdapter.OnRightItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
                    Intent intent = new Intent(getActivity(), XiangQingActivity.class);
                    intent.putExtra("pid",list.get(position).getCid());
                    startActivity(intent);
                }
            });

            listBeans.clear();
            listBeans.addAll(rightBean.getData().get(i).getList());
            rightAdapter.notifyDataSetChanged();
            llRight.addView(recyclerView);

        }
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void onResume() {
        super.onResume();
        rightPresenter.getRight("1");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
