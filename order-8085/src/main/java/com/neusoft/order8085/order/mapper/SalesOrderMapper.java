package com.neusoft.order8085.order.mapper;

import com.neusoft.order8085.common.base.BaseMapper;
import com.neusoft.order8085.order.entity.SalesOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderMapper extends BaseMapper<Integer, SalesOrder> {
    SalesOrder getByStoreOrderId(int sto_id);
}
