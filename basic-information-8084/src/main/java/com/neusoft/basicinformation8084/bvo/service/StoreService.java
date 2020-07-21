package com.neusoft.basicinformation8084.bvo.service;


import com.neusoft.basicinformation8084.bvo.entity.Store;

import java.util.List;
import java.util.Map;

public interface StoreService {
    int insert(Store store);

    int update(Store store);

    int delete(int pk);

    Store getById(int pk);

    List<Store> getAllByFilter(Map<String, Object> map);

    List<Store> getAll();
}
