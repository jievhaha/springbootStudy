package com.jievhaha.springbootweb.controller;

import com.jievhaha.springbootweb.dao.DepartmentDao;
import com.jievhaha.springbootweb.dao.EmployeeDao;
import com.jievhaha.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @GetMapping("/emps")
    public String getAllEmployees(ModelMap modelMap){
        modelMap.put("employees",employeeDao.getAll());
        return "/emp/list";
    }

    @GetMapping("/operating")
    public String toAddPage(ModelMap modelMap){
        modelMap.put("depts", departmentDao.getDepartments());
        return "/emp/add.html";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(Employee employee){
        System.out.println("添加的员工信息为:" + employee);

        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/operating/{id}")
    public String modifyPage(@PathVariable(value = "id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        model.addAttribute("depts", departmentDao.getDepartments());
        return "/emp/add.html";
    }

    @DeleteMapping("/operating/{id}")
    public String deleteEmployee(@PathVariable(value = "id")Integer id){
        System.out.println(id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
