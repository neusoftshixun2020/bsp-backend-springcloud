package com.neusoft.basicinformation8084.bvo.service;


import com.neusoft.basicinformation8084.bvo.entity.DropShipper;

import java.util.List;
import java.util.Map;

public interface DropShipperService {
    int insert(DropShipper dropShipper);

    int update(DropShipper dropShipper);

    int delete(int pk);

    DropShipper getById(int pk);

    List<DropShipper> getAllByFilter(Map<String, Object> map);

    List<DropShipper> getAll();
}
