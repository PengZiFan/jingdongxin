package com.example.windows.ijingdong.updatenickname.view;

import com.example.windows.ijingdong.bean.UpdateNickNameBean;

/**
 * Created by Windows on 2018/11/20.
 */

public interface IUpdateNickNameView {
    void success(UpdateNickNameBean updateNickNameBean);
    void failed(Exception e);
}
