package com.ecust.jwq.tails.service.impl;

import com.ecust.jwq.tails.mapper.EmpMapepr;
import com.ecust.jwq.tails.pojo.Emp;
import com.ecust.jwq.tails.pojo.PageBean;
import com.ecust.jwq.tails.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServicelmpl implements EmpService {
@Autowired
    private  EmpMapepr empMapepr;
        //封装pagebean对象
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end){
        PageHelper.startPage(page,pageSize);
        List<Emp> empList = empMapepr.list(name,gender,begin,end);
        Page<Emp> p = ( Page<Emp>) empList;

        PageBean pageBean  = new PageBean(p.getTotal(),p.getResult());
        return  pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapepr.delet(ids);
    }

    @Override

    public void insert(Emp emp) {
        emp.setUpdateTime( LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());
        empMapepr.insert(emp);
    }

    @Override
    public Emp getByid(Integer id) {

        return  empMapepr.getByid( id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapepr.update(emp);
    }

    @Override
    public Emp login(Emp emp) {

        return empMapepr.getByUsernameandPassword(emp);
    }
}
