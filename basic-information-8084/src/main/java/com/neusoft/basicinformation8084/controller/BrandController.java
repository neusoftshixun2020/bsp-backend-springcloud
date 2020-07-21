package com.neusoft.basicinformation8084.controller;

import com.neusoft.basicinformation8084.common.base.BaseController;
import com.neusoft.basicinformation8084.common.base.BaseModel;
import com.neusoft.basicinformation8084.common.base.BaseModelJson;
import com.neusoft.basicinformation8084.common.validationGroup.DeleteGroup;
import com.neusoft.basicinformation8084.common.validationGroup.UpdateGroup;
import com.neusoft.basicinformation8084.mvo.entity.Brand;
import com.neusoft.basicinformation8084.mvo.entity.Manufacturer;
import com.neusoft.basicinformation8084.mvo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/brand")
public class BrandController extends BaseController {

    @Autowired
    BrandService brandService;

    @PostMapping("/getBrandByFilter")
    public BaseModelJson<List<Brand>> getAllByFilter(@RequestBody Manufacturer manufacturer) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", manufacturer.getUser_id());
        List<Brand> brands = brandService.getAllByFilter(map);
        if (brands.size() == 0) {
            throwException("not_exists");
        } else {
            BaseModelJson<List<Brand>> result = new BaseModelJson<>();
            result.code = 200;
            result.data = brands;
            return result;
        }
        return null;
    }

    @PostMapping("/addBrand")
    public BaseModel addBrand(@RequestBody Brand brand, BindingResult bindingResult) {
        System.out.println(brand);
        if (bindingResult.hasErrors()) {
            throwException("insert_fail");
        } else {
            BaseModel result = new BaseModel();
            int i = brandService.insert(brand);
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                throwException("insert_fail");
            }
        }
        return null;
    }

    @PostMapping("/addOrUpdateBrand")
    public BaseModel addOrUpdateBrand(@Validated({UpdateGroup.class}) @RequestBody Brand brand, BindingResult bindingResult) {
        System.out.println(brand);
        if (bindingResult.hasErrors()) {
            throwException("insert_fail");
        } else {
            BaseModel result = new BaseModel();
            int i = brandService.insert(brand);
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                int j = brandService.update(brand);
                if (j == 1) {
                    result.code = 200;
                    return result;
                } else {
                    throwException("operation_fail");
                }
            }
        }
        return null;
    }


    @PostMapping("/deleteBrand")
    public BaseModel deleteBrand(@Validated({DeleteGroup.class}) @RequestBody Brand brand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throwException("userid_null_error");

        } else {
            BaseModel result = new BaseModel();
            int i = brandService.delete(brand.getId());
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                throwException("delete_fail");
            }
        }
        return null;
    }

    @PostMapping("/deleteAllBrand")
    public BaseModel deleteAllBrand(@Validated({DeleteGroup.class}) @RequestBody List<Brand> brands, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throwException("update_fail");
        } else {
            BaseModel result = new BaseModel();
            for (Brand brand: brands){
                int i = brandService.delete(brand.getId());
                if (i==0){
                    throwException("update_fail");
                }
            }
            result.code = 200;
            return result;
        }
        return null;
    }


    @PostMapping("/updateBrand")
    public BaseModel updateBrand(@Validated({UpdateGroup.class}) @RequestBody Brand brand,  BindingResult bindingResult) {  //bindingResult用于获得validate的反馈信息
        if (bindingResult.hasErrors()) {
            throwException("userid_null_error");
        } else {
            BaseModel result = new BaseModel();
            System.out.println(brand);
            int i = brandService.update(brand);
            if(i==1){
                result.code = 200;
                return result;
            }else{
                throwException("update_fail");
            }
        }
        return null;
    }
}
