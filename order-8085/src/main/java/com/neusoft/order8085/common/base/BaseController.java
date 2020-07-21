package com.neusoft.order8085.common.base;


import com.neusoft.order8085.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

public class BaseController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    public void throwException(String exception) {
          ServiceInstance serviceInstance = loadBalancerClient.choose("exceptionHandler");
          String url = serviceInstance.getUri() + "/" + exception;
//          System.out.println(url);
          RestTemplate restTemplate = new RestTemplate();
          BaseModel result = restTemplate.getForObject(url, BaseModel.class);
          throw new BusinessException(result.code, result.message);
    }

    Logger logger = LoggerFactory.getLogger(Exception.class);

    @ExceptionHandler
    @ResponseBody
    public BaseModel exp(HttpServletRequest request, Exception exception) {
        BaseModel baseModel = new BaseModel();
        exception.printStackTrace();
        if (exception instanceof BusinessException) {
            baseModel.message = exception.getMessage();
            baseModel.code = ((BusinessException) exception).getCode();
            this.logger.error("errorResponse: {}", baseModel.toString());
        } else {
            this.logger.error("errorResponse: {}", baseModel.toString());
            baseModel.code = 500;
            baseModel.message = "Sorry! Exception occurs! Please try it later!";
        }
        return baseModel;
    }

    public String getErrorResponse(BindingResult bindingResult) {
        StringBuffer sb = new StringBuffer();
        Iterator errIterator = bindingResult.getAllErrors().iterator();
        while (errIterator.hasNext()) {
            ObjectError objectError = (ObjectError)errIterator.next();
            this.logger.info(objectError.getDefaultMessage());
            this.logger.info(objectError.getCode());
            sb.append(objectError.getDefaultMessage()).append(",");
        }
        sb.delete(sb.length()-1, sb.length());
        return sb.toString();
    }
}
