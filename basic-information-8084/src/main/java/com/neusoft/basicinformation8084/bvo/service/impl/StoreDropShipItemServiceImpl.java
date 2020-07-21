package com.neusoft.basicinformation8084.bvo.service.impl;

import com.neusoft.basicinformation8084.bvo.entity.StoreDropShipItem;
import com.neusoft.basicinformation8084.bvo.mapper.StoreDropShipItemMapper;
import com.neusoft.basicinformation8084.bvo.service.StoreDropShipItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class StoreDropShipItemServiceImpl implements StoreDropShipItemService {

    @Autowired
    StoreDropShipItemMapper storeDropShipItemMapper;

    @Override
    public int insert(StoreDropShipItem storeDropShipItem) {
        return storeDropShipItemMapper.insert(storeDropShipItem);
    }

    @Override
    public int update(StoreDropShipItem storeDropShipItem) {
        return storeDropShipItemMapper.update(storeDropShipItem);
    }

    @Override
    public int delete(int pk) {
        return storeDropShipItemMapper.delete(pk);
    }

    @Override
    public StoreDropShipItem getById(int pk) {
        return storeDropShipItemMapper.getById(pk);
    }

    @Override
    public List<StoreDropShipItem> getAllByFilter(Map<String, Object> map) {
        return storeDropShipItemMapper.getAllByFilter(map);
    }

    @Override
    public List<StoreDropShipItem> getAll() {
        return storeDropShipItemMapper.getAll();
    }
}
