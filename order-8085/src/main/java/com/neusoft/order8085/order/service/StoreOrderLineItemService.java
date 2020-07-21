package com.neusoft.order8085.order.service;


import com.neusoft.order8085.order.entity.StoreOrderLineItem;

import java.util.List;
import java.util.Map;

public interface StoreOrderLineItemService {
    int insert(StoreOrderLineItem storeOrderLineItem);

    int update(StoreOrderLineItem storeOrderLineItem);

    int delete(int pk);

    StoreOrderLineItem getById(int pk);

    List<StoreOrderLineItem> getAllByFilter(Map<String, Object> map);

    List<StoreOrderLineItem> getAll();
}
