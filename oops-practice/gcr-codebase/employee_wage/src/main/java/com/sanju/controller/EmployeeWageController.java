package com.sanju.controller;

import com.sanju.model.Employee;
import com.sanju.service.EmployeeWageService;

public class EmployeeWageController {
    private EmployeeWageService employeeWageService;

    public EmployeeWageController() {
        this.employeeWageService = new EmployeeWageService();
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Welcome to Employee Wage Computation");
        System.out.println("========================================");
        System.out.println();

        EmployeeWageController controller = new EmployeeWageController();
        controller.start();
    }

    public void start() {
        // Create sample employee
        Employee employee = new Employee("Rajesh Kumar", 101, 200);
        
        System.out.println("Employee Details:");
        System.out.println("Name: " + employee.getName());
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Wage Per Hour: Rs " + employee.getWagePerHour());
        System.out.println();


        // Mark attendance using random
        employeeWageService.markAttendance(employee);
        
        System.out.println("========================================");
    }
}
