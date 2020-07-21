package com.neusoft.basicinformation8084.product.mapper;

import com.neusoft.basicinformation8084.common.base.BaseMapper;
import com.neusoft.basicinformation8084.product.entity.ProductCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryMapper extends BaseMapper<Integer, ProductCategory> {

   int getProNum(int prc_id);

}
