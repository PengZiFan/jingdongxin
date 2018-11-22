package com.example.windows.ijingdong.sort.right.view;

import com.example.windows.ijingdong.bean.RightBean;

/**
 * Created by Windows on 2018/11/9.
 */

public interface IRightView {

    void success(RightBean rightBean);
    void failed(Exception e);
}
