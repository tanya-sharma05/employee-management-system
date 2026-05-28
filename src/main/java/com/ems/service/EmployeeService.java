package com.ems.service;

import com.ems.model.Employee;
import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);

    List<Employee> viewEmployees();

    Employee searchById(int id);

    List<Employee> searchByName(String name);

    void updateEmployee(int id, String name, String department, double salary, String email);

    void deleteEmployee(int id);

    boolean employeeExists(int id);

    int getTotalCount();
};