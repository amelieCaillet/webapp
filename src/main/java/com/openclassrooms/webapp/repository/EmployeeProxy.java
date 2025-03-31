package com.openclassrooms.webapp.repository;


import com.openclassrooms.webapp.CustomProperties;
import com.openclassrooms.webapp.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Slf4j
@Repository
public class EmployeeProxy {

    @Autowired
    private CustomProperties properties;

    /**
     * Récupère tous les employés
     *
     * @return un employé
     */
    public Iterable<Employee> getEmployees() {
        String baseApiUrl = properties.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                    getEmployeesUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Iterable<Employee>>() {
                    }
            );
            log.debug("Get Employees call : " + response.getStatusCode().toString());
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (RestClientException e) {
            log.error("Error retrieving employees: ", e);
            return Collections.emptyList();
        }
    }

    /**
     * Récupère un employé
     * @param id
     * @return l'employé demandé (id)
     */
    public Employee getEmployee(Long id) {
        String baseApiUrl = properties.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Employee.class);

        log.debug("Get Employee call : " + response.getStatusCode().toString());
        return response.getBody();
    }

    /**
     * Créer un employé
     *
     * @param employee
     * @return un nouvel employé avec un id
     */
    public Employee createEmployee(Employee employee) {
        String baseApiUrl = properties.getApiUrl();
        String createEmployeeUrl = baseApiUrl + "/employee";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
        ResponseEntity<Employee> response = restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Employee.class
        );

        log.debug("Create Employee call : " + response.getStatusCode().toString());
        return response.getBody();
    }

    /**
     * Mettre à jour un employé
     * @param employee
     * @return l'employé mis à jour
     */
    public Employee updateEmployee(Employee employee) {
        String baseApiUrl = properties.getApiUrl();
        String updateEmployeeUrl = baseApiUrl + "/employee/" + employee.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
        ResponseEntity<Employee> response = restTemplate.exchange(
                updateEmployeeUrl,
                HttpMethod.PUT,
                request,
                Employee.class
        );

        log.debug("Update Employee call : " + response.getStatusCode().toString());
        return response.getBody();
    }

    /**
     * Supprimer un employé
     *
     * @param id
     */
    public void deleteEmployee(Long id) {
        String baseApiUrl = properties.getApiUrl();
        String deleteEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Void> response = restTemplate.exchange(
                deleteEmployeeUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        log.debug("Delete Employee call : " + response.getStatusCode().toString());
    }
}
