package com.example.windows.ijingdong.bean;

/**
 * Created by Windows on 2018/11/20.
 */

public class UpdateNickNameBean {
    /**
     * msg : 昵称修改成功
     * code : 0
     */

    private String msg;
    private String code;

    @Override
    public String toString() {
        return "UpdateNickNameBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
