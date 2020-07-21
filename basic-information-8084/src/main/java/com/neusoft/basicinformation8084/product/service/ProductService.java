package com.neusoft.basicinformation8084.product.service;

import com.github.pagehelper.PageInfo;
import com.neusoft.basicinformation8084.product.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    int insert(Product product);

    int update(Product product);

    int delete(int pk);

    Product getById(int proid);

    List<Product> getAllByFilter(Map<String, Object> map);

    List<Product> getAll();

    PageInfo<Product> getAll(Integer pageNum, Integer pagesize);

    PageInfo<Product> getAllByFilter(Integer pageNum, Integer pagesize, Map<String, Object> map);
}
