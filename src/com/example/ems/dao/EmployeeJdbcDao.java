package com.example.ems.dao;



import com.example.ems.model.Employee;
import com.example.ems.utill.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.ems.exception.DatabaseOperationException;
import com.example.ems.model.Employee;
import com.example.ems.model.EmployeeStatus;
import com.example.ems.model.EmploymentType;
import com.example.ems.model.Gender;
import com.example.ems.utill.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeJdbcDao implements EmployeeDao {

    @Override
    public Employee save(Employee employee) {
        String sql = "INSERT INTO employees " +
                "(first_name, last_name, email, phone_number, department, role, salary, joining_date, status, gender, employment_type, is_manager) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getEmail());
            stmt.setString(4, employee.getPhoneNumber());
            stmt.setString(5, employee.getDepartment());
            stmt.setString(6, employee.getRole());
            stmt.setDouble(7, employee.getSalary());
            stmt.setDate(8, Date.valueOf(employee.getJoiningDate()));
            stmt.setString(9, employee.getStatus().name());
            stmt.setString(10, employee.getGender().name());
            stmt.setString(11, employee.getEmploymentType().name());
            stmt.setBoolean(12, employee.isManager());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    employee.setId(rs.getInt(1));
                }
            }

            return employee;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to save employee to database.", e);
        }
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return executeQueryList(sql);
    }

    @Override
    public Optional<Employee> findById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find employee by id: " + id, e);
        }
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employees SET first_name=?, last_name=?, email=?, phone_number=?, department=?, role=?, salary=?, joining_date=?, status=?, gender=?, employment_type=?, is_manager=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getEmail());
            stmt.setString(4, employee.getPhoneNumber());
            stmt.setString(5, employee.getDepartment());
            stmt.setString(6, employee.getRole());
            stmt.setDouble(7, employee.getSalary());
            stmt.setDate(8, Date.valueOf(employee.getJoiningDate()));
            stmt.setString(9, employee.getStatus().name());
            stmt.setString(10, employee.getGender().name());
            stmt.setString(11, employee.getEmploymentType().name());
            stmt.setBoolean(12, employee.isManager());
            stmt.setInt(13, employee.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseOperationException(
                        "No employee record was updated for id: " + employee.getId(),
                        null
                );
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update employee with id: " + employee.getId(), e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM employees WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseOperationException("No employee record was deleted for id: " + id, null);
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete employee with id: " + id, e);
        }
    }

    @Override
    public List<Employee> findByDepartment(String department) {
        String sql = "SELECT * FROM employees WHERE department = ?";
        return executeQueryList(sql, department);
    }

    @Override
    public List<Employee> findAllOrderBySalaryAsc() {
        String sql = "SELECT * FROM employees ORDER BY salary ASC";
        return executeQueryList(sql);
    }

    @Override
    public List<Employee> findAllOrderBySalaryDesc() {
        String sql = "SELECT * FROM employees ORDER BY salary DESC";
        return executeQueryList(sql);
    }

    @Override
    public Optional<Employee> findEmployeeWithMaxSalary() {
        String sql = "SELECT * FROM employees ORDER BY salary DESC LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to fetch employee with maximum salary.", e);
        }
    }

    @Override
    public List<Employee> findManagers() {
        return List.of();
    }

    @Override
    public boolean isEmployeeManager(int id) {
        String sql = "SELECT is_manager FROM employees WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("is_manager");
                }
            }

            return false;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to check manager status for employee id: " + id, e);
        }
    }

    @Override
    public List<Employee> findByStatus(String status) {
        return List.of();
    }

    @Override
    public List<Employee> findByRole(String role) {
        return List.of();
    }

    private List<Employee> executeQueryList(String sql, Object... params) {
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    employees.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to execute employee query.", e);
        }

        return employees;
    }

    private Employee mapRow(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getString("department"),
                rs.getString("role"),
                rs.getDouble("salary"),
                rs.getDate("joining_date").toLocalDate(),
                EmployeeStatus.valueOf(rs.getString("status")),
                Gender.valueOf(rs.getString("gender")),
                EmploymentType.valueOf(rs.getString("employment_type")),
                rs.getBoolean("is_manager")
        );
    }
}



//    //salary @Override
//    public List<Employee> findAllOrderBySalaryAsc() {
//        String sql = "SELECT * FROM employees ORDER BY salary ASC";
//        return getEmployeesFromQuery(sql);
//    }
//    @Override
//    public List<Employee> findAllOrderBySalaryDesc() {
//        String sql = "SELECT * FROM employees ORDER BY salary DESC";
//        return getEmployeesFromQuery(sql);
//    }
//    @Override
//    public Optional<Employee> findEmployeeWithMaxSalary() {
//        String sql = "SELECT * FROM employees ORDER BY salary DESC LIMIT 1";
//
//        try (Connection connection = DBConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            if (resultSet.next()) {
//                return Optional.of(mapRowToEmployee(resultSet));
//            }
//
//            return Optional.empty();
//
//        } catch (SQLException e) {
//            throw new DatabaseOperationException("Error fetching employee with max salary", e);
//        }
//    }

//    @Override
//    public boolean isEmployeeManager(int id) {
//        String sql = "SELECT is_manager FROM employees WHERE id = ?";
//
//        try (Connection connection = DBConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setInt(1, id);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getBoolean("is_manager");
//                }
//                return false;
//            }
//
//        } catch (SQLException e) {
//            throw new DatabaseOperationException("Error checking manager status for employee id: " + id, e);
//        }
//    }
//    @Override
//    public List<Employee> findByDepartment(String department) {
//        String sql = "SELECT * FROM employees WHERE department = ?";
//
//        List<Employee> employees = new ArrayList<>();
//
//        try (Connection connection = DBConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setString(1, department);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    employees.add(mapRowToEmployee(resultSet));
//                }
//            }
//
//            return employees;
//
//        } catch (SQLException e) {
//            throw new DatabaseOperationException("Error fetching employees by department", e);
//        }
//    }

    //makifn one reusable cleas instead of getid again and again do this
//    private Employee mapRowToEmployee(ResultSet rs) throws SQLException {
//        Employee employee = new Employee();
//        employee.setId(rs.getInt("id"));
//        employee.setFirstName(rs.getString("first_name"));
//        employee.setLastName(rs.getString("last_name"));
//        employee.setEmail(rs.getString("email"));
//        employee.setPhoneNumber(rs.getString("phone_number"));
//        employee.setDepartment(rs.getString("department"));
//        employee.setRole(rs.getString("role"));
//        employee.setSalary(rs.getDouble("salary"));
//        employee.setJoiningDate(rs.getDate("joining_date").toLocalDate());
//        employee.setStatus(Enum.valueOf(
//                com.example.ems.model.EmployeeStatus.class,
//                rs.getString("status")
//        ));
//        employee.setGender(Enum.valueOf(
//                com.example.ems.model.Gender.class,
//                rs.getString("gender")
//        ));
//        employee.setEmploymentType(Enum.valueOf(
//                com.example.ems.model.EmploymentType.class,
//                rs.getString("employment_type")
//        ));
//        employee.setManager(rs.getBoolean("is_manager"));
//        return employee;
//    }

