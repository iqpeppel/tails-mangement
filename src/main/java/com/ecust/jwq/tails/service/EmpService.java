package com.ecust.jwq.tails.service;

import com.ecust.jwq.tails.pojo.Emp;
import com.ecust.jwq.tails.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void insert(Emp emp);

    Emp getByid(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
