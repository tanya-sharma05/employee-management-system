package com.ems.utils;

import com.ems.model.Employee;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final String FILE_PATH = "employees.ser"; // Serialized file

    public static void saveEmployees(List<Employee> employees) {
        // try with resources
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(employees);
        }
        catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Employee> loadEmployees() {
        File file = new File(FILE_PATH);

        if(!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Employee>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}