package com.ecust.jwq.tails.service.impl;

import com.ecust.jwq.tails.mapper.DeptLogMapper;
import com.ecust.jwq.tails.pojo.DeptLog;
import com.ecust.jwq.tails.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Component
public class DeptLogServicelmpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void InsertLog(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
