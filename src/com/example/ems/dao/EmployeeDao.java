package com.example.ems.dao;

import com.example.ems.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    Employee save(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(int id);

    void update(Employee employee);

    void deleteById(int id);

    List<Employee> findByDepartment(String department);

    List<Employee> findAllOrderBySalaryAsc();

    List<Employee> findAllOrderBySalaryDesc();

    Optional<Employee> findEmployeeWithMaxSalary();

    List<Employee> findManagers();

    boolean isEmployeeManager(int id);

    List<Employee> findByStatus(String status);

    List<Employee> findByRole(String role);
}