package com.ems.service;

import com.ems.model.Employee;
import java.util.List;

public interface EmployeeDAO {

    void addEmployee(Employee employee);

    List<Employee> viewEmployees();

    Employee searchById(int id);

    List<Employee> searchByName(String name);

    void updateEmployee(Employee employee);

    void deleteEmployee(int id);

    boolean employeeExists(int id);
}