package com.veryworks.android.httpsignin.model;

/**
 * Created by pc on 10/25/2017.
 */

public class Result {
    public static final String OK = "200";
    String code;
    String msg;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public boolean isOk(){
        return OK.equals(code);
    }
}
