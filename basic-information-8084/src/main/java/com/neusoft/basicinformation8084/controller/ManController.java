package com.neusoft.basicinformation8084.controller;


import com.github.pagehelper.PageInfo;
import com.neusoft.basicinformation8084.common.base.BaseController;
import com.neusoft.basicinformation8084.common.base.BaseModel;
import com.neusoft.basicinformation8084.common.base.BaseModelJson;
import com.neusoft.basicinformation8084.common.validationGroup.InsertGroup;
import com.neusoft.basicinformation8084.common.validationGroup.UpdateGroup;
import com.neusoft.basicinformation8084.mvo.entity.Manufacturer;
import com.neusoft.basicinformation8084.mvo.service.ManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/manufacturer")
public class ManController extends BaseController {

    @Autowired
    ManService manService;

    @PostMapping("/addManufacturer")
    public BaseModel addManufacturer(@Validated({InsertGroup.class}) @RequestBody Manufacturer manufacturer, BindingResult bindingResult) {
        System.out.println(manufacturer);
        if (bindingResult.hasErrors()) {
            throwException("insert_fail");
        } else {
            BaseModel result = new BaseModel();
            String description=manufacturer.getDescription();
            // <p>段落替换为换行
            description = description.replaceAll("<p .*?>", "\r\n");
            // <br><br/>替换为换行
            description = description.replaceAll("<br\\s*/?>", "\r\n");
            // 去掉其它的<>之间的东西
            description = description.replaceAll("\\<.*?>", "");
            System.out.println(description);
            manufacturer.setDescription(description);
            int i = manService.insert(manufacturer);
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                throwException("insert_fail");
            }
        }
        return null;
    }

    @PostMapping("/UpdateManufacturer")
    public BaseModel updateManufacturer(@Validated({UpdateGroup.class}) @RequestBody Manufacturer manufacturer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throwException("update_fail");
        } else {
            BaseModel result = new BaseModel();
            int i = manService.update(manufacturer);
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                throwException("update_fail");
            }
        }
        return null;
    }

    @PostMapping("/getManByFilter")
    public BaseModelJson<PageInfo<Manufacturer>> getManByFilter(Integer pageNum, Integer pageSize,
                                                                @RequestBody Manufacturer manufacturer) {
        Map<String, Object> map = new HashMap<>();
        map.put("man_id", manufacturer.getUser_id());
        BaseModelJson<PageInfo<Manufacturer>> result = new BaseModelJson<>();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        result.code = 200;
        result.data = manService.getAllByFilter(pageNum, pageSize, map);

        return result;
    }

    @GetMapping("getManByUserId")
    public List<Manufacturer> getManByUserid(@RequestParam String user_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user_id);
        return manService.getAllByFilter(map);
    }
}
