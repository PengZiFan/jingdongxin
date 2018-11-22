package com.example.windows.ijingdong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.windows.ijingdong.address.addAddress.presenter.AddAddressPresenter;
import com.example.windows.ijingdong.address.addAddress.view.IAddAddressView;
import com.example.windows.ijingdong.bean.AddAddressBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends AppCompatActivity implements IAddAddressView{

    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.input_address)
    EditText inputAddress;
    @BindView(R.id.btn_add_address)
    Button btnAddAddress;
    private AddAddressPresenter addAddressPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        addAddressPresenter = new AddAddressPresenter();
        addAddressPresenter.attach(this);
    }

    @OnClick({R.id.image_back, R.id.btn_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_add_address:
                SharedPreferences sp = getSharedPreferences("config" ,MODE_PRIVATE);
                int uid = sp.getInt("uid", 0);
                String Address = inputAddress.getText().toString();
                String phone = inputPhone.getText().toString();
                String Name = inputName.getText().toString();

                if (TextUtils.isEmpty(Address)) {
                    Toast.makeText(this, "收货地址不能为空！", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(this, "主人的名字不能为空啦！", Toast.LENGTH_LONG).show();
                }
                if (uid != 0){
                    addAddressPresenter.addAddress(uid,Address,phone,Name);
                }
                break;
        }
    }


    @Override
    public void success(AddAddressBean addAddressBean) {
        Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,AddressActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "添加失败"+e, Toast.LENGTH_LONG).show();
    }
}
