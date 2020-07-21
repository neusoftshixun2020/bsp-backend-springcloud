package com.neusoft.order8085.order.service;


import com.neusoft.order8085.order.entity.SalesOrder;

import java.util.List;
import java.util.Map;

public interface SalesOrderService {

    int insert(SalesOrder salesOrder);

    int update(SalesOrder salesOrder);

    int delete(int pk);

    SalesOrder getById(int pk);

    SalesOrder getByStoreOrderId(int sto_id);

    List<SalesOrder> getAllByFilter(Map<String, Object> map);

    List<SalesOrder> getAll();

}
