package com.neusoft.userpermission8083.user.service;



import com.neusoft.userpermission8083.user.entity.Role;

import java.util.List;
import java.util.Map;


public interface RoleService {

    int insert(Role role);

    int update(Role role);

    int delete(int pk);

    Role getById(int role_id);

    List<Role> getAllByFilter(Map<String, Object> map);

    List<Role> getAll();

}
