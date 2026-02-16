package com.sanju.service;

import com.sanju.model.Employee;

public class EmployeeWageService {
    // UC1: Constants for attendance
    public static final int IS_ABSENT = 0;
    public static final int IS_PRESENT = 1;
    
    // UC2 & UC3: Constants for wage calculation - Full Day Hour is 8, Part Time Hour is 4
    public static final int FULL_DAY_HOURS = 8;
    public static final int PART_TIME_HOURS = 4;

    // UC1: Check Employee Attendance using Random
    public boolean checkAttendance() {
        // Use Math.random() to generate random number (0 or 1)
        int attendance = (int) (Math.random() * 2);
        return attendance == IS_PRESENT;
    }

    // UC1: Mark Employee Attendance
    public void markAttendance(Employee employee) {
        boolean isPresent = checkAttendance();
        employee.setPresent(isPresent);
        
        if (isPresent) {
            System.out.println("Employee " + employee.getName() + " is PRESENT");
        } else {
            System.out.println("Employee " + employee.getName() + " is ABSENT");
        }
    }
    
    // UC3: Check if employee is part-time or full-time using Random
    public boolean isPartTime() {
        // Use Math.random() to generate random number (0 or 1)
        // 0 = Full Time, 1 = Part Time
        int empType = (int) (Math.random() * 2);
        return empType == 1;
    }
    
    // UC3: Set employee work type (Part-time or Full-time)
    public void setEmployeeWorkType(Employee employee) {
        boolean partTime = isPartTime();
        employee.setPartTime(partTime);
        
        if (partTime) {
            System.out.println("Employee " + employee.getName() + " is PART-TIME");
        } else {
            System.out.println("Employee " + employee.getName() + " is FULL-TIME");
        }
    }
    
    // UC2 & UC3: Calculate Daily Employee Wage based on work type
    public int calculateDailyWage(Employee employee) {
        if (employee.isPresent()) {
            if (employee.isPartTime()) {
                return employee.getWagePerHour() * PART_TIME_HOURS;
            } else {
                return employee.getWagePerHour() * FULL_DAY_HOURS;
            }
        }
        return 0;
    }
}
