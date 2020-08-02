package cn.jiyun.controller;

import cn.jiyun.pojo.Dept;
import cn.jiyun.pojo.Emp;
import cn.jiyun.service.DeptService;
import cn.jiyun.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("emp")
public class EmpController {


    @Value("${file.upload.path}")
    private  String filePath;

    @Autowired
    private EmpService es;

    @Autowired
    private DeptService ds;

    @GetMapping("findAll")
    public String findAll(Model model){
        List<Emp> emps = es.findAll();
        model.addAttribute("emps",emps);
       return "empList";
    }

    @GetMapping("toAddEmp")
    public String toAddEmp(Model model){
        List<Dept> depts = ds.findAll();
        model.addAttribute("depts",depts);
        return "addEmp";
    }

    @PostMapping("addEmp")
    public String addEmp(@ModelAttribute(value = "emp")Emp emp, @RequestParam(value = "file")MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        File file1 = new File(filePath,filename);
        //判断当前系统是否存在上传路径，如果不存在则新建
        if (!file1.getParentFile().exists()){
            file1.getParentFile().mkdirs();
        }
        file.transferTo(new File(filePath+File.separator+filename));
        emp.setPic("/photo/"+filename);
        es.addEmp(emp);
        return "redirect:/emp/findAll";
    }

    @GetMapping("deleteById")
    public String deleteById(Integer eid){
        es.deleteById(eid);
        return "redirect:/emp/findAll";
    }

}
