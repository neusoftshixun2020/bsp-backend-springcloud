package com.neusoft.userpermission8083.user.service;



import com.neusoft.userpermission8083.user.entity.ChildrenRoute;

import java.util.List;
import java.util.Map;


public interface ChildrenRouteService {

    int insert(ChildrenRoute childrenRoute);

    int update(ChildrenRoute ChildrenRoute);

    int delete(int pk);

    ChildrenRoute getById(int childrenRoute_id);

    List<ChildrenRoute> getAllByFilter(Map<String, Object> map);

    List<ChildrenRoute> getAll();

}
