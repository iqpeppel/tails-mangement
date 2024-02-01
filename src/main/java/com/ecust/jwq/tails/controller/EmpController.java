package com.ecust.jwq.tails.controller;

import com.ecust.jwq.tails.anno.Log;
import com.ecust.jwq.tails.mapper.EmpMapepr;
import com.ecust.jwq.tails.pojo.Emp;
import com.ecust.jwq.tails.pojo.PageBean;
import com.ecust.jwq.tails.pojo.Result;
import com.ecust.jwq.tails.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")
            Integer pageSize , String name , Short gender , @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("查询输出为：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean  pageBean  =  empService.page(page ,pageSize ,name ,gender,begin,end);
        return  Result.success(pageBean);
    }
    @DeleteMapping("/{ids}")
    @Log
    public Result delete( @PathVariable List<Integer>ids){
        log.info("批量删除 ids：{}",ids);
        empService.delete(ids);
        return  Result.success();

    }
    /*
    插入员工
    */
    @PostMapping
    @Log
    public  Result save(@RequestBody Emp emp ){
        empService.insert(emp);
        log.info("插入员工数据为：{}",emp);
        return  Result.success();
    }
    //查询员工
    @GetMapping("/{id}")
    public  Result getByid( @PathVariable Integer id){
        log.info("查询员工，id：{}" , id);
        Emp emp =  empService.getByid(id);
       return  Result.success(emp);
    }
    //更新员工
    @PutMapping(" ")
    @Log
    public  Result update(@RequestBody  Emp  emp){
        log.info("更新员工，id：{}",emp);
         empService.update(emp);
    return     Result.success();
    }
}
