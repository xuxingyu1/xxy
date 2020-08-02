package cn.jiyun.service;

import cn.jiyun.mapper.EmpMapper;
import cn.jiyun.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpService {

    @Autowired
    private EmpMapper em;

    public List<Emp> findAll(){
        return  em.findAll();
    }

    public void addEmp(Emp emp){
        em.addEmp(emp);
    }

    public void deleteById(Integer eid){
        em.deleteById(eid);
    }
}
