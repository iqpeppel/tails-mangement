package com.ecust.jwq.tails.service;

import com.ecust.jwq.tails.pojo.Dept;

import java.util.List;

public interface DeptService {
  List<Dept> list();
  void delete(Integer id);

  void insertDept(Dept dept);
}
