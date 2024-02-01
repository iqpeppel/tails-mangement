package com.ecust.jwq.tails.controller;

import ch.qos.logback.classic.Logger;
import com.ecust.jwq.tails.pojo.Dept;
import com.ecust.jwq.tails.pojo.Result;
import com.ecust.jwq.tails.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @GetMapping
    public Result List(){
        log.info("查询所有数据：");
        List<Dept> deptList  = deptService.list();
        return  Result.success(deptList);
    }
    /*
    删除
    */
    @DeleteMapping("/{id}")
    public   Result  deleteDept(@PathVariable Integer id){
        log.info("根据id删除:{}",id);
        deptService.delete(id);
        return  Result.success();
    }
    /*
    添加数据
    */
    @PostMapping
    public  Result insertDept(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
        deptService.insertDept(dept);
        return  Result.success();

    }
}
