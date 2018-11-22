package com.example.windows.ijingdong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.windows.ijingdong.adapter.AddressAdapter;
import com.example.windows.ijingdong.address.presenter.AddressPresenter;
import com.example.windows.ijingdong.address.view.IAddressView;
import com.example.windows.ijingdong.bean.AddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends AppCompatActivity implements IAddressView{

    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.rc_address)
    RecyclerView rcAddress;
    @BindView(R.id.btn_add_address)
    Button btnAddAddress;
    private AddressPresenter addressPresenter;
    private List<AddressBean.DataBean> list;
    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        addressPresenter = new AddressPresenter();
        addressPresenter.attach(this);
        SharedPreferences sp = getSharedPreferences("config",MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        addressPresenter.getAddress(uid);
        addressAdapter = new AddressAdapter(this,list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcAddress.setLayoutManager(layoutManager);
        rcAddress.setAdapter(addressAdapter);
    }

    @OnClick({R.id.image_back, R.id.btn_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_add_address:
                Intent intent = new Intent(this,AddAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void success(AddressBean addressBean) {
        list.clear();
        list.addAll(addressBean.getData());
        addressAdapter.notifyDataSetChanged();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,"请求失败"+e,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addressPresenter.dettach();
    }
}
