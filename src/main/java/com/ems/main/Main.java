package com.ems.main;

import com.ems.exception.EmployeeNotFoundException;
import com.ems.model.Employee;
import com.ems.service.EmployeeService;
import com.ems.service.EmployeeServiceImplementation;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static EmployeeService service = new EmployeeServiceImplementation();

    public static void main(String[] args) {
        boolean running = true;

        while(running){
            System.out.println("\n --- EMPLOYEE MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View all Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> searchEmployee();
                case 4 -> updateEmployee();
                case 5 -> deleteEmployee();
                case 6 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addEmployee() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Department: ");
        String dept = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        service.addEmployee(new Employee(id, name, dept, salary, email));
    }

    static void viewEmployees() {
        List<Employee> employees = service.viewEmployees();
        if(employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        for(Employee emp : employees) {
            System.out.println(emp);
        }
    }

    static void searchEmployee() {
        System.out.println("Search by: ");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.print("Enter choice: ");

        String choice = scanner.nextLine();

        try{
            if(choice.equals("1")) {
                System.out.print("Enter ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                Employee emp = service.searchById(id);
                System.out.println(emp);

            }
            else if(choice.equals("2")) {
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                List<Employee> results = service.searchByName(name);
                for(Employee emp : results) {
                    System.out.println(emp);
                }
            }
            else {
                System.out.println("Invalid choice.");
            }
        }
        catch(EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid ID entered.");
        }
    }

    static void updateEmployee() {
        System.out.println("Enter ID to update: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Updated Name: ");
            String name = scanner.nextLine();

            System.out.print("Updated Department: ");
            String dept = scanner.nextLine();

            System.out.print("Updated Salary: ");
            String salaryInput = scanner.nextLine();
            double salary = salaryInput.isBlank() ? -1 : Double.parseDouble(salaryInput);

            System.out.print("Updated Email: ");
            String email = scanner.nextLine();

            service.updateEmployee(id, name, dept, salary, email);
        }
        catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid ID entered.");
        }
    }

    static void deleteEmployee() {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        try {
            service.deleteEmployee(id);
        }
        catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
