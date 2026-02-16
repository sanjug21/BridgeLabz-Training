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
        Employee employee = new Employee("Rajesh Kumar", 101, 20);
        
        System.out.println("Employee Details:");
        System.out.println("Name: " + employee.getName());
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Wage Per Hour: Rs " + employee.getWagePerHour());
        System.out.println();

        // UC1: Mark attendance using random
        employeeWageService.markAttendance(employee);
        System.out.println();
        
        // UC3: Set employee work type (Part-time or Full-time)
        employeeWageService.setEmployeeWorkType(employee);
        System.out.println();
        
        // UC2 & UC3: Calculate daily wage based on attendance, work type, and wage per hour (Rs 20)
        int dailyWage = employeeWageService.calculateDailyWage(employee);
        System.out.println("Daily Employee Wage: Rs " + dailyWage);
        
        System.out.println("========================================");
    }
}
