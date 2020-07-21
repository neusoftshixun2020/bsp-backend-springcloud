package com.neusoft.imagemanager8082.common.base;

public class BaseModelJson<T> extends BaseModel {

    public T data;

    @Override
    public String toString() {
        return "BaseModelJson{" +
                "data=" + data +
                '}';
    }
}
