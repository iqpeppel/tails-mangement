package com.ecust.jwq.tails.mapper;

import com.ecust.jwq.tails.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {
    /*
 添加数据
 */
    @Insert("insert into  deptlog(  CreateTime, Description) values(#{CreateTime},#{Description})")
    void insert(DeptLog deptLog);
}
