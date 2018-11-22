package com.example.windows.ijingdong;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.ijingdong.bean.UpdateNickNameBean;
import com.example.windows.ijingdong.updatenickname.presenter.UpdateNickNamePresenter;
import com.example.windows.ijingdong.updatenickname.view.IUpdateNickNameView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateNameActivity extends AppCompatActivity implements IUpdateNickNameView{

    @BindView(R.id.gainame_finsh)
    ImageView gainameFinsh;
    @BindView(R.id.gai_queding)
    TextView gaiQueding;
    @BindView(R.id.gai_names)
    EditText gaiNames;
    private UpdateNickNamePresenter updateNickNamePresenter;
    private int uid;
    private String nickname;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);
        ButterKnife.bind(this);
        updateNickNamePresenter = new UpdateNickNamePresenter();
        updateNickNamePresenter.attach(this);
        sp = getSharedPreferences("config",MODE_PRIVATE);
        uid = sp.getInt("uid", 0);

    }

    @OnClick({R.id.gainame_finsh, R.id.gai_queding, R.id.gai_names})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gainame_finsh:
                finish();
                break;
            case R.id.gai_queding:
                nickname = gaiNames.getText().toString();
                updateNickNamePresenter.updateNickName(uid,nickname);
                break;
            case R.id.gai_names:
                break;
        }
    }

    @Override
    public void success(UpdateNickNameBean updateNickNameBean) {
        sp.edit().putString("nickname",nickname).commit();
        Toast.makeText(this,""+updateNickNameBean.getMsg(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,"修改失败"+e,Toast.LENGTH_LONG).show();
    }
}
