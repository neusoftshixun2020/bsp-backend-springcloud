package com.neusoft.basicinformation8084.controller;

import com.neusoft.basicinformation8084.bvo.entity.DropShipper;
import com.neusoft.basicinformation8084.bvo.entity.Store;
import com.neusoft.basicinformation8084.bvo.entity.StoreDropShipItem;
import com.neusoft.basicinformation8084.bvo.entity.WishList;
import com.neusoft.basicinformation8084.bvo.service.DropShipperService;
import com.neusoft.basicinformation8084.bvo.service.StoreDropShipItemService;
import com.neusoft.basicinformation8084.bvo.service.StoreService;
import com.neusoft.basicinformation8084.bvo.service.WishListService;
import com.neusoft.basicinformation8084.common.base.BaseController;
import com.neusoft.basicinformation8084.common.base.BaseModel;
import com.neusoft.basicinformation8084.common.base.BaseModelJson;
import com.neusoft.basicinformation8084.common.exception.BusinessException;
import com.neusoft.basicinformation8084.common.validationGroup.DeleteGroup;
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
@RequestMapping("/bvo")
public class BvoController extends BaseController {

    @Autowired
    WishListService wishListService;

    @Autowired
    StoreDropShipItemService storeDropShipItemService;

    @Autowired
    StoreService storeService;

    @Autowired
    ProductService productService;

    @Autowired
    DropShipperService dropShipperService;

    @PostMapping("/getDsr")
    public BaseModelJson<List<DropShipper>> getDsr(@RequestBody String userid){
        String userid1 = userid.substring(0, userid.length()-1);
        BaseModelJson<List<DropShipper>> result = new BaseModelJson<>();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userid1);
        result.code = 200;
        result.data = dropShipperService.getAllByFilter(map);
        return result;
    }

    @PostMapping("/addStore")
    public BaseModel addStore(@RequestBody Store store, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throwException("insert_fail");
        }else {
            BaseModel result = new BaseModel();
            int i = storeService.insert(store);
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                throwException("insert_fail");
            }
        }
        return null;
    }

    @PostMapping("/getEbayStore")
    public BaseModelJson<List<Store>> getEbayStore(@RequestBody Store store){
        Map<String, Object> map = new HashMap<>();
        map.put("platform_type", String.valueOf(store.getPlatform_type()));
        map.put("dsr_id", store.getDsr_id());
        BaseModelJson<List<Store>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = storeService.getAllByFilter(map);
        return result;
    }

    @PostMapping("/getAmazonStore")
    public BaseModelJson<List<Store>> getAmazonStore(@RequestBody Store store){
        Map<String, Object> map = new HashMap<>();
        map.put("platform_type", String.valueOf(store.getPlatform_type()));
        map.put("dsr_id", store.getDsr_id());
        BaseModelJson<List<Store>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = storeService.getAllByFilter(map);
        return result;
    }

    @PostMapping("/getWishListProducts")
    public BaseModelJson<List<WishList>> getWishListProducts(@RequestBody WishList wishList){
        Map<String, Object> map = new HashMap<>();
        map.put("dsr_id", wishList.getDsr_id());
        List<WishList> wishLists= wishListService.getAllByFilter(map);
        BaseModelJson<List<WishList>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = wishLists;
        return result;
    }

    @PostMapping("/addWishList")
    public BaseModel addWishList(@RequestBody WishList wishList, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throwException("insert_fail");
        }else {
            BaseModel result = new BaseModel();
            int i = wishListService.insert(wishList);
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                throwException("insert_fail");
            }
        }
        return null;
    }

    @PostMapping("/deleteWishList")
    public BaseModel deleteWishList(@Validated({DeleteGroup.class}) @RequestBody WishList wishList, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throwException("userid_null_error");
        } else {
            BaseModel result = new BaseModel();
            int i = wishListService.delete(wishList.getWit_id());
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                throwException("delete_fail");
            }
        }
        return null;
    }

    @PostMapping("/addDropShipItem")
    public BaseModel addDropShipItem(@RequestBody List<StoreDropShipItem> storeDropShipItems, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throwException("insert_fail");
        }else {
            BaseModel result = new BaseModel();
            for (StoreDropShipItem storeDropShipItem: storeDropShipItems){
                int i = storeDropShipItemService.insert(storeDropShipItem);
                if (i != 1){
                    result.code = 504;
                    throwException("insert_fail");
                }
            }
            result.code = 200;
            return result;
        }
        return null;
    }

    @GetMapping("getStoreByDsrId")
    public List<Store> getStoreByDsrId(@RequestParam Integer dsr_id) {
        Map map = new HashMap();
        map.put("dsr_id", dsr_id);
        return storeService.getAllByFilter(map);
    }

}
