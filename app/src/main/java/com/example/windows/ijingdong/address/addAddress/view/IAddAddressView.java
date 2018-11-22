package com.example.windows.ijingdong.address.addAddress.view;

import com.example.windows.ijingdong.bean.AddAddressBean;

/**
 * Created by Windows on 2018/11/19.
 */

public interface IAddAddressView {
    void success(AddAddressBean addAddressBean);
    void failed(Exception e);
}
