package com.example.windows.ijingdong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.ijingdong.bean.LoginBean;
import com.example.windows.ijingdong.eventbus.TransmitMobile;
import com.example.windows.ijingdong.login.presenter.LoginPresenter;
import com.example.windows.ijingdong.login.view.ILoginView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.image_logo)
    ImageView imageLogo;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.txt_forget_password)
    TextView txtForgetPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_yanzhengma)
    TextView txtYanzhengma;
    @BindView(R.id.txt_regist)
    TextView txtRegist;
    @BindView(R.id.image_weixin)
    ImageView imageWeixin;
    @BindView(R.id.btnAAdengru)
    ImageView btnAAdengru;
    private LoginPresenter loginPresenter;
    private String mobile;
    private String password;
    private UMShareAPI umShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.attach(this);
        umShareAPI = UMShareAPI.get(this);
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(etUsername.getText())||TextUtils.isEmpty(etPassword.getText())){
                    btnLogin.setBackgroundResource(R.drawable.radio_gray);
                    btnLogin.setEnabled(Boolean.FALSE);
                }else {
                    btnLogin.setBackgroundResource(R.drawable.radio);
                    btnLogin.setEnabled(Boolean.TRUE);
                }
            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(etUsername.getText())||TextUtils.isEmpty(etPassword.getText())){
                    btnLogin.setBackgroundResource(R.drawable.radio_gray);
                    btnLogin.setEnabled(Boolean.FALSE);
                }else {
                    btnLogin.setBackgroundResource(R.drawable.radio);
                    btnLogin.setEnabled(Boolean.TRUE);
                }
            }
        });

    }

    @OnClick({R.id.image_close, R.id.btn_login, R.id.txt_regist, R.id.btnAAdengru})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.btn_login:
                mobile = etUsername.getText().toString().trim();
                password = etPassword.getText().toString();
                if (!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(password)) {
                    if (mobile.length() != 11) {

                        Toast.makeText(this, "请核查手机号是否正确", Toast.LENGTH_LONG).show();
                    } else {
                        btnLogin.setFocusable(true);
                        loginPresenter.login(mobile, password);
                    }
                } else {
                    btnLogin.setFocusable(false);
                    //btnLogin.setBackgroundColor(Color.GRAY);
                    Toast.makeText(this, "手机号或密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            case R.id.txt_regist:
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAAdengru:
                //QQ登录
                UMAuthListener authListener = new UMAuthListener() {
                    /**
                     * @param platform 平台名称
                     * @desc 授权开始的回调
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {

                    }

                    /**
                     * @param platform 平台名称
                     * @param action   行为序号，开发者用不上
                     * @param data     用户资料返回
                     * @desc 授权成功的回调
                     */
                    @Override
                    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                        //授权成功跳转页面
                        Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent1);
                        Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
                        Toast.makeText(LoginActivity.this, "授权成功后的回调数据，用户信息" + data, Toast.LENGTH_LONG).show();
                        String iconurl = data.put("iconurl", "");
                        SharedPreferences sp = getSharedPreferences("config",MODE_PRIVATE);
                        sp.edit().putString("iconurl",iconurl).commit();
                        finish();
                    }

                    /**
                     * @param platform 平台名称
                     * @param action   行为序号，开发者用不上
                     * @param t        错误原因
                     * @desc 授权失败的回调
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                        Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @param platform 平台名称
                     * @param action   行为序号，开发者用不上
                     * @desc 授权取消的回调
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform, int action) {
                        Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
                    }
                };
                umShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
        }
    }
    //第三方登录
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void success(LoginBean loginBean) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        sp.edit().putString("username",loginBean.getData().getUsername())
                .putString("login","true").putInt("uid",loginBean.getData().getUid())
                .putString("nickname",String.valueOf(loginBean.getData().getNickname())).commit();
        finish();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "登录失败"+e, Toast.LENGTH_LONG).show();
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
            loginPresenter.dettach();

    }

}
