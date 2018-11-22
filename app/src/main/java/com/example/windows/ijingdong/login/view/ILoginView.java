package com.example.windows.ijingdong.login.view;

import com.example.windows.ijingdong.bean.LoginBean;

/**
 * Created by Windows on 2018/11/7.
 */

public interface ILoginView {
    void success(LoginBean loginBean);
    void failed(Exception e);
}
