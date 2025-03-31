package com.openclassrooms.webapp.controller;

import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.service.EmployeeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Data
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Employee> employees = service.getEmployees();
        model.addAttribute("employees", employees);
        return "home";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "createEmployee";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") final Long id, Model model) {
        Employee employee = service.getEmployee(id);
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") final Long id) {
       service.deleteEmployee(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {

        if(employee.getId() != null && employee.getId() > 0) {
            Employee current = service.getEmployee(employee.getId());
            employee.setPassword(current.getPassword());
        }
        service.saveEmployee(employee);
        return new ModelAndView("redirect:/");
    }

}
