package com.ecust.jwq.tails.mapper;

import com.ecust.jwq.tails.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    //查询所有部门数据
    @Select("select id, name, create_time, update_time from dept")
    List<Dept> list();
    /*
    删除数据
    */
    @Delete(" delete  from  dept  where id=#{id}")
    void  delete(Integer id);
    /*
   添加数据
   */
    @Insert("insert into  dept( name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);


}
