package com.example.windows.ijingdong.addcart.view;

import com.example.windows.ijingdong.bean.AddCart;

/**
 * Created by Windows on 2018/11/15.
 */

public interface IAddCartView {
    void success(AddCart addCart);
    void failed(Exception e);
}
