package com.neusoft.exceptionhandler8081.exception.base;

import java.io.Serializable;

public interface BaseEntity<E extends Serializable> extends Serializable {
    E getId();

}
