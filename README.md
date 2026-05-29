# Employee Management System

A console-based Employee Management System built with **Core Java** and **Maven**.  
This project was built step by step to learn Java fundamentals from scratch.

---

## 🛠️ Tech Stack

- Java 17
- Maven
- Core Java (OOP, Collections, Exception Handling, File I/O, Serialization)
- MySQL (JDBC) — added in v2.0

---

## 📁 Project Structure

```
employee-management-system/
├── pom.xml
└── src/main/java/com/ems/
    ├── model/
    │   └── Employee.java                       # Employee entity
    ├── exception/
    │   ├── EmployeeNotFoundException.java
    │   ├── DuplicateEmployeeException.java
    │   └── InvalidInputException.java
    ├── service/
    │   ├── EmployeeDAO.java                    # Interface — DB operations
    │   ├── EmployeeDAOImplementation.java      # SQL queries (INSERT, SELECT, UPDATE, DELETE)
    │   ├── EmployeeService.java                # Interface — business operations
    │   └── EmployeeServiceImplementation.java  # Business logic, talks to DAO
    ├── utils/
    │   └── DatabaseConnection.java             # MySQL connection setup
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
- Data persists in **MySQL database** (upgraded from file-based `.ser` storage)
- Custom exception handling (duplicate ID, not found, invalid input)
- Clean separation of concerns using DAO pattern (model, service, DAO, util, exception, main)

---

## 🚀 How to Run

### Prerequisites
- Java 17+ → https://adoptium.net
- Maven → https://maven.apache.org
- MySQL → https://dev.mysql.com/downloads/

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/tanya-sharma05/employee-management-system.git
cd employee-management-system
```

**2. Set up the database**

Open MySQL and run:
```sql
CREATE DATABASE IF NOT EXISTS ems_db;
USE ems_db;

CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DOUBLE NOT NULL,
    email VARCHAR(100) NOT NULL
);
```

**3. Update your MySQL credentials**

Open `src/main/java/com/ems/utils/DatabaseConnection.java` and update:
```java
private static final String URL = "jdbc:mysql://localhost:3306/ems_db";
private static final String USERNAME = "root";
private static final String PASSWORD = "your_mysql_password";
```

**4. Compile the project**
```bash
mvn compile
```

**5. Run the application**
```bash
mvn exec:java -Dexec.mainClass="com.ems.main.Main"
```

---

## 📌 Data Storage

### v1.0 — File Storage
Employee data was saved to `employees.ser` using Java Serialization. Data was loaded back on app startup.

### v2.0 — MySQL Database (current)
File storage has been replaced with a real **MySQL database** using **JDBC**.  
Every add, update, and delete operation is instantly reflected in the `employees` table in `ems_db`.  
No data is lost between sessions — MySQL handles all persistence.

