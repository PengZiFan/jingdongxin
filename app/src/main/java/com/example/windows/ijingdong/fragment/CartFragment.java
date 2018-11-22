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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.adapter.ProductAdapter;
import com.example.windows.ijingdong.adapter.ShopperAdapter;
import com.example.windows.ijingdong.bean.CartBean;
import com.example.windows.ijingdong.cart.presenter.CartPresenter;
import com.example.windows.ijingdong.cart.view.ICartView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartFragment extends Fragment implements ICartView {

    @BindView(R.id.rc_shopper)
    RecyclerView rcShopper;
    @BindView(R.id.cb_total_select)
    CheckBox cbTotalSelect;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    private List<CartBean.DataBean> shopperList;
    private CartPresenter cartPresenter;
    private ShopperAdapter shopperAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
         int uid = sp.getInt("uid", 0);
        shopperList = new ArrayList<>();
        cartPresenter = new CartPresenter();
        cartPresenter.attach(this);

        shopperAdapter = new ShopperAdapter(getActivity(), shopperList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcShopper.setLayoutManager(layoutManager);
        rcShopper.setAdapter(shopperAdapter);
        //商家发生变化的监听
        shopperAdapter.setonShopperClickListener(new ShopperAdapter.onShopperClickListener() {
            @Override
            public void onShopperClick(int position, boolean isChecked) {
                //如果商家不选中
                if (!isChecked) {
                    //全选按钮也不选中
                    cbTotalSelect.setChecked(false);
                } else {
                    //遍历商家
                    boolean isAllShopperCheked = true;
                    for (CartBean.DataBean shopper : shopperList) {
                        if (!shopper.isChecked()) {
                            isAllShopperCheked = false;
                            break;
                        }
                    }
                    cbTotalSelect.setChecked(isAllShopperCheked);
                }
                calulatePrice();
            }

        });
        shopperAdapter.setonAddDecreaseProductListener(new ProductAdapter.onAddDecreaseProductListener() {
            @Override
            public void onChange(int position, int num) {
                calulatePrice();
            }
        });
        cbTotalSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = cbTotalSelect.isChecked();
                for (CartBean.DataBean shopper : shopperList) {
                    shopper.setChecked(isChecked);
                    for (CartBean.DataBean.ListBean product : shopper.getList()) {
                        product.setChecked(isChecked);

                    }
                }
                calulatePrice();
                shopperAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void success(CartBean cartBean) {
        shopperList.clear();
        shopperList.addAll(cartBean.getData());
        shopperAdapter.notifyDataSetChanged();
    }

    @Override
    public void failed(Exception e) {
        //Toast.makeText(getActivity(), "请求失败" + e, Toast.LENGTH_LONG).show();
    }

    private void calulatePrice() {
        float totalPrice = 0;
        for (CartBean.DataBean shopper : shopperList) {
            for (CartBean.DataBean.ListBean product : shopper.getList()) {
                if (product.isChecked()) {
                    totalPrice += product.getNum() * product.getPrice();
                }
            }
        }
            txtPrice.setText("总价:"+totalPrice);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sps = getActivity().getSharedPreferences("config",Context.MODE_PRIVATE);
        String login = sps.getString("login", "");
        int uid = sps.getInt("uid", 0);
        //      sps.getInt("uid",0)
        if (login.equals("true") && uid != 0) {
            cartPresenter.getCart(uid);
        }else {
            //cartPresenter.getCart(0);
            shopperList.clear();

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cartPresenter.dettach();
    }
}
