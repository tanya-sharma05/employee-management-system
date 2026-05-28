package com.ems.service;

import com.ems.model.Employee;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import com.ems.exception.EmployeeNotFoundException;
import com.ems.utils.FileUtil;

public class EmployeeService {
    private List<Employee> employees = FileUtil.loadEmployees();

    // Add Employee
    public void addEmployee(Employee employee) {
        employees.add(employee);
        FileUtil.saveEmployees(employees);
        System.out.println("Employee added successfully!");
    }

    // View all Employees
    public void viewEmployees() {
        if(employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        for(Employee emp: employees) {
            System.out.println(emp);
        }
    }

    // Search Employee by ID
    public Employee searchById(int id) {
        for(Employee emp: employees) {
            if(emp.getId() == id) {
                return emp;
            }
        }
        throw new EmployeeNotFoundException(id);
    }

    // Update Employee
    public void updateEmployee(int id) {
        Employee emp = searchById(id);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter new Name (current: " + emp.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isBlank()) {
            emp.setName(name);
        }

        System.out.print("Enter new Department (current: " + emp.getDepartment() + "): ");
        String dept = scanner.nextLine();
        if (!dept.isBlank()) {
            emp.setDepartment(dept);
        }

        System.out.print("Enter new Salary (current: " + emp.getSalary() + "): ");
        String salaryInput = scanner.nextLine();
        if (!salaryInput.isBlank()) {
            emp.setSalary(Double.parseDouble(salaryInput));
        }

        System.out.print("Enter new Email (current: " + emp.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isBlank()) {
            emp.setEmail(email);
        }

        FileUtil.saveEmployees(employees);
        System.out.println("Employee updated successfully!");
    }

    // Delete Employee by ID
    public void deleteEmployee(int id) {
        Employee emp = searchById(id);
        employees.remove(emp);
        FileUtil.saveEmployees(employees);
        System.out.println("Employee deleted successfully!");
    }
};