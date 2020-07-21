package com.neusoft.order8085.order.service;


import com.neusoft.order8085.order.entity.SalesOrderLineItem;

import java.util.List;
import java.util.Map;

public interface SalesOrderLineItemService {
    int insert(SalesOrderLineItem salesOrderLineItem);

    int update(SalesOrderLineItem salesOrderLineItem);

    int delete(int pk);

    SalesOrderLineItem getById(int pk);

    List<SalesOrderLineItem> getAllByFilter(Map<String, Object> map);

    List<SalesOrderLineItem> getAll();
}
