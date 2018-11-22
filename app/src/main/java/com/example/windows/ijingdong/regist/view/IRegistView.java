package com.example.windows.ijingdong.regist.view;

import com.example.windows.ijingdong.bean.RegistBean;

/**
 * Created by Windows on 2018/11/8.
 */

public interface IRegistView {
    void success(RegistBean registBean);
    void failed(Exception e);
}
