package com.example.windows.ijingdong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.ijingdong.bean.RegistBean;
import com.example.windows.ijingdong.regist.presenter.RegistPresenter;
import com.example.windows.ijingdong.regist.view.IRegistView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//注册页面
public class RegistActivity extends AppCompatActivity implements IRegistView {

    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.txt_regist_qiye)
    TextView txtRegistQiye;
    @BindView(R.id.et_regist_username)
    EditText etRegistUsername;
    @BindView(R.id.et_regist_password)
    EditText etRegistPassword;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        registPresenter = new RegistPresenter();
        registPresenter.attach(this);
    }

    @Override
    public void success(RegistBean registBean) {
        Toast.makeText(this,"注册成功" ,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,"注册失败,请重新注册"+e ,Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.image_back, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_regist:
                String mobile = etRegistUsername.getText().toString().trim();
                String password = etRegistPassword.getText().toString();
                if (!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(password)) {
                    if (mobile.length() != 11) {
                        Toast.makeText(this, "请核查手机号是否正确", Toast.LENGTH_LONG).show();
                    } else {
                        registPresenter.regist(mobile, password);

                    }
                } else {
                    Toast.makeText(this, "手机号或密码不能为空", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
