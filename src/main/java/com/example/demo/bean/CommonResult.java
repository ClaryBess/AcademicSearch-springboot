package com.example.demo.bean;

public class CommonResult {
    Integer status;
    String msg;
    Object data;

    public CommonResult(Integer i, String s, Object o) {
        this.status=i;
        this.msg=s;
        this.data=o;
    }

//    需要有set get 方法
    public void setData(Object data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }
}
