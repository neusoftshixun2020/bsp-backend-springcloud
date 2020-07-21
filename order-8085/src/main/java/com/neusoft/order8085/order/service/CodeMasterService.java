package com.neusoft.order8085.order.service;

import com.neusoft.order8085.order.entity.CodeMaster;

import java.util.List;
import java.util.Map;

public interface CodeMasterService {

    int insert(CodeMaster codeMaster);

    int update(CodeMaster codeMaster);

    int delete(int pk);

    CodeMaster getById(int pk);

    List<CodeMaster> getAllByFilter(Map<String, Object> map);

    List<CodeMaster> getAll();

}

