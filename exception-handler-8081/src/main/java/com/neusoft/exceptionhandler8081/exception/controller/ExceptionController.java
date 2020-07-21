package com.neusoft.exceptionhandler8081.exception.controller;

import com.neusoft.exceptionhandler8081.exception.base.BaseController;
import com.neusoft.exceptionhandler8081.exception.entity.BusinessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ExceptionController extends BaseController {

    @RequestMapping("not_exists")
    public void not_exists() {
        throw BusinessException.NOT_EXISTS;
    }

    @RequestMapping("product_not_exists")
    public void product_not_exists() {
        throw BusinessException.PRODUCT_NOT_EXISTS;
    }

    @RequestMapping("password_wrong")
    public void password_wrong() {
        throw BusinessException.PASSWORD_WRONG;
    }

    @RequestMapping("insert_fail")
    public void insert_fail() {
        throw BusinessException.INSERT_FAIL;
    }

    @RequestMapping("update_fail")
    public void update_fail() {
        throw BusinessException.UPDATE_FAIL;
    }

    @RequestMapping("delete_fail")
    public void delete_fail() {
        throw BusinessException.DELETE_FAIL;
    }

    @RequestMapping("userid_null_error")
    public void userid_null_error() {
        throw BusinessException.USERID_NULL_ERROR;
    }

    @RequestMapping("upload_file_fail")
    public void upload_file_fail() {
        throw BusinessException.UPLOAD_FILE_FAIL;
    }

    @RequestMapping("filetype_not_picture")
    public void filetype_not_picture() {
        throw BusinessException.FILETYPE_NOT_PICTURE;
    }

    @RequestMapping("operation_fail")
    public void operation_fail() {
        throw BusinessException.OPERATION_FAIL;
    }

    @RequestMapping("no_login")
    public void no_login() {
        throw BusinessException.NO_LOGIN;
    }

    @RequestMapping("permission_token_expired")
    public void permission_token_expired() {
        throw BusinessException.PERMISSION_TOKEN_EXPIRED;
    }

    @RequestMapping("permission_token_invalid")
    public void permission_token_invalid() {
        throw BusinessException.PERMISSION_TOKEN_INVALID;
    }

    @RequestMapping("duplicate_path")
    public void duplicate_path() {
        throw BusinessException.DUPLICATE_PATH;
    }

    @RequestMapping("duplicate_username")
    public void duplicate_username() {
        throw BusinessException.DUPLICATE_USERNAME;
    }
}
