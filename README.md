# Employee Management System

A console-based Employee Management System built with **Core Java** and **Maven**.  
This project was built step by step to learn Java fundamentals from scratch.

---

## 🛠️ Tech Stack

- Java 17
- Maven
- Core Java (OOP, Collections, Exception Handling, File I/O, Serialization)

---

## 📁 Project Structure

```
employee-management-system/
├── pom.xml
└── src/main/java/com/ems/
    ├── model/
    │   └── Employee.java         
    ├── exception/
    │   ├── EmployeeNotFoundException.java
    │   ├── DuplicateEmployeeException.java
    │   └── InvalidInputException.java
    ├── service/
    │   ├── EmployeeService.java      # Interface
    │   └── EmployeeServiceImpl.java  # Business logic
    ├── util/
    │   ├── FileUtil.java
    └── main/
        └── Main.java            
```

---

## ✨ Features

- Add a new employee
- View all employees
- Search employee by ID or Name
- Update employee details
- Delete employee
- Data persists between sessions using Java Serialization (`.ser` file)
- Custom exception handling (duplicate ID, not found, invalid input)
- Clean separation of concerns (model, service, util, exception, main)

---

## 🚀 How to Run

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/employee-management-system.git
cd employee-management-system
```

**2. Compile the project**
```bash
mvn compile
```

**3. Run the application**
```bash
mvn exec:java -Dexec.mainClass="com.ems.main.Main"
```

---


## 📌 Data Storage

Employee data is saved automatically to `employees.ser` in the project root folder after every add, update, or delete operation. Data is restored when the app starts again.
