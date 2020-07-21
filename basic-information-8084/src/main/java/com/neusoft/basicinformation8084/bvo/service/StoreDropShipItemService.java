package com.neusoft.basicinformation8084.bvo.service;


import com.neusoft.basicinformation8084.bvo.entity.StoreDropShipItem;

import java.util.List;
import java.util.Map;

public interface StoreDropShipItemService {
    int insert(StoreDropShipItem storeDropShipItem);

    int update(StoreDropShipItem storeDropShipItem);

    int delete(int pk);

    StoreDropShipItem getById(int pk);

    List<StoreDropShipItem> getAllByFilter(Map<String, Object> map);

    List<StoreDropShipItem> getAll();
}
