package com.example.ems.service;

import com.example.ems.dao.EmployeeDao;
import com.example.ems.exception.EmployeeNotFoundException;
import com.example.ems.exception.InvalidEmployeeDataException;
import com.example.ems.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee addEmployee(Employee employee) {

        validateEmployee(employee);

        return employeeDao.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeDao.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void updateEmployee(Employee employee) {

        if (employeeDao.findById(employee.getId()).isEmpty()) {
            throw new EmployeeNotFoundException(employee.getId());
        }

        validateEmployee(employee);

        employeeDao.update(employee);
    }

    public void deleteEmployee(int id) {

        if (employeeDao.findById(id).isEmpty()) {
            throw new EmployeeNotFoundException(id);
        }

        employeeDao.deleteById(id);
    }

    public List<Employee> findByDepartment(String department) {
        return employeeDao.findByDepartment(department);
    }

    public List<Employee> findAllOrderBySalaryAsc() {
        return employeeDao.findAllOrderBySalaryAsc();
    }

    public List<Employee> findAllOrderBySalaryDesc() {
        return employeeDao.findAllOrderBySalaryDesc();
    }

    public Optional<Employee> findEmployeeWithMaxSalary() {
        return employeeDao.findEmployeeWithMaxSalary();
    }

    public boolean isEmployeeManager(int id) {
        return employeeDao.isEmployeeManager(id);
    }

    // 🔥 BUSINESS VALIDATION
    private void validateEmployee(Employee employee) {

        if (employee.getFirstName() == null || employee.getFirstName().isBlank()) {
            throw new InvalidEmployeeDataException("First name cannot be empty.");
        }

        if (employee.getLastName() == null || employee.getLastName().isBlank()) {
            throw new InvalidEmployeeDataException("Last name cannot be empty.");
        }

        if (employee.getEmail() == null || !employee.getEmail().contains("@")) {
            throw new InvalidEmployeeDataException("Invalid email format.");
        }

        if (employee.getSalary() < 0) {
            throw new InvalidEmployeeDataException("Salary cannot be negative.");
        }

        if (employee.getJoiningDate().isAfter(LocalDate.now())) {
            throw new InvalidEmployeeDataException("Joining date cannot be in the future.");
        }
    }
}