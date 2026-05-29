package com.ems.service;

import com.ems.model.Employee;
import com.ems.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImplementation implements EmployeeDAO {

    private final Connection connection;

    public EmployeeDAOImplementation() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (id, name, department, salary, email) VALUES (?,?,?,?,?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getDepartment());
            statement.setDouble(4, employee.getSalary());
            statement.setString(5, employee.getEmail());

            statement.executeUpdate();
            System.out.println("[INFO] Employee inserted into db successfully!");
        }
        catch(SQLException e) {
            System.out.println("[ERROR] Failed to add employee! " + e.getMessage());
        }
    }

    @Override
    public List<Employee> viewEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)) {

            while(rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"),
                        rs.getString("email")
                );
                employees.add(emp);
            }
        }
        catch(SQLException e) {
            System.out.println("[ERROR] Failed to fetch employees! " + e.getMessage());
        }

        return employees;
    }

    @Override
    public Employee searchById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"),
                        rs.getString("email")
                );
            }
        }
        catch(SQLException e) {
            System.out.println("[ERROR] Failed to search employee! " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Employee> searchByName(String name) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE name LIKE ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"),
                        rs.getString("email")
                ));
            }
        }
        catch(SQLException e) {
            System.out.println("[ERROR] Failed to search by name! " + e.getMessage());
        }

        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, department = ?, salary = ?, email = ? WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getDepartment());
            statement.setDouble(3, employee.getSalary());
            statement.setString(4, employee.getEmail());
            statement.setInt(5, employee.getId());

            statement.executeUpdate();
            System.out.println("[INFO] Employee updated in database!");

        }
        catch(SQLException e) {
            System.out.println("[ERROR] Failed to update employee! " + e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("[INFO] Employee deleted from database!");

        }
        catch(SQLException e) {
            System.out.println("[ERROR] Failed to delete employee! " + e.getMessage());
        }
    }

    @Override
    public boolean employeeExists(int id) {
        String sql = "SELECT COUNT(*) FROM employees WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        catch(SQLException e) {
            System.out.println("[ERROR] Failed to check employee! " + e.getMessage());
        }

        return false;
    }
}
