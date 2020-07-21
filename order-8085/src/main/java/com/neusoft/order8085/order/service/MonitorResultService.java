package com.neusoft.order8085.order.service;



import com.neusoft.order8085.order.entity.MonitorResult;

import java.util.List;
import java.util.Map;

public interface MonitorResultService {

    int insert(MonitorResult monitorResult);

    int update(MonitorResult monitorResult);

    int delete(int pk);

    MonitorResult getById(int pk);

    List<MonitorResult> getAllByFilter(Map<String, Object> map);

    List<MonitorResult> getAll();

}
