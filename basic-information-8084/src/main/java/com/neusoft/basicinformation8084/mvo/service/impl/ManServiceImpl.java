package com.neusoft.basicinformation8084.mvo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.basicinformation8084.mvo.entity.Manufacturer;
import com.neusoft.basicinformation8084.mvo.mapper.ManMapper;
import com.neusoft.basicinformation8084.mvo.service.ManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("manService")
public class ManServiceImpl implements ManService {

    @Autowired
    ManMapper manMapper;

    @Override
    public int insert(Manufacturer manufacturer) {
        return manMapper.insert(manufacturer);
    }

    @Override
    public int update(Manufacturer manufacturer) {
        return manMapper.update(manufacturer);
    }

    @Override
    public int delete(String pk) {
        return manMapper.delete(pk);
    }

    @Override
    public Manufacturer getById(String manid) {
        return manMapper.getById(manid);
    }

    @Override
    public List<Manufacturer> getAllByFilter(Map<String, Object> map) {
        return manMapper.getAllByFilter(map);
    }

    @Override
    public List<Manufacturer> getAll() {
        return manMapper.getAll();
    }

    @Override
    public PageInfo<Manufacturer> getAllByFilter(Integer pageNum, Integer pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNum,pageSize,true);
        List<Manufacturer> manufacturer = manMapper.getAllByFilter(map);
        return new PageInfo<>(manufacturer);
    }
}
