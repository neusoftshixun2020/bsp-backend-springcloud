package com.neusoft.basicinformation8084.common.base;

public class BaseModel {

    public Integer code = 200;

    public String message;

    @Override
    public String toString() {
        return "BaseModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
