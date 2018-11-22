package com.example.windows.ijingdong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.ijingdong.bean.LoginBean;
import com.example.windows.ijingdong.login.presenter.LoginPresenter;
import com.example.windows.ijingdong.login.view.ILoginView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CloseActivity extends AppCompatActivity {

    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.txt_service)
    TextView txtService;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    @BindView(R.id.search_address)
    TextView searchAddress;
    @BindView(R.id.img_head)
    SimpleDraweeView imgHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.img_return, R.id.btn_logout, R.id.txt_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                finish();
                break;
            case R.id.btn_logout:
                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                sp.edit().putString("login", "false").commit();
                sp.edit().putInt("uid", 0).commit();
                finish();
                break;
            case R.id.txt_name:
                Intent intent = new Intent(this, MineActivity.class);
                startActivity(intent);
                break;
        }
    }

    @OnClick(R.id.search_address)
    public void onViewClicked() {
        Intent intent = new Intent(this, AddressActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        //修改昵称
        String nickname = sp.getString("nickname", null);
        txtName.setText(nickname);
        //头像上传
        String icon = sp.getString("icon", null);
        String replace = icon.replace("https", "http");

        imgHead.setImageURI(replace);


    }




}
