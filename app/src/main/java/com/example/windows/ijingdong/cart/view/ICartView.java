package com.example.windows.ijingdong.cart.view;

import com.example.windows.ijingdong.bean.CartBean;

/**
 * Created by Windows on 2018/11/14.
 */

public interface ICartView {
    void success(CartBean cartBean);
    void failed(Exception e);
}
