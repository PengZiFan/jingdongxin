package com.example.windows.ijingdong.user;

import com.example.windows.ijingdong.bean.UserBean;

/**
 * Created by Windows on 2018/11/22.
 */

public interface IUserView {
    void success(UserBean userBean);
    void failed(Exception e);
}
