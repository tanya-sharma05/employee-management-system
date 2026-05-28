package com.ems.main;

import com.ems.exception.EmployeeNotFoundException;
import com.ems.model.Employee;
import com.ems.service.EmployeeService;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static EmployeeService service = new EmployeeService();

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
                case 2 -> service.viewEmployees();
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

    static void searchEmployee() {
        System.out.print("Enter ID to search: ");
        int id = Integer.parseInt(scanner.nextLine());

        try {
            Employee emp = service.searchById(id);
            System.out.println("Found: " + emp);
        }
        catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void updateEmployee() {
        System.out.println("Enter ID to update: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());
            service.updateEmployee(id);
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
