package com.neusoft.basicinformation8084.product.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.basicinformation8084.product.entity.ProductCategory;
import com.neusoft.basicinformation8084.product.mapper.ProductCategoryMapper;
import com.neusoft.basicinformation8084.product.mapper.ProductMapper;
import com.neusoft.basicinformation8084.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    public int insert(ProductCategory productCategory) {
        return productCategoryMapper.insert(productCategory);
    }

    @Override
    public int update(ProductCategory productCategory) {
        return productCategoryMapper.update(productCategory);
    }

    @Override
    public int delete(int pk) {
        return productCategoryMapper.delete(pk);
    }

    @Override
    public  ProductCategory getById(int pk) {
        return productCategoryMapper.getById(pk);
    }

    @Override
    public List<ProductCategory> getAllByFilter(Map<String, Object> map) {
        List<ProductCategory> productCategories =  productCategoryMapper.getAllByFilter(map);
        for (ProductCategory productCategory:productCategories) {
            productCategory.setProduct(productMapper.getById(productCategory.getPro_id()));
        }
        return productCategories;
    }

    @Override
    public List<ProductCategory> getAll() {
        return productCategoryMapper.getAll();
    }

    @Override
    public PageInfo<ProductCategory> getAllByFilter(Integer pageNum, Integer pagesize, Map<String, Object> map) {
        PageHelper.startPage(pageNum, pagesize, true);
        List<ProductCategory> productCategories = productCategoryMapper.getAllByFilter(map);
        return new PageInfo<>(productCategories);
    }

    @Override
    public int getProNum(int prc_id) {
        return productCategoryMapper.getProNum(prc_id);
    }
}
