package com.example.ems.utill;

public class AppMessages {

    public static final String APP_HEADER = "\n===== ADVANCED EMPLOYEE MANAGEMENT SYSTEM =====";

    public static final String MENU =
            "1. Add Employee\n" +
                    "2. Show All Employees\n" +
                    "3. Search Employee By ID\n" +
                    "4. Update Employee\n" +
                    "5. Delete Employee\n" +
                    "6. Find Employees By Department\n" +
                    "7. Show Employees By Salary ASC\n" +
                    "8. Show Employees By Salary DESC\n" +
                    "9. Show Employee With Max Salary\n" +
                    "10. Check If Employee Is Manager\n" +
                    "11. Exit";

    public static final String ENTER_CHOICE = "Enter your choice: ";
    public static final String ENTER_ID = "Enter employee ID: ";

    public static final String ENTER_FIRST_NAME = "Enter first name: ";
    public static final String ENTER_LAST_NAME = "Enter last name: ";
    public static final String ENTER_EMAIL = "Enter email: ";
    public static final String ENTER_PHONE = "Enter phone number: ";
    public static final String ENTER_DEPARTMENT = "Enter department: ";
    public static final String ENTER_ROLE = "Enter role: ";
    public static final String ENTER_SALARY = "Enter salary: ";
    public static final String ENTER_JOINING_DATE = "Enter joining date (YYYY-MM-DD): ";
    public static final String ENTER_STATUS = "Enter status (ACTIVE, INACTIVE, RESIGNED, TERMINATED, ON_LEAVE): ";
    public static final String ENTER_GENDER = "Enter gender (MALE, FEMALE, OTHER): ";
    public static final String ENTER_EMPLOYMENT_TYPE = "Enter employment type (FULL_TIME, PART_TIME, INTERN, CONTRACT): ";
    public static final String IS_MANAGER = "Is employee a manager? (true/false): ";

    public static final String ENTER_UPDATED_FIRST_NAME = "Enter new first name: ";
    public static final String ENTER_UPDATED_LAST_NAME = "Enter new last name: ";
    public static final String ENTER_UPDATED_EMAIL = "Enter new email: ";
    public static final String ENTER_UPDATED_PHONE = "Enter new phone number: ";
    public static final String ENTER_UPDATED_DEPARTMENT = "Enter new department: ";
    public static final String ENTER_UPDATED_ROLE = "Enter new role: ";
    public static final String ENTER_UPDATED_SALARY = "Enter new salary: ";
    public static final String ENTER_UPDATED_JOINING_DATE = "Enter new joining date (YYYY-MM-DD): ";
    public static final String ENTER_UPDATED_STATUS = "Enter new status (ACTIVE, INACTIVE, RESIGNED, TERMINATED, ON_LEAVE): ";
    public static final String ENTER_UPDATED_GENDER = "Enter new gender (MALE, FEMALE, OTHER): ";
    public static final String ENTER_UPDATED_EMPLOYMENT_TYPE = "Enter new employment type (FULL_TIME, PART_TIME, INTERN, CONTRACT): ";
    public static final String ENTER_UPDATED_MANAGER = "Is employee manager? (true/false): ";

    public static final String ENTER_SEARCH_DEPARTMENT = "Enter department to search: ";

    public static final String EMPLOYEE_ADDED = "Employee added successfully.";
    public static final String EMPLOYEE_UPDATED = "Employee updated successfully.";
    public static final String EMPLOYEE_DELETED = "Employee deleted successfully.";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found.";
    public static final String NO_EMPLOYEES_FOUND = "No employees found.";
    public static final String MANAGER_YES = "This employee is a manager.";
    public static final String MANAGER_NO = "This employee is not a manager.";
    public static final String SHOWING_MAX_SALARY_EMPLOYEE = "Employee with maximum salary:";
    public static final String SHOWING_EMPLOYEES_BY_DEPARTMENT = "Employees in department:";
    public static final String SHOWING_EMPLOYEES_SALARY_ASC = "Employees sorted by salary ascending:";
    public static final String SHOWING_EMPLOYEES_SALARY_DESC = "Employees sorted by salary descending:";

    public static final String INVALID_CHOICE = "Invalid choice. Please select a valid option.";
    public static final String INVALID_NUMBER = "Invalid input. Please enter a valid number.";
    public static final String INVALID_BOOLEAN = "Invalid input. Please enter true or false.";
    public static final String INVALID_DATE = "Invalid date. Please use format YYYY-MM-DD.";
    public static final String INVALID_ENUM = "Invalid input. Please enter one of the allowed values.";
    public static final String INVALID_SALARY = "Salary must be greater than or equal to 0.";

    public static final String EMPTY_FIRST_NAME = "First name cannot be empty.";
    public static final String EMPTY_LAST_NAME = "Last name cannot be empty.";
    public static final String EMPTY_EMAIL = "Email cannot be empty.";
    public static final String EMPTY_DEPARTMENT = "Department cannot be empty.";
    public static final String EMPTY_ROLE = "Role cannot be empty.";

    public static final String EXIT_MESSAGE = "Exiting Employee Management System. Goodbye!";

    private AppMessages() {
    }
}