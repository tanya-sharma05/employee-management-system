package com.ems.service;

import com.ems.model.Employee;
import java.util.List;

import com.ems.exception.EmployeeNotFoundException;
import com.ems.exception.DuplicateEmployeeException;
import com.ems.exception.InvalidInputException;

public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImplementation() {
        this.employeeDAO = new EmployeeDAOImplementation();
    }

    @Override
    public void addEmployee(Employee employee) {
        if(employee == null) {
            throw new InvalidInputException("Employee can't be null!");
        }
        if(employeeDAO.employeeExists(employee.getId())) {
            throw new DuplicateEmployeeException(employee.getId());
        }
        employeeDAO.addEmployee(employee);
    }

    @Override
    public List<Employee> viewEmployees() {
        return employeeDAO.viewEmployees();
    }

    @Override
    public Employee searchById(int id) {
        Employee emp = employeeDAO.searchById(id);
        if(emp == null) {
            throw new EmployeeNotFoundException(id);
        }
        return emp;
    }

    @Override
    public List<Employee> searchByName(String name) {
        if(name == null || name.isBlank()) {
            throw new InvalidInputException("Search name can't be empty!");
        }
        List<Employee> results = employeeDAO.searchByName(name);
        if(results.isEmpty()) {
            throw new EmployeeNotFoundException("No employee found with name: " + name);
        }
        return results;
    }

    @Override
    public void updateEmployee(int id, String name, String department, double salary, String email) {
        Employee employee = searchById(id);

        if(name != null && !name.isBlank()) {
            employee.setName(name.trim());
        }
        if(department != null && !department.isBlank()) {
            employee.setDepartment(department.trim());
        }
        if(salary > 0) {
            employee.setSalary(salary);
        }
        if(email != null && !email.isBlank()){
            employee.setEmail(email.trim());
        }

        employeeDAO.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        if(!employeeDAO.employeeExists(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeDAO.deleteEmployee(id);
    }

    @Override
    public boolean employeeExists(int id) {
        return employeeDAO.employeeExists(id);
    }

    @Override
    public int getTotalCount() {
        return employeeDAO.viewEmployees().size();
    }
}