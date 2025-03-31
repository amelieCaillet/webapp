package com.openclassrooms.webapp.service;

import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.repository.EmployeeProxy;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class EmployeeService {

    private final EmployeeProxy employeeProxy;

    public EmployeeService(EmployeeProxy employeeProxy) {
        this.employeeProxy = employeeProxy;
    }

    public Employee getEmployee(final Long id) {
        return employeeProxy.getEmployee(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeProxy.getEmployees();
    }

    public void deleteEmployee(final Long id) {
        employeeProxy.deleteEmployee(id);
    }

    public Employee saveEmployee(final Employee employee) {
        Employee savedEmployee;

        //Règle métier : Nom en majuscule
        employee.setLastName(employee.getLastName().toUpperCase());

        //si l'employé n'existe pas on le créé sinon on le met à jour et on sauvegarde
        if (employee.getId() == null){
            savedEmployee = employeeProxy.createEmployee(employee);
        }else {
            savedEmployee = employeeProxy.updateEmployee(employee);
        }
        return savedEmployee;
    }
}
