package com.example.windows.ijingdong.bottomdetails.view;

import com.example.windows.ijingdong.bean.BottomDetailsBean;

import java.util.List;

/**
 * Created by Windows on 2018/11/8.
 */

public interface IBottomDetailsView {
    void success(BottomDetailsBean bottomDetailsBean);
    void failed(Exception e);
}
