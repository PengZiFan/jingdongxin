package com.example.windows.ijingdong.address.view;

import android.location.Address;

import com.example.windows.ijingdong.bean.AddressBean;

/**
 * Created by Windows on 2018/11/19.
 */

public interface IAddressView {
    void success(AddressBean addressBean);
    void failed(Exception e);
}
