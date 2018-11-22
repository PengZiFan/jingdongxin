package com.example.windows.ijingdong.touxiang;

import com.example.windows.ijingdong.bean.TouXiangBean;

/**
 * Created by Windows on 2018/11/22.
 */

public interface ITouXiangView {
    void success(TouXiangBean touXiangBean);
    void failed(Exception e);
}
