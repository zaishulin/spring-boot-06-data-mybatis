package com.atguigu.springboot.controller;


import com.atguigu.springboot.bean.Department;
import com.atguigu.springboot.bean.Employee;
import com.atguigu.springboot.mapper.DepartmentMapper;
import com.atguigu.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;


    @Cacheable(cacheNames="dept",key="#id")
    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }

//    @CachePut(cacheNames="dept")
//    @GetMapping("/dept")
//    public Department insertDept(Department department){
//        departmentMapper.insertDept(department);
//        return department;
//    }

    @CachePut(cacheNames="dept",key="#department.id")
    @GetMapping("/dept")
    public Department updateDept(Department department){
        departmentMapper.updateDept(department);
        return department;
    }

    @CacheEvict(cacheNames="dept",key="#id")
    @GetMapping("/deldept/{id}")
    public void deleteDept(@PathVariable("id") Integer id){
        System.out.println("deleteDept:"+id);
    }

    @Cacheable(cacheNames="emp",key="#id",condition = "#id>1",unless = "#a0 == 2")
    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
       return employeeMapper.getEmpById(id);
    }


}
