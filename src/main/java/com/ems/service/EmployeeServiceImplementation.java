package com.ems.service;

import com.ems.model.Employee;
import java.util.List;
import java.util.ArrayList;

import com.ems.exception.EmployeeNotFoundException;
import com.ems.exception.DuplicateEmployeeException;
import com.ems.exception.InvalidInputException;
import com.ems.utils.FileUtil;

public class EmployeeServiceImplementation implements EmployeeService {

    private final List<Employee> employees;

    public EmployeeServiceImplementation() {
        List<Employee> employeesLoaded = FileUtil.loadEmployees();
        this.employees = (employeesLoaded != null) ? employeesLoaded : new ArrayList<>();
        System.out.println("[INFO] loaded " + employees.size() + " employee(s) from storage.");
    }

    // Add Employee
    @Override
    public void addEmployee(Employee employee) {
        if(employee == null) {
            throw new InvalidInputException("Employee can't be null");
        }

        if(employeeExists(employee.getId())) {
            throw new DuplicateEmployeeException(employee.getId());
        }

        employees.add(employee);
        FileUtil.saveEmployees(employees);
        System.out.println("[INFO] Employee added successfully!");
    }

    // View all Employees
    @Override
    public List<Employee> viewEmployees() {
        return new ArrayList<>(employees);
    }

    // Search Employee by ID
    @Override
    public Employee searchById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // Search Employee by Name
    @Override
    public List<Employee> searchByName(String name) {
        if(name == null || name.isBlank()) {
            throw new InvalidInputException("Search name can't be empty.");
        }

        List<Employee> results = new ArrayList<>();
        for(Employee e : employees) {
            if(e.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(e);
            }
        }

        if(results.isEmpty()) {
            throw new EmployeeNotFoundException("No employee found with name " + name);
        }
        return results;
    }

    // Update Employee
    @Override
    public void updateEmployee(int id, String name, String department, double salary, String email) {
        Employee emp = searchById(id);

        if(name != null && !name.isBlank()) {
            emp.setName(name.trim());
        }
        if(department != null && !department.isBlank()) {
            emp.setDepartment(department.trim());
        }
        if(salary > 0) {
            emp.setSalary(salary);
        }
        if(email != null && !email.isBlank()){
            emp.setEmail(email.trim());
        }

        FileUtil.saveEmployees(employees);
        System.out.println("[INFO] Employee updated successfully!");
    }

    // Delete Employee by ID
    @Override
    public void deleteEmployee(int id) {
        Employee emp = searchById(id);
        employees.remove(emp);
        FileUtil.saveEmployees(employees);
        System.out.println("[INFO] Employee deleted successfully!");
    }

    @Override
    public boolean employeeExists(int id) {
        return employees.stream()
                .anyMatch(e -> e.getId() == id);
    }

    @Override
    public int getTotalCount() {
        return employees.size();
    }
}