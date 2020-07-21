package com.neusoft.order8085.order.service;


import com.neusoft.order8085.order.entity.ShippingAddress;

import java.util.List;
import java.util.Map;

public interface ShippingAddressService {
    int insert(ShippingAddress shippingAddress);

    int update(ShippingAddress shippingAddress);

    int delete(int pk);

    ShippingAddress getById(int pk);

    List<ShippingAddress> getAllByFilter(Map<String, Object> map);

    List<ShippingAddress> getAll();
}
