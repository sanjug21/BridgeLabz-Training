package com.sanju.service;

import com.sanju.model.Employee;

public class EmployeeWageService {
    // Constants for attendance
    public static final int IS_ABSENT = 0;
    public static final int IS_PRESENT = 1;
    
    // UC2: Constants for wage calculation - Assume Full Day Hour is 8
    public static final int FULL_DAY_HOURS = 8;
    public static final int PART_TIME_HOURS = 4;

    // UC1: Check Employee Attendance
    public boolean checkAttendance() {
        // Use Math.random() to generate random number (0 or 1)
        int attendance = (int) (Math.random() * 2);
        return attendance == IS_PRESENT;
    }

    public void markAttendance(Employee employee) {
        boolean isPresent = checkAttendance();
        employee.setPresent(isPresent);
        
        if (isPresent) {
            System.out.println("Employee " + employee.getName() + " is PRESENT");
        } else {
            System.out.println("Employee " + employee.getName() + " is ABSENT");
        }
    }
    
    // UC2: Calculate Daily Employee Wage
    public int calculateDailyWage(Employee employee) {
        if (employee.isPresent()) {
            return employee.getWagePerHour() * FULL_DAY_HOURS;
        }
        return 0;
    }
}
