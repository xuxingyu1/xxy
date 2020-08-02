package cn.jiyun.mapper;

import cn.jiyun.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpMapper {

    List<Emp> findAll();

    public void addEmp(@Param(value = "emp")Emp emp);

    public void deleteById(@Param(value = "eid")Integer eid);
}
