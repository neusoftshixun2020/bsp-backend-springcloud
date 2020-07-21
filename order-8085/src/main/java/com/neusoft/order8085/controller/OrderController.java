package com.neusoft.order8085.controller;

import com.neusoft.order8085.common.base.BaseController;
import com.neusoft.order8085.common.base.BaseModel;
import com.neusoft.order8085.common.base.BaseModelJson;
import com.neusoft.order8085.common.exception.BusinessException;
import com.neusoft.order8085.common.validationGroup.UpdateGroup;
import com.neusoft.order8085.order.entity.*;
import com.neusoft.order8085.order.mapper.OrderTrackMapper;
import com.neusoft.order8085.order.service.SalesOrderService;
import com.neusoft.order8085.order.service.ShippingAddressService;
import com.neusoft.order8085.order.service.StoreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    SalesOrderService salesOrderService;

    @Autowired
    StoreOrderService storeOrderService;

    @Autowired
    ShippingAddressService shippingAddressService;

    @Autowired
    OrderTrackMapper orderTrackMapper;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    private List<Manufacturer> getManByUserId(String userid) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("basicInformation");
        String url = serviceInstance.getUri() + "/manufacturer/getManByUserId/?user_id="+userid;
        RestTemplate restTemplate = new RestTemplate();
        Manufacturer[] result = restTemplate.getForObject(url, Manufacturer[].class);
        return Arrays.asList(result);
    }

    private List<Store> getStoreByDsrId(Integer dsrid) {
       ServiceInstance serviceInstance = loadBalancerClient.choose("basicInformation");
       String url = serviceInstance.getUri() + "/bvo/getStoreByDsrId?dsr_id=" + dsrid;
       RestTemplate restTemplate = new RestTemplate();
       Store[] result = restTemplate.getForObject(url, Store[].class);
       return Arrays.asList(result);
    }

    private Product getProductById(Integer pro_id) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("basicInformation");
        String url = serviceInstance.getUri() + "/product/getProductById?pro_id=" + pro_id;
        RestTemplate restTemplate = new RestTemplate();
        Product result = restTemplate.getForObject(url, Product.class);
        return result;
    }

    private void assignProductsToSalesOrder(SalesOrder salesOrder) {
        List<Product> products = new ArrayList<>();
        List<SalesOrderLineItem> salesOrderLineItems = salesOrder.getSalesOrderLineItems();
        for (SalesOrderLineItem salesOrderLineItem: salesOrderLineItems){
            Product product = getProductById(salesOrderLineItem.getPro_id());
            products.add(product);
        }
        salesOrder.setProducts(products);
    }

    @PostMapping("/getAwaitingPayment")
    public BaseModelJson<List<List<SalesOrder>>> getAwaitingPayment(@RequestBody Manufacturer manufacturer){
        List<Manufacturer> manufacturers = getManByUserId(manufacturer.getUser_id());
        List<List<SalesOrder>> allSalesOrders = new ArrayList<>();
        for(Manufacturer manufacturer1: manufacturers){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("man_id", manufacturer1.getMan_id());
            map1.put("order_sts", "1");
            List<SalesOrder> salesOrders = salesOrderService.getAllByFilter(map1);
            for (SalesOrder salesOrder:salesOrders) {
                assignProductsToSalesOrder(salesOrder);
            }
            allSalesOrders.add(salesOrders);
        }
        BaseModelJson<List<List<SalesOrder>>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = allSalesOrders;
        return result;
    }

    @PostMapping("/getAwaitingShipment")
    public BaseModelJson<List<List<SalesOrder>>> getAwaitingShipment(@RequestBody Manufacturer manufacturer){
        List<Manufacturer> manufacturers = getManByUserId(manufacturer.getUser_id());
        List<List<SalesOrder>> allSalesOrders = new ArrayList<>();
        for(Manufacturer manufacturer1: manufacturers){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("man_id", manufacturer1.getMan_id());
            map1.put("order_sts", "2");
            List<SalesOrder> salesOrders = salesOrderService.getAllByFilter(map1);
            for (SalesOrder salesOrder:salesOrders) {
                assignProductsToSalesOrder(salesOrder);
            }
            allSalesOrders.add(salesOrders);
        }
        BaseModelJson<List<List<SalesOrder>>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = allSalesOrders;
        return result;
    }

    @PostMapping("/deliver")
    public BaseModel deliver(@Validated({UpdateGroup.class}) @RequestBody List<SalesOrder> salesOrders, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throwException("update_fail");
        } else {
            BaseModel result = new BaseModel();
            for (SalesOrder salesOrder: salesOrders){
                int i = salesOrderService.update(salesOrder);
                if (i==0){
                    throwException("update_fail");
                }
            }
            result.code = 200;
            return result;
        }
        return null;
    }

    @PostMapping("/getShiped")
    public BaseModelJson<List<List<SalesOrder>>> getShiped(@RequestBody Manufacturer manufacturer){
        List<Manufacturer> manufacturers = getManByUserId(manufacturer.getUser_id());
        List<List<SalesOrder>> allSalesOrders = new ArrayList<>();
        for(Manufacturer manufacturer1: manufacturers){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("man_id", manufacturer1.getMan_id());
            map1.put("order_sts", "3");
            List<SalesOrder> salesOrders = salesOrderService.getAllByFilter(map1);
            for (SalesOrder salesOrder:salesOrders) {
                assignProductsToSalesOrder(salesOrder);
            }

            allSalesOrders.add(salesOrders);
        }
        BaseModelJson<List<List<SalesOrder>>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = allSalesOrders;
        return result;
    }

    @PostMapping("/cancelOrder")
    public BaseModel cancelOrder(@Validated({UpdateGroup.class}) @RequestBody List<SalesOrder> salesOrders, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throwException("update_fail");
        } else {
            BaseModel result = new BaseModel();
            for (SalesOrder salesOrder: salesOrders){
                int i = salesOrderService.update(salesOrder);
                if (i==0){
                    throwException("update_fail");
                }
            }
            result.code = 200;
            return result;
        }
        return null;
    }

    @PostMapping("/getCompleted")
    public BaseModelJson<List<List<SalesOrder>>> getCompleted(@RequestBody Manufacturer manufacturer){
        List<Manufacturer> manufacturers = getManByUserId(manufacturer.getUser_id());
        List<List<SalesOrder>> allSalesOrders = new ArrayList<>();
        for(Manufacturer manufacturer1: manufacturers){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("man_id", manufacturer1.getMan_id());
            map1.put("order_sts", "4");
            List<SalesOrder> salesOrders = salesOrderService.getAllByFilter(map1);
            for (SalesOrder salesOrder:salesOrders) {
                assignProductsToSalesOrder(salesOrder);
            }

            allSalesOrders.add(salesOrders);
        }
        BaseModelJson<List<List<SalesOrder>>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = allSalesOrders;
        return result;
    }

    @PostMapping("/getCancelled")
    public BaseModelJson<List<List<SalesOrder>>> getCancelled(@RequestBody Manufacturer manufacturer){
        List<Manufacturer> manufacturers = getManByUserId(manufacturer.getUser_id());
        List<List<SalesOrder>> allSalesOrders = new ArrayList<>();
        for(Manufacturer manufacturer1: manufacturers){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("man_id", manufacturer1.getMan_id());
            map1.put("order_sts", "5");
            List<SalesOrder> salesOrders = salesOrderService.getAllByFilter(map1);
            for (SalesOrder salesOrder:salesOrders) {
                assignProductsToSalesOrder(salesOrder);
            }

            allSalesOrders.add(salesOrders);
        }
        BaseModelJson<List<List<SalesOrder>>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = allSalesOrders;
        return result;
    }

    @PostMapping("/getTrack")
    public BaseModelJson<List<OrderTrack>> getTrack(@RequestBody SalesOrder salesOrder){
        List<SalesOrderLineItem> salesOrderLineItems = salesOrder.getSalesOrderLineItems();
        List<OrderTrack> orderTracks = new ArrayList<>();
        for (SalesOrderLineItem salesOrderLineItem: salesOrderLineItems){
            orderTracks.add(orderTrackMapper.getById(salesOrderLineItem.getTracking_no()));
        }
        BaseModelJson<List<OrderTrack>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = orderTracks;
        return result;
    }

    @PostMapping("/getShippingAddress")
    public BaseModelJson<List<ShippingAddress>> getShippingAddress(@RequestBody SalesOrder salesOrder){
        Map<String, Object> map = new HashMap<>();
        map.put("sto_id", salesOrder.getSto_id());
        BaseModelJson<List<ShippingAddress>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = shippingAddressService.getAllByFilter(map);
        return result;
    }


    @PostMapping("/getBvoAwaitingPayment")
    public BaseModelJson<List<List<SalesOrder>>> getBvoAwaitingPayment(@RequestBody DropShipper dropShipper){
        List<Store> stores = getStoreByDsrId(dropShipper.getDsr_id());
        List<List<SalesOrder>> allSalesOrders = new ArrayList<>();
        for(Store store: stores){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("str_id", store.getStr_id());

            List<StoreOrder> storeOrders = storeOrderService.getAllByFilter(map1);
            List<SalesOrder> salesOrders = new ArrayList<>();
            for (StoreOrder storeOrder: storeOrders){
                SalesOrder salesOrder = salesOrderService.getByStoreOrderId(storeOrder.getSto_id());
                if (salesOrder.getOrder_sts().equals("1")){
                    assignProductsToSalesOrder(salesOrder);
                    salesOrders.add(salesOrder);
                }
            }
            allSalesOrders.add(salesOrders);
        }
        BaseModelJson<List<List<SalesOrder>>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = allSalesOrders;
        return result;
    }

    @PostMapping("/getBvoAwaitingShipment")
    public BaseModelJson<List<List<SalesOrder>>> getBvoAwaitingShipment(@RequestBody DropShipper dropShipper){
        List<Store> stores = getStoreByDsrId(dropShipper.getDsr_id());
        List<List<SalesOrder>> allSalesOrders = new ArrayList<>();
        for(Store store: stores){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("str_id", store.getStr_id());

            List<StoreOrder> storeOrders = storeOrderService.getAllByFilter(map1);
            List<SalesOrder> salesOrders = new ArrayList<>();
            for (StoreOrder storeOrder: storeOrders){
                SalesOrder salesOrder = salesOrderService.getByStoreOrderId(storeOrder.getSto_id());
                if (salesOrder.getOrder_sts().equals("2")){
                    assignProductsToSalesOrder(salesOrder);
                    salesOrders.add(salesOrder);
                }
            }
            allSalesOrders.add(salesOrders);
        }
        BaseModelJson<List<List<SalesOrder>>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = allSalesOrders;
        return result;
    }
    @PostMapping("/getBvoShiped")
    public BaseModelJson<List<List<SalesOrder>>> getBvoShiped(@RequestBody DropShipper dropShipper){
        List<Store> stores = getStoreByDsrId(dropShipper.getDsr_id());
        List<List<SalesOrder>> allSalesOrders = new ArrayList<>();
        for(Store store: stores){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("str_id", store.getStr_id());

            List<StoreOrder> storeOrders = storeOrderService.getAllByFilter(map1);
            List<SalesOrder> salesOrders = new ArrayList<>();
            for (StoreOrder storeOrder: storeOrders){
                SalesOrder salesOrder = salesOrderService.getByStoreOrderId(storeOrder.getSto_id());
                if (salesOrder.getOrder_sts().equals("3")){
                    assignProductsToSalesOrder(salesOrder);
                    salesOrders.add(salesOrder);
                }
            }
            allSalesOrders.add(salesOrders);
        }
        BaseModelJson<List<List<SalesOrder>>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = allSalesOrders;
        return result;
    }
    @PostMapping("/getBvoCompleted")
    public BaseModelJson<List<List<SalesOrder>>> getBvoCompleted(@RequestBody DropShipper dropShipper){
        List<Store> stores = getStoreByDsrId(dropShipper.getDsr_id());
        List<List<SalesOrder>> allSalesOrders = new ArrayList<>();
        for(Store store: stores){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("str_id", store.getStr_id());

            List<StoreOrder> storeOrders = storeOrderService.getAllByFilter(map1);
            List<SalesOrder> salesOrders = new ArrayList<>();
            for (StoreOrder storeOrder: storeOrders){
                SalesOrder salesOrder = salesOrderService.getByStoreOrderId(storeOrder.getSto_id());
                if (salesOrder.getOrder_sts().equals("4")){
                    assignProductsToSalesOrder(salesOrder);
                    salesOrders.add(salesOrder);
                }
            }
            allSalesOrders.add(salesOrders);
        }
        BaseModelJson<List<List<SalesOrder>>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = allSalesOrders;
        return result;
    }

    @PostMapping("/getBvoCancelled")
    public BaseModelJson<List<List<SalesOrder>>> getBvoCancelled(@RequestBody DropShipper dropShipper){
        List<Store> stores = getStoreByDsrId(dropShipper.getDsr_id());
        List<List<SalesOrder>> allSalesOrders = new ArrayList<>();
        for(Store store: stores){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("str_id", store.getStr_id());

            List<StoreOrder> storeOrders = storeOrderService.getAllByFilter(map1);
            List<SalesOrder> salesOrders = new ArrayList<>();
            for (StoreOrder storeOrder: storeOrders){
                SalesOrder salesOrder = salesOrderService.getByStoreOrderId(storeOrder.getSto_id());
                if (salesOrder.getOrder_sts().equals("5")){
                    assignProductsToSalesOrder(salesOrder);
                    salesOrders.add(salesOrder);
                }
            }
            allSalesOrders.add(salesOrders);
        }
        BaseModelJson<List<List<SalesOrder>>> result = new BaseModelJson<>();
        result.code = 200;
        result.data = allSalesOrders;
        return result;
    }
}
