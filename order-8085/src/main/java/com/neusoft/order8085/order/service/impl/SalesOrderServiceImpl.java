package com.neusoft.order8085.order.service.impl;

import com.neusoft.order8085.order.entity.SalesOrder;
import com.neusoft.order8085.order.entity.SalesOrderLineItem;
import com.neusoft.order8085.order.mapper.SalesOrderLineItemMapper;
import com.neusoft.order8085.order.mapper.SalesOrderMapper;
import com.neusoft.order8085.order.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    @Autowired
    SalesOrderMapper salesOrderMapper;
    @Autowired
    SalesOrderLineItemMapper salesOrderLineItemMapper;
//    @Autowired
//    ProductService productService;

    @Override
    public int insert(SalesOrder salesOrder) {
        int i = salesOrderMapper.insert(salesOrder);
        for (SalesOrderLineItem salesOrderLineItem: salesOrder.getSalesOrderLineItems()){
            int i1 = salesOrderLineItemMapper.insert(salesOrderLineItem);
            if (i1==0){
                return 0;
            }
        }
        return 1;
    }

    @Override
    public int update(SalesOrder salesOrder) {
        int u = salesOrderMapper.update(salesOrder);
        for (SalesOrderLineItem salesOrderLineItem: salesOrder.getSalesOrderLineItems()){
            int u2 = salesOrderLineItemMapper.update(salesOrderLineItem);
            if (u2==0){
                return 0;
            }
        }
        return 1;
    }

    @Override
    public int delete(int pk) {
        return salesOrderMapper.delete(pk);
    }

    @Override
    public SalesOrder getById(int pk) {
        SalesOrder salesOrder = salesOrderMapper.getById(pk);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("sao_id", salesOrder.getSao_id());
        List<SalesOrderLineItem> salesOrderLineItems = salesOrderLineItemMapper.getAllByFilter(map1);
//        List<Product> products = new ArrayList<>();
//        for (SalesOrderLineItem salesOrderLineItem: salesOrderLineItems){
//            Product product = productService.getById(salesOrderLineItem.getPro_id());
//            products.add(product);
//        }
        salesOrder.setSalesOrderLineItems(salesOrderLineItems);
//        salesOrder.setProducts(products);
        return salesOrder;
    }

    @Override
    public SalesOrder getByStoreOrderId(int sto_id) {
        SalesOrder salesOrder = salesOrderMapper.getByStoreOrderId(sto_id);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("sao_id", salesOrder.getSao_id());
        List<SalesOrderLineItem> salesOrderLineItems = salesOrderLineItemMapper.getAllByFilter(map1);
        salesOrder.setSalesOrderLineItems(salesOrderLineItems);
//        List<Product> products = new ArrayList<>();
//        for (SalesOrderLineItem salesOrderLineItem: salesOrderLineItems){
//            Product product = productService.getById(salesOrderLineItem.getPro_id());
//            products.add(product);
//        }
//        salesOrder.setProducts(products);
        return salesOrder;
    }

    @Override
    public List<SalesOrder> getAllByFilter(Map<String, Object> map) {
        List<SalesOrder> salesOrders = salesOrderMapper.getAllByFilter(map);
        for (SalesOrder salesOrder: salesOrders){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("sao_id", salesOrder.getSao_id());
            List<SalesOrderLineItem> salesOrderLineItems = salesOrderLineItemMapper.getAllByFilter(map1);
            salesOrder.setSalesOrderLineItems(salesOrderLineItems);
//            List<Product> products = new ArrayList<>();
//            for (SalesOrderLineItem salesOrderLineItem: salesOrderLineItems){
//                Product product = productService.getById(salesOrderLineItem.getPro_id());
//                products.add(product);
//            }
//            salesOrder.setProducts(products);
        }
        return salesOrders;
    }

    @Override
    public List<SalesOrder> getAll() {
        Map<String, Object> map1 = new HashMap<>();
        return this.getAllByFilter(map1);
    }
}
