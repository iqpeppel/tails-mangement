package com.ecust.jwq.tails.mapper;

import com.ecust.jwq.tails.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapepr {
    /*
    查询记录数
    */
//    @Select("select COUNT(*) from emp ")
//    public  Long count();
//
//    @Select("select *  from  emp limit  #{start},#{pageSize}")
//    public List<Emp>page(Integer start, Integer pageSize);
    // @Select("select *  from  emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void delet(List<Integer> ids);

    /*
      插入员工数据
    */
    @Insert(" insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            " values (#{username}, #{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime});")
    void insert(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getByid(Integer id);

    void update(Emp emp);

    /*  根据用户名和密码查询用户*/
    @Select("select * from  emp where username = #{username} and password = #{password}")
    Emp getByUsernameandPassword(Emp emp);
//根据部门id删除员工
    @Delete("delete  from emp where dept_id = #{deptid}")
    void deleteByDeptid(Integer deptid);
}