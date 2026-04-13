package com.example.ems.view;

import com.example.ems.controller.EmployeeController;
import com.example.ems.model.Employee;
import com.example.ems.model.EmployeeStatus;
import com.example.ems.model.EmploymentType;
import com.example.ems.model.Gender;
import com.example.ems.utill.AppMessages;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleView {

    private final EmployeeController controller;
    private final Scanner scanner;

    public ConsoleView(EmployeeController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            showMenu();
            int choice = readInt(AppMessages.ENTER_CHOICE);

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    showAllEmployees();
                    break;
                case 3:
                    searchEmployeeById();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    findEmployeesByDepartment();
                    break;
                case 7:
                    showEmployeesBySalaryAsc();
                    break;
                case 8:
                    showEmployeesBySalaryDesc();
                    break;
                case 9:
                    showEmployeeWithMaxSalary();
                    break;
                case 10:
                    checkIfEmployeeIsManager();
                    break;
                case 11:
                    System.out.println(AppMessages.EXIT_MESSAGE);
                    scanner.close();
                    return;
                default:
                    System.out.println(AppMessages.INVALID_CHOICE);
            }
        }
    }

    private void showMenu() {
        System.out.println(AppMessages.APP_HEADER);
        System.out.println(AppMessages.MENU);
    }

    private void addEmployee() {
        System.out.print(AppMessages.ENTER_FIRST_NAME);
        String firstName = scanner.nextLine();

        System.out.print(AppMessages.ENTER_LAST_NAME);
        String lastName = scanner.nextLine();

        System.out.print(AppMessages.ENTER_EMAIL);
        String email = scanner.nextLine();

        System.out.print(AppMessages.ENTER_PHONE);
        String phone = scanner.nextLine();

        System.out.print(AppMessages.ENTER_DEPARTMENT);
        String department = scanner.nextLine();

        System.out.print(AppMessages.ENTER_ROLE);
        String role = scanner.nextLine();

        double salary = readDouble(AppMessages.ENTER_SALARY);
        LocalDate joiningDate = readDate(AppMessages.ENTER_JOINING_DATE);
        EmployeeStatus status = readEnum(EmployeeStatus.class, AppMessages.ENTER_STATUS);
        Gender gender = readEnum(Gender.class, AppMessages.ENTER_GENDER);
        EmploymentType employmentType = readEnum(EmploymentType.class, AppMessages.ENTER_EMPLOYMENT_TYPE);
        boolean isManager = readBoolean(AppMessages.IS_MANAGER);

        boolean added = controller.addEmployee(
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

        if (added) {
            System.out.println(AppMessages.EMPLOYEE_ADDED);
        } else {
            System.out.println("Employee could not be added. Please check the entered data.");
        }
    }

    private void showAllEmployees() {
        List<Employee> employees = controller.getAllEmployees();
        printEmployeeTable(employees);
    }
    private void printEmployeeTable(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println(AppMessages.NO_EMPLOYEES_FOUND);
            return;
        }

        System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-20s %-28s %-15s %-15s %-20s %-12s %-12s %-12s %-10s %-15s %-10s%n",
                "ID", "Full Name", "Email", "Phone", "Department", "Role", "Salary", "Join Date", "Status", "Gender", "Emp Type", "Manager");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Employee employee : employees) {
            System.out.printf("%-4d %-20s %-28s %-15s %-15s %-20s %-12.2f %-12s %-12s %-10s %-15s %-10s%n",
                    employee.getId(),
                    employee.getFullName(),
                    employee.getEmail(),
                    employee.getPhoneNumber(),
                    employee.getDepartment(),
                    employee.getRole(),
                    employee.getSalary(),
                    employee.getJoiningDate(),
                    employee.getStatus(),
                    employee.getGender(),
                    employee.getEmploymentType(),
                    employee.isManager() ? "Yes" : "No");
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private void searchEmployeeById() {
        int id = readInt(AppMessages.ENTER_ID);
        Employee employee = controller.searchEmployeeById(id);

        if (employee == null) {
            System.out.println(AppMessages.EMPLOYEE_NOT_FOUND);
        } else {
            System.out.println(employee);
        }
    }

    private void updateEmployee() {
        int id = readInt(AppMessages.ENTER_ID);

        Employee existingEmployee = controller.searchEmployeeById(id);
        if (existingEmployee == null) {
            System.out.println(AppMessages.EMPLOYEE_NOT_FOUND);
            return;
        }

        System.out.print(AppMessages.ENTER_UPDATED_FIRST_NAME);
        String firstName = scanner.nextLine();

        System.out.print(AppMessages.ENTER_UPDATED_LAST_NAME);
        String lastName = scanner.nextLine();

        System.out.print(AppMessages.ENTER_UPDATED_EMAIL);
        String email = scanner.nextLine();

        System.out.print(AppMessages.ENTER_UPDATED_PHONE);
        String phone = scanner.nextLine();

        System.out.print(AppMessages.ENTER_UPDATED_DEPARTMENT);
        String department = scanner.nextLine();

        System.out.print(AppMessages.ENTER_UPDATED_ROLE);
        String role = scanner.nextLine();

        double salary = readDouble(AppMessages.ENTER_UPDATED_SALARY);
        LocalDate joiningDate = readDate(AppMessages.ENTER_UPDATED_JOINING_DATE);
        EmployeeStatus status = readEnum(EmployeeStatus.class, AppMessages.ENTER_UPDATED_STATUS);
        Gender gender = readEnum(Gender.class, AppMessages.ENTER_UPDATED_GENDER);
        EmploymentType employmentType = readEnum(EmploymentType.class, AppMessages.ENTER_UPDATED_EMPLOYMENT_TYPE);
        boolean isManager = readBoolean(AppMessages.ENTER_UPDATED_MANAGER);

        boolean updated = controller.updateEmployee(
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

        if (updated) {
            System.out.println(AppMessages.EMPLOYEE_UPDATED);
        } else {
            System.out.println("Employee could not be updated. Please check the entered data.");
        }
    }

    private void deleteEmployee() {
        int id = readInt(AppMessages.ENTER_ID);

        boolean deleted = controller.deleteEmployee(id);

        if (deleted) {
            System.out.println(AppMessages.EMPLOYEE_DELETED);
        } else {
            System.out.println(AppMessages.EMPLOYEE_NOT_FOUND);
        }
    }

    private void findEmployeesByDepartment() {
        System.out.print(AppMessages.ENTER_SEARCH_DEPARTMENT);
        String department = scanner.nextLine();

        List<Employee> employees = controller.findByDepartment(department);

        if (employees.isEmpty()) {
            System.out.println(AppMessages.NO_EMPLOYEES_FOUND);
            return;
        }

        System.out.println(AppMessages.SHOWING_EMPLOYEES_BY_DEPARTMENT + " " + department);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private void showEmployeesBySalaryAsc() {
        List<Employee> employees = controller.findAllOrderBySalaryAsc();

        if (employees.isEmpty()) {
            System.out.println(AppMessages.NO_EMPLOYEES_FOUND);
            return;
        }

        //List<Employee> employees = controller.findAllOrderBySalaryAsc();

        System.out.println(AppMessages.SHOWING_EMPLOYEES_SALARY_ASC);
        printEmployeeTable(employees);
    }

    private void showEmployeesBySalaryDesc() {
        List<Employee> employees = controller.findAllOrderBySalaryDesc();
//
        if (employees.isEmpty()) {
            System.out.println(AppMessages.NO_EMPLOYEES_FOUND);
            return;
        }
//
//        System.out.println(AppMessages.SHOWING_EMPLOYEES_SALARY_DESC);
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }
//        List<Employee> employees = controller.findAllOrderBySalaryDesc();

        System.out.println(AppMessages.SHOWING_EMPLOYEES_SALARY_DESC);
        printEmployeeTable(employees);
    }

    private void showEmployeeWithMaxSalary() {
//        Optional<Employee> employeeOptional = controller.findEmployeeWithMaxSalary();
//
//        if (employeeOptional.isPresent()) {
//            System.out.println(AppMessages.SHOWING_MAX_SALARY_EMPLOYEE);
//            System.out.println(employeeOptional.get());
//        } else {
//            System.out.println(AppMessages.NO_EMPLOYEES_FOUND);
//        }
        Optional<Employee> employeeOptional = controller.findEmployeeWithMaxSalary();

        if (employeeOptional.isPresent()) {
            System.out.println(AppMessages.SHOWING_MAX_SALARY_EMPLOYEE);
            printEmployeeTable(List.of(employeeOptional.get()));
        } else {
            System.out.println(AppMessages.NO_EMPLOYEES_FOUND);
        }
    }

    private void checkIfEmployeeIsManager() {
        int id = readInt(AppMessages.ENTER_ID);

        Employee employee = controller.searchEmployeeById(id);
        if (employee == null) {
            System.out.println(AppMessages.EMPLOYEE_NOT_FOUND);
            return;
        }

        boolean isManager = controller.isEmployeeManager(id);

        if (isManager) {
            System.out.println(AppMessages.MANAGER_YES);
        } else {
            System.out.println(AppMessages.MANAGER_NO);
        }
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(AppMessages.INVALID_NUMBER);
            }
        }
    }

    private double readDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(AppMessages.INVALID_NUMBER);
            }
        }
    }

    private boolean readBoolean(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("true")) {
                return true;
            } else if (input.equalsIgnoreCase("false")) {
                return false;
            } else {
                System.out.println(AppMessages.INVALID_BOOLEAN);
            }
        }
    }

    private LocalDate readDate(String message) {
        while (true) {
            System.out.print(message);
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println(AppMessages.INVALID_DATE);
            }
        }
    }

    private <T extends Enum<T>> T readEnum(Class<T> enumClass, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println(AppMessages.INVALID_ENUM);
            }
        }
    }
}