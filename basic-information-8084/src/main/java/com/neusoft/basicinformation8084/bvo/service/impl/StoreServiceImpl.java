package com.neusoft.basicinformation8084.bvo.service.impl;

import com.neusoft.basicinformation8084.bvo.entity.Store;
import com.neusoft.basicinformation8084.bvo.mapper.StoreMapper;
import com.neusoft.basicinformation8084.bvo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreMapper storeMapper;

    @Override
    public int insert(Store store) {
        return storeMapper.insert(store);
    }

    @Override
    public int update(Store store) {
        return storeMapper.update(store);
    }

    @Override
    public int delete(int pk) {
        return storeMapper.delete(pk);
    }

    @Override
    public Store getById(int pk) {
        return storeMapper.getById(pk);
    }

    @Override
    public List<Store> getAllByFilter(Map<String, Object> map) {
        return storeMapper.getAllByFilter(map);
    }

    @Override
    public List<Store> getAll() {
        return storeMapper.getAll();
    }
}
