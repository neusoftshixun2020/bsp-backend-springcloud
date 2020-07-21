package com.neusoft.basicinformation8084.controller;

import com.github.pagehelper.PageInfo;
import com.neusoft.basicinformation8084.common.base.BaseController;
import com.neusoft.basicinformation8084.common.base.BaseModel;
import com.neusoft.basicinformation8084.common.base.BaseModelJson;
import com.neusoft.basicinformation8084.common.validationGroup.DeleteGroup;
import com.neusoft.basicinformation8084.product.entity.Product;
import com.neusoft.basicinformation8084.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    ProductService productService;

    @PostMapping("/getProducts")
    public BaseModelJson<PageInfo<Product>> getProducts(Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        BaseModelJson<PageInfo<Product>> result = new BaseModelJson();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        result.code = 200;
        result.data = productService.getAllByFilter(pageNum, pageSize, map);

        return result;
    }

    @PostMapping("/getProductByTitle")
    public BaseModelJson<PageInfo<Product>> getProductByTitle(Integer pageNum, Integer pageSize,
                                                              @RequestBody Product product){
        Map<String, Object> map = new HashMap<>();
        map.put("title", product.getTitle());
        BaseModelJson<PageInfo<Product>> result = new BaseModelJson();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        result.code = 200;
        result.data = productService.getAllByFilter(pageNum, pageSize, map);

        return result;
    }

    @PostMapping("/deleteProduct")
    public BaseModel deleteProduct(@Validated({DeleteGroup.class}) @RequestBody Product product, BindingResult bindingResult) {
        System.out.println(product);
        if (bindingResult.hasErrors()) {
            throwException("userid_null_error");
        } else {
            BaseModel result = new BaseModel();
            int i = productService.delete(product.getId());
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                throwException("delete_fail");
            }
        }
        return null;
    }

    @PostMapping("/addAndUpdateProduct")
    public BaseModel addAndUpdateProduct(@RequestBody Product product, BindingResult bindingResult, String operationFlag) {
        System.out.println(product);
        System.out.println(operationFlag);
        if (bindingResult.hasErrors()) {
            throwException("insert_fail");
        } else {
            BaseModel result = new BaseModel();
            int i=0;
            if (operationFlag.equals("add")){
                i = productService.insert(product);
            }else if (operationFlag.equals("modify")){
                i = productService.update(product);
            }
            if (i == 4) {
                result.code = 200;
                return result;
            } else {
                throwException("insert_fail");
            }
        }
        return null;
    }

    @GetMapping("getProductById")
    public Product getProductById(@RequestParam Integer pro_id) {
        return productService.getById(pro_id);
    }
}
