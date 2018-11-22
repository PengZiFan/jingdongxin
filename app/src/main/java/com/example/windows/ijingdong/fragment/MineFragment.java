package com.example.windows.ijingdong.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.ijingdong.CloseActivity;
import com.example.windows.ijingdong.LoginActivity;
import com.example.windows.ijingdong.MyDingDanActivity;
import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.adapter.BottomDetailsAdapter;
import com.example.windows.ijingdong.bean.BottomDetailsBean;
import com.example.windows.ijingdong.bottomdetails.presenter.BottomDetailsPresenter;
import com.example.windows.ijingdong.bottomdetails.view.IBottomDetailsView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

public class MineFragment extends Fragment implements IBottomDetailsView {

    @BindView(R.id.image_login)
    SimpleDraweeView imageLogin;
    @BindView(R.id.txt_login)
    TextView txtLogin;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.rc_bottom)
    RecyclerView rcBottom;
    @BindView(R.id.image_mydd)
    ImageView imageMydd;
    private Intent intent;
    private boolean key;
    private List<BottomDetailsBean.DataBean.TuijianBean.ListBeanX> beanList;
    private BottomDetailsAdapter bottomDetailsAdapter;
    private BottomDetailsPresenter bottomDetailsPresenter;
    private SharedPreferences sp;
    private String login;
    private String username;
    private String iconurl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdata();
        bottomdetails();
    }

    @SuppressLint("CommitPrefEdits")
    @OnClick({R.id.image_login, R.id.txt_login, R.id.ll_login,R.id.image_mydd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_login:
                if (login.equals("true")) {
                    intent = new Intent(getContext(), CloseActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.image_mydd:
                Intent intents = new Intent(getActivity(), MyDingDanActivity.class);
                startActivity(intents);
                break;
        }
    }


    private void initdata() {
        bottomDetailsPresenter = new BottomDetailsPresenter();
        bottomDetailsPresenter.attach(this);
        bottomDetailsPresenter.getBottomDetails();
    }

    private void bottomdetails() {
        beanList = new ArrayList<>();
        bottomDetailsAdapter = new BottomDetailsAdapter(getContext(), beanList);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(),2);
        rcBottom.setLayoutManager(manager);
        rcBottom.setAdapter(bottomDetailsAdapter);
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
    public void onResume() {
        super.onResume();
        sp = getActivity().getSharedPreferences("config", MODE_PRIVATE);
        username = sp.getString("username", null);
        //第三方QQ登录的头像
        iconurl = sp.getString("iconurl", null);
        imageLogin.setImageURI(iconurl);
        String nickname = sp.getString("nickname", null);
        //判断登录状态
        login = sp.getString("login", "");
        if (login.equals("true")) {
            txtLogin.setText(nickname);
        } else {
            txtLogin.setText("登录/注册>");
        }

        //上传头像
        String icon = sp.getString("icon", null);
        String replace = icon.replace("https", "http");
        if (login.equals("true")){
            imageLogin.setImageURI(replace);
        }else{
            imageLogin.setImageResource(R.mipmap.user);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bottomDetailsPresenter.dettach();
    }


}
