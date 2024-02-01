package com.ecust.jwq.tails.service.impl;

import com.ecust.jwq.tails.mapper.DeptLogMapper;
import com.ecust.jwq.tails.mapper.DeptMapper;
import com.ecust.jwq.tails.mapper.EmpMapepr;
import com.ecust.jwq.tails.pojo.Dept;
import com.ecust.jwq.tails.pojo.DeptLog;
import com.ecust.jwq.tails.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//部门实现类
@Slf4j
@Service
public class DeptServicelmpl implements DeptService {
    @Autowired
   private  DeptMapper  deptMapper;
    @Autowired
    private EmpMapepr empMapepr;
    @Autowired
    private  DeptLogServicelmpl deptLogServicelmpl;
    @Override
    public List<Dept> list() {
        List<Dept> deptList =  deptMapper.list();
        return  deptList;
    }
    @Transactional(rollbackFor = Exception.class)//交由事务管理,回滚所有异常
    @Override
    public  void  delete( Integer id){

        try {
            deptMapper.delete(id);
            empMapepr.deleteByDeptid(id);//删除部门下id的员工

        }finally {
            DeptLog  deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("解散了部门的操作，解散的是"+id+"号部门");
            deptLogServicelmpl.InsertLog(deptLog);
        }


    }
    @Override
    public  void  insertDept( Dept dept){
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }
}
