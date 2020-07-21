package com.neusoft.imagemanager8082.common.base;

import java.io.Serializable;

public interface BaseEntity<E extends Serializable> extends Serializable {
    E getId();

}
