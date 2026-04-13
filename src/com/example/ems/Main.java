package com.example.ems;

import com.example.ems.controller.EmployeeController;
import com.example.ems.dao.EmployeeDao;
import com.example.ems.dao.EmployeeJdbcDao;
import com.example.ems.service.EmployeeService;
import com.example.ems.view.ConsoleView;

public class Main {

    public static void main(String[] args) {

        // DAO Layer
        EmployeeDao dao = new EmployeeJdbcDao();

        // Service Layer
        EmployeeService service = new EmployeeService(dao);

        // Controller Layer
        EmployeeController controller = new EmployeeController(service);

        //Controller → Service → DAO

        // View Layer
        ConsoleView view = new ConsoleView(controller);

        // Start App
        view.start();
    }
}