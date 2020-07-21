package com.neusoft.userpermission8083.user.service;



import com.neusoft.userpermission8083.user.entity.Permission;

import java.util.List;
import java.util.Map;


public interface PermissionService {

    int insert(Permission permission);

    int update(Permission permission);

    int delete(int pk);

    Permission getById(int permission_id);

    List<Permission> getAllByFilter(Map<String, Object> map);

    List<Permission> getAll();

}
