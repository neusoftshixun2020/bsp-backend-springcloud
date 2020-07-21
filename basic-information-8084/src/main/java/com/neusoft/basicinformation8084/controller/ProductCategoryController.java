package com.neusoft.basicinformation8084.controller;

import com.neusoft.basicinformation8084.common.base.BaseController;
import com.neusoft.basicinformation8084.common.base.BaseModel;
import com.neusoft.basicinformation8084.common.base.BaseModelJson;
import com.neusoft.basicinformation8084.common.validationGroup.DeleteGroup;
import com.neusoft.basicinformation8084.common.validationGroup.UpdateGroup;
import com.neusoft.basicinformation8084.product.entity.Product;
import com.neusoft.basicinformation8084.product.entity.ProductCategory;
import com.neusoft.basicinformation8084.product.service.ProductCategoryService;
import com.neusoft.basicinformation8084.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController extends BaseController {

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductService productService;

    @PostMapping("/getAllCategory")
    public BaseModelJson<List<ProductCategory>> getAllCategory() {
        Map<String, Object> map = new HashMap<>();
        List<ProductCategory> productCategories = productCategoryService.getAllByFilter(map);
        BaseModelJson<List<ProductCategory>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = productCategories;
        return result;
    }



    @PostMapping("/addOrUpdateProductCategory")
    public BaseModel addOrUpdateProductCategory(@Validated({UpdateGroup.class}) @RequestBody ProductCategory productCategory, String operationFlag, BindingResult bindingResult) {
        System.out.println(productCategory);
        System.out.println(operationFlag);
        if (bindingResult.hasErrors()) {
            throwException("insert_fail");
        } else {
            BaseModel result = new BaseModel();
            if (operationFlag.equals("add")) {
                Map<String, Object> map = new HashMap<>();
                map.put("title", productCategory.getProduct().getTitle());
                List<Product> productList = productService.getAllByFilter(map);
                if (productList.size() == 0) {
                    throwException("product_not_exists");
                } else {
                    Product product = productList.get(0);
                    productCategory.setPro_id(product.getPro_id());
                }
                int i = productCategoryService.insert(productCategory);
                if (i == 1) {
                    result.code = 200;
                    result.message = "add success";
                    return result;
                } else {
                    throwException("insert_fail");
                }
            } else if (operationFlag.equals("update")) {
                int j = productCategoryService.update(productCategory);
                if (j == 1) {
                    result.code = 200;
                    result.message = "update success";
                    return result;
                } else {
                    throwException("update_fail");
                }
            } else {
                throwException("operation_fail");
            }
        }
        return null;
    }


    @PostMapping("/deleteProductCategory")
    public BaseModel deleteProductCategory(@Validated({DeleteGroup.class}) @RequestBody ProductCategory productCategory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throwException("userid_null_error");
        } else {
            BaseModel result = new BaseModel();
            int i = productCategoryService.delete(productCategory.getId());
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                throwException("delete_fail");
            }
        }
        return null;
    }
}
