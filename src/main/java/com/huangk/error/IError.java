package com.huangk.error;

public interface IError {
    /**
     * 错误码
     */
    Integer getCode();

    /**
     * 错误描述
     */
    String getMsg();
}
