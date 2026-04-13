Here’s a **clean, professional README.md** tailored for your **advanced version** of the Employee Management System 👇

---

# 👨‍💼 Employee Management System (Advanced Java - MVC + DAO + JDBC)

A **console-based Employee Management System** built using **Core Java**, following **MVC architecture**, **DAO pattern**, and **JDBC integration** with MySQL.

This project demonstrates **real-world backend design principles**, clean code practices, and scalable architecture.

---

## 🚀 Features

### ✅ Core Features

* ➕ Add Employee
* 📋 View All Employees
* 🔍 Search Employee by ID
* ✏️ Update Employee
* 🗑️ Delete Employee

### 🚀 Advanced Features

* 🔎 Search employees by **department**
* 📊 Sort employees by **salary (ASC / DESC)**
* 🏆 Find employee with **maximum salary**
* 👔 Check if employee is **manager**
* 🧠 Use of **Enums** instead of raw strings
* ⚠️ Custom **Exception Handling**
* 🗂️ Clean **Layered Architecture (MVC + DAO)**

---

## 🧱 Project Architecture

### 🔹 MVC Pattern

* **Model** → Employee, Enums
* **View** → ConsoleView (UI interaction)
* **Controller** → Handles user actions

### 🔹 DAO Pattern

* **EmployeeDao** → Interface (contract)
* **EmployeeJdbcDao** → MySQL implementation

### 🔹 Layered Design

```text
View → Controller → DAO → Database
```

---

## 📁 Project Structure

```text
src/
└── com/example/ems/
    ├── Main.java
    ├── controller/
    │   └── EmployeeController.java
    ├── dao/
    │   ├── EmployeeDao.java
    │   └── EmployeeJdbcDao.java
    ├── model/
    │   ├── Employee.java
    │   ├── EmployeeStatus.java
    │   ├── Gender.java
    │   └── EmploymentType.java
    ├── exception/
    │   ├── EmployeeNotFoundException.java
    │   ├── InvalidEmployeeDataException.java
    │   └── DatabaseOperationException.java
    ├── util/
    │   ├── DBConnection.java
    │   └── AppMessages.java
    └── view/
        └── ConsoleView.java
```

---

## 🧠 Concepts Implemented

* Object-Oriented Programming (OOP)
* Encapsulation & Abstraction
* MVC Architecture
* DAO Design Pattern
* JDBC (Java Database Connectivity)
* MySQL Integration
* Enums (Type Safety)
* Exception Handling (Custom Exceptions)
* Clean Code Practices
* Separation of Concerns

---

## 🗄️ Database Setup

### Create Database

```sql
CREATE DATABASE ems_v1;
USE ems_v1;
```

### Create Table

```sql
CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(150),
    phone_number VARCHAR(20),
    department VARCHAR(100),
    role VARCHAR(100),
    salary DOUBLE,
    joining_date DATE,
    status VARCHAR(30),
    gender VARCHAR(20),
    employment_type VARCHAR(30),
    is_manager BOOLEAN
);
```

---

## 🔌 JDBC Configuration

Update your `DBConnection.java`:

```java
private static final String URL =
    "jdbc:mysql://localhost:3306/ems_v1?useSSL=false&serverTimezone=UTC";

private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

## ▶️ How to Run

### 1. Compile

```bash
javac com/example/ems/Main.java
```

### 2. Run

```bash
java com.example.ems.Main
```

---

## ⚠️ Important Notes

* MySQL must be running
* JDBC Driver (`mysql-connector-j.jar`) must be added
* Database name must match your connection URL
* Package name (`util` vs `utill`) must match folder

---

## 🔥 What’s New in This Version

### ✅ Advanced Employee Model

* firstName, lastName
* email, phoneNumber
* role, department
* joiningDate
* status, gender, employmentType
* isManager flag

---

### ✅ Enums Introduced

* EmployeeStatus
* Gender
* EmploymentType

👉 Avoids string errors and improves code safety

---

### ✅ Custom Exception Handling

* `EmployeeNotFoundException`
* `InvalidEmployeeDataException`
* `DatabaseOperationException`

👉 Cleaner error handling and debugging

---

### ✅ Advanced JDBC Queries

* Sort by salary
* Max salary employee
* Find by department
* Manager check
* Reusable query methods

---

### ✅ Clean Code Improvements

* Reusable `mapRow()` method
* `PreparedStatement` usage
* `try-with-resources`
* Reduced duplication

---

## 🚀 Future Improvements

* 🔧 Add Service Layer (Business Logic Separation)
* 🧪 Add Unit Testing (JUnit)
* 📦 Convert to Maven
* 🌐 Build REST API (Spring Boot)
* 🔐 Add Authentication System
* 📊 Add Reporting / Analytics

---

## 🎯 Key Learning

> “Design your system so that you can change the data source without changing the entire code.”

---

## 👨‍💻 Author

Built as an advanced learning project to understand:

* real-world Java architecture
* scalable backend design
* database integration

---

## ⭐ Final Thought

This project is now at **intermediate → advanced backend level**
Next step is to evolve into:

👉 **Service Layer + Spring Boot API**

<img width="1243" height="409" alt="image" src="https://github.com/user-attachments/assets/ada052ed-45db-4408-9fe5-336828a6427b" />

