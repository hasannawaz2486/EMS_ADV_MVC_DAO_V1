//package com.example.ems.controller;
//
//import com.example.ems.dao.EmployeeDao;
//import com.example.ems.model.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//public class EmployeeController {
//
//    private final EmployeeDao employeeDao;
//
//    public EmployeeController(EmployeeDao employeeDao) {
//        this.employeeDao = employeeDao;
//    }
//
//    public boolean addEmployee(String firstName, String lastName, String email, String phone,
//                               String department, String role, double salary, LocalDate joiningDate,
//                               EmployeeStatus status, Gender gender,
//                               EmploymentType employmentType, boolean isManager) {
//
//        if (firstName == null || firstName.trim().isEmpty()) return false;
//        if (lastName == null || lastName.trim().isEmpty()) return false;
//        if (email == null || email.trim().isEmpty()) return false;
//        if (department == null || department.trim().isEmpty()) return false;
//        if (role == null || role.trim().isEmpty()) return false;
//        if (salary < 0) return false;
//
//
//
//        Employee employee = new Employee(
//                0, // DB will generate ID
//                firstName.trim(),
//                lastName.trim(),
//                email.trim(),
//                phone,
//                department.trim(),
//                role.trim(),
//                salary,
//                joiningDate,
//                status,
//                gender,
//                employmentType,
//                isManager
//        );
//
//        return employeeDao.save(employee) != null;
//    }
//
//    public List<Employee> getAllEmployees() {
//        return employeeDao.findAll();
//    }
//
//    public Employee searchEmployeeById(int id) {
//        return employeeDao.findById(id).orElse(null);
//    }
//
//    public boolean updateEmployee(int id, String firstName, String lastName, String email, String phone,
//                                  String department, String role, double salary, LocalDate joiningDate,
//                                  EmployeeStatus status, Gender gender,
//                                  EmploymentType employmentType, boolean isManager) {
//
//        Optional<Employee> optional = employeeDao.findById(id);
//        if (optional.isEmpty()) return false;
//
//        Employee employee = optional.get();
//
//        employee.setFirstName(firstName);
//        employee.setLastName(lastName);
//        employee.setEmail(email);
//        employee.setPhoneNumber(phone);
//        employee.setDepartment(department);
//        employee.setRole(role);
//        employee.setSalary(salary);
//        employee.setJoiningDate(joiningDate);
//        employee.setStatus(status);
//        employee.setGender(gender);
//        employee.setEmploymentType(employmentType);
//        employee.setManager(isManager);
//
//        employeeDao.update(employee);
//        return true;
//    }
//
//    public boolean deleteEmployee(int id) {
//        employeeDao.deleteById(id);
//        return true;
//    }
//
//    public List<Employee> findByDepartment(String department) {
//        return employeeDao.findByDepartment(department);
//    }
//
//    public List<Employee> findAllOrderBySalaryAsc() {
//        return employeeDao.findAllOrderBySalaryAsc();
//    }
//
//    public List<Employee> findAllOrderBySalaryDesc() {
//        return employeeDao.findAllOrderBySalaryDesc();
//    }
//
//    public Optional<Employee> findEmployeeWithMaxSalary() {
//        return employeeDao.findEmployeeWithMaxSalary();
//    }
//
//    public boolean isEmployeeManager(int id) {
//        return employeeDao.isEmployeeManager(id);
//    }
//}


//adding employee service
package com.example.ems.controller;

import com.example.ems.model.*;
import com.example.ems.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    public boolean addEmployee(String firstName, String lastName, String email, String phone,
                               String department, String role, double salary, LocalDate joiningDate,
                               EmployeeStatus status, Gender gender,
                               EmploymentType employmentType, boolean isManager) {

        try {
            Employee employee = new Employee(
                    0,
                    firstName,
                    lastName,
                    email,
                    phone,
                    department,
                    role,
                    salary,
                    joiningDate,
                    status,
                    gender,
                    employmentType,
                    isManager
            );

            service.addEmployee(employee);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    public Employee searchEmployeeById(int id) {
        try {
            return service.getEmployeeById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateEmployee(int id, String firstName, String lastName, String email, String phone,
                                  String department, String role, double salary, LocalDate joiningDate,
                                  EmployeeStatus status, Gender gender,
                                  EmploymentType employmentType, boolean isManager) {

        try {
            Employee employee = new Employee(
                    id,
                    firstName,
                    lastName,
                    email,
                    phone,
                    department,
                    role,
                    salary,
                    joiningDate,
                    status,
                    gender,
                    employmentType,
                    isManager
            );

            service.updateEmployee(employee);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteEmployee(int id) {
        try {
            service.deleteEmployee(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Employee> findByDepartment(String department) {
        return service.findByDepartment(department);
    }

    public List<Employee> findAllOrderBySalaryAsc() {
        return service.findAllOrderBySalaryAsc();
    }

    public List<Employee> findAllOrderBySalaryDesc() {
        return service.findAllOrderBySalaryDesc();
    }

    public Optional<Employee> findEmployeeWithMaxSalary() {
        return service.findEmployeeWithMaxSalary();
    }

    public boolean isEmployeeManager(int id) {
        return service.isEmployeeManager(id);
    }
}