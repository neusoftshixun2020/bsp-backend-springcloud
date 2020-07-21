package com.neusoft.userpermission8083.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.github.pagehelper.PageInfo;
import com.neusoft.userpermission8083.common.base.BaseController;
import com.neusoft.userpermission8083.common.base.BaseModel;
import com.neusoft.userpermission8083.common.base.BaseModelJson;
import com.neusoft.userpermission8083.common.base.BaseModelTokenJson;
import com.neusoft.userpermission8083.jwt.JwtTokenUtil;
import com.neusoft.userpermission8083.jwt.LoginToken;
import com.neusoft.userpermission8083.user.entity.User;
import com.neusoft.userpermission8083.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public BaseModel addUser(@RequestBody User user) {
//        if (bindingResult.hasErrors()) {
//            throw BusinessException.INSERT_FAIL.newInstance("504", this.getErrorResponse(bindingResult), new Object[]{user.toString()});
//        } else {
            BaseModel result = new BaseModel();
            Map<String, Object> map = new HashMap<>();
            map.put("username", user.getUsername());
            List<User> users = userService.getAllByFilter(map);
            if (users.size() > 0)
                throwException("duplicate_username");
            Random r = new Random();
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");
            String userId = String.valueOf(r.nextInt(100)) + ft.format(date);
            user.setUser_id(userId);
            int i = userService.insert(user);
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                throwException("insert_fail");
            }
//        }
        return null;
    }

//    @PostMapping("/checkUser")
//    public BaseModel getAllByFilter(@RequestBody User user) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("username", user.getUsername());
//        map.put("password", user.getPassword());
//        List<User> users = userService.getAllByFilter(map);
//        if (users.size() == 0) {
//            throw BusinessException.NOT_EXISTS;
//        } else {
//            BaseModel result = new BaseModel();
//            result.code = 200;
//            return result;
//        }
//    }

    @PostMapping("/checkUser")
    @LoginToken
    public BaseModelJson<User> getAllByFilter(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        try {
            subject.login(token);
            System.out.println(subject.getPrincipal());
            user = (User)subject.getPrincipal();
            BaseModelTokenJson<User> result = new BaseModelTokenJson<>();
            result.code = 200;
            result.data = user;
            String resultToken = JwtTokenUtil.createJWT(6000000, user);
            System.out.println(resultToken);
            result.token = resultToken;
            System.out.println("认证成功");
            System.out.println("Authenticated"+ subject.isAuthenticated());
            return result;
        } catch (UnknownAccountException e) {
            throwException("userid_null_error");
        } catch (IncorrectCredentialsException e) {
            throwException("password_wrong");
        }
        return null;
    }

    @PostMapping("getAllUser")
    public BaseModelJson<List<User>> getAllUser() {
        BaseModelJson<List<User>> result = new BaseModelJson<>();
        List<User> userList = userService.getAll();
        result.code = 200;
        result.data = userList;
        return result;
    }


    @PostMapping("logout")
    public BaseModel logout() {
        BaseModel result = new BaseModel();
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.isAuthenticated());
        result.code = 200;
        return result;
    }

    @PostMapping("getInfo")
    public BaseModelJson<User> getInfo(HttpServletRequest httpServletRequest) {
        String rToken = httpServletRequest.getHeader("token");
        System.out.println(rToken);
        if (rToken == null) {
            throwException("permission_token_invalid");
        }
        String username = null;
        String password = null;
        try {
            username = JWT.decode(rToken).getClaim("username").asString();
            password = JWT.decode(rToken).getClaim("password").asString();
        } catch (JWTDecodeException j) {
            throwException("permission_token_invalid");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            User user = (User)subject.getPrincipal();
            BaseModelTokenJson<User> result = new BaseModelTokenJson<>();
            result.code = 200;
            result.data = user;
            System.out.println("认证成功");
            System.out.println("Authenticated"+ subject.isAuthenticated());
            return result;
        } catch (UnknownAccountException e) {
            throwException("userid_null_error");
        } catch (IncorrectCredentialsException e) {
            throwException("password_wrong");
        }
        return null;
    }

    @PostMapping("/searchUser")
    public BaseModelJson<User> searchUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        List<User> users = userService.getAllByFilter(map);
        if (users.size() == 0) {
            throwException("not_exists");
        } else {
            BaseModelJson<User> result = new BaseModelJson();
            result.code = 200;
            result.data=users.get(0);
            return result;
        }
        return null;
    }

    @PostMapping("/deleteUser")
    public BaseModel deleteUser( @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throwException("userid_null_error");
        } else {
            BaseModel result = new BaseModel();
            int i = userService.delete(user.getId());
            if (i == 1) {
                result.code = 200;
                return result;
            } else {
                throwException("delete_fail");
            }
        }
        return null;
    }

    @PostMapping("/updateUser")
    public BaseModel updateUser(@RequestBody User user, BindingResult bindingResult) {  //bindingResult用于获得validate的反馈信息
        if (bindingResult.hasErrors()) {
            throwException("userid_null_error");
        } else {
            BaseModel result = new BaseModel();
            int i =userService.update(user);
            if(i==1){
                result.code = 200;
                return result;
            }else{
                throwException("update_fail");
            }
        }
        return null;
    }

    @PostMapping("/userlist")
    public BaseModelJson<PageInfo<User>> getUserList(Integer pageNum, Integer pageSize,
                                                     @RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        BaseModelJson<PageInfo<User>> result = new BaseModelJson();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        result.code = 200;
        result.data = userService.getAllByFilter(pageNum, pageSize, map);

        return result;
    }
}
