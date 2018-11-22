package com.example.windows.ijingdong;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.ijingdong.addcart.presenter.AddCartPresenter;
import com.example.windows.ijingdong.addcart.view.IAddCartView;
import com.example.windows.ijingdong.bean.AddCart;
import com.example.windows.ijingdong.bean.AddMyDingDanBean;
import com.example.windows.ijingdong.bean.XiangQingBean;
import com.example.windows.ijingdong.mydingdan.addmydingdan.model.AddMyDingDanModel;
import com.example.windows.ijingdong.mydingdan.addmydingdan.presenter.AddMyDingDanPresenter;
import com.example.windows.ijingdong.mydingdan.addmydingdan.view.IAddMyDingDanView;
import com.example.windows.ijingdong.xiangqing.presenter.XiangQingPresenter;
import com.example.windows.ijingdong.xiangqing.view.IXiangQingView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiangQingActivity extends AppCompatActivity implements IXiangQingView, IAddCartView ,IAddMyDingDanView{

    @BindView(R.id.sdv_image)
    SimpleDraweeView sdvImage;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_dtitle)
    TextView txtDtitle;
    @BindView(R.id.btn_shopping)
    Button btnShopping;
    @BindView(R.id.btn_addshopping)
    Button btnAddshopping;
    @BindView(R.id.sdv_back)
    ImageView sdvBack;
    @BindView(R.id.sure)
    Button sure;
    @BindView(R.id.layout_shop_add_car)
    RelativeLayout layoutShopAddCar;
    @BindView(R.id.op_close)
    ImageView opClose;
    @BindView(R.id.op_image)
    SimpleDraweeView opImage;
    @BindView(R.id.op_price)
    TextView opPrice;
    @BindView(R.id.op_title)
    TextView opTitle;
    private XiangQingPresenter xiangQingPresenter;
    private int pid;
    private AddMyDingDanPresenter addMyDingDanPresenter;
    private String price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        ButterKnife.bind(this);
        pid = getIntent().getIntExtra("pid", 1);
        xiangQingPresenter = new XiangQingPresenter();
        xiangQingPresenter.attach(this);
        xiangQingPresenter.getXiangQing(pid);

    }

    @Override
    public void success(XiangQingBean xiangQingBean) {
        sdvImage.setImageURI(xiangQingBean.getData().getImages().split("\\|")[0]);
        txtPrice.setText(xiangQingBean.getData().getPrice());
        txtDtitle.setText(xiangQingBean.getData().getTitle());
        opImage.setImageURI(xiangQingBean.getData().getImages().split("\\|")[0]);
        opPrice.setText(xiangQingBean.getData().getPrice());
        opTitle.setText(xiangQingBean.getData().getTitle());
        price = xiangQingBean.getData().getPrice();
    }

    @Override
    public void success(AddCart addCart) {
        Toast.makeText(this, "" + addCart.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(AddMyDingDanBean addMyDingDanBean) {
        Toast.makeText(this, "" + addMyDingDanBean.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "请求失败" + e, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        xiangQingPresenter.dettach();
    }

    @OnClick({R.id.sdv_back, R.id.btn_shopping, R.id.btn_addshopping, R.id.sure, R.id.layout_shop_add_car, R.id.op_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sdv_back:
                finish();
                break;
            case R.id.btn_shopping:
                SharedPreferences sps = getSharedPreferences("config", MODE_PRIVATE);
                int uids = sps.getInt("uid", 1);
                addMyDingDanPresenter = new AddMyDingDanPresenter();
                addMyDingDanPresenter.attach(this);
                addMyDingDanPresenter.addMyDingDan(uids,price);
                break;
            case R.id.btn_addshopping:
                layoutShopAddCar.setVisibility(View.VISIBLE);

                break;
            case R.id.sure:
                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                int uid = sp.getInt("uid", 1);
                Log.i("1232432423423423423", "onViewClicked: " + uid);
                AddCartPresenter addCartPresenter = new AddCartPresenter();
                addCartPresenter.attach(this);
                addCartPresenter.addCart(uid, pid);
                break;
            case R.id.layout_shop_add_car:
                break;
            case R.id.op_close:
                layoutShopAddCar.setVisibility(View.GONE);
                break;
        }
    }

}
