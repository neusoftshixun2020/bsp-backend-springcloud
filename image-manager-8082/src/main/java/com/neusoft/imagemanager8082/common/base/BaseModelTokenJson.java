package com.neusoft.imagemanager8082.common.base;

public class BaseModelTokenJson<T> extends BaseModelJson<T>{
    public String token;

    @Override
    public String toString() {
        return "BaseModelTokenJson{" +
                "token='" + token + '\'' +
                ", data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
