package com.sanju.service;

import com.sanju.model.Employee;

public class EmployeeWageService {
    // UC1: Constants for attendance
    public static final int IS_ABSENT = 0;
    public static final int IS_PART_TIME = 1;
    public static final int IS_FULL_TIME = 2;
    
    // UC2 & UC3: Constants for wage calculation - Full Day Hour is 8, Part Time Hour is 4
    public static final int FULL_DAY_HOURS = 8;
    public static final int PART_TIME_HOURS = 4;
    
    // UC5: Constants for monthly wage calculation - Assume 20 Working Days per Month
    public static final int WORKING_DAYS_PER_MONTH = 20;

    // UC4: Check Employee Type using Random (Absent, Part-time, or Full-time)
    public int checkEmployeeType() {
        // Use Math.random() to generate random number (0, 1, or 2)
        // 0 = Absent, 1 = Part-time, 2 = Full-time
        return (int) (Math.random() * 3);
    }

    // UC4: Set Employee Type using Switch Case
    public void setEmployeeType(Employee employee) {
        int empType = checkEmployeeType();
        
        switch (empType) {
            case IS_ABSENT:
                employee.setPresent(false);
                employee.setPartTime(false);
                System.out.println("Employee " + employee.getName() + " is ABSENT");
                break;
            case IS_PART_TIME:
                employee.setPresent(true);
                employee.setPartTime(true);
                System.out.println("Employee " + employee.getName() + " is PRESENT (PART-TIME)");
                break;
            case IS_FULL_TIME:
                employee.setPresent(true);
                employee.setPartTime(false);
                System.out.println("Employee " + employee.getName() + " is PRESENT (FULL-TIME)");
                break;
            default:
                employee.setPresent(false);
                employee.setPartTime(false);
                System.out.println("Employee " + employee.getName() + " is ABSENT");
                break;
        }
    }
    
    // UC4: Calculate Daily Employee Wage using Switch Case
    public int calculateDailyWage(Employee employee) {
        int wage = 0;
        int empHours = 0;
        
        if (!employee.isPresent()) {
            empHours = 0;
        } else if (employee.isPartTime()) {
            empHours = PART_TIME_HOURS;
        } else {
            empHours = FULL_DAY_HOURS;
        }
        
        switch (empHours) {
            case 0:
                wage = 0;
                break;
            case PART_TIME_HOURS:
                wage = employee.getWagePerHour() * PART_TIME_HOURS;
                break;
            case FULL_DAY_HOURS:
                wage = employee.getWagePerHour() * FULL_DAY_HOURS;
                break;
            default:
                wage = 0;
                break;
        }
        
        return wage;
    }
    
   // UC5: Calculate Monthly Employee Wage
    
    public int calculateMonthlyWage(Employee employee) {
        int totalMonthlyWage = 0;
        
        System.out.println("\nCalculating Monthly Wage for " + WORKING_DAYS_PER_MONTH + " working days:");
        System.out.println("==================================================");
        
        for (int day = 1; day <= WORKING_DAYS_PER_MONTH; day++) {
            // Set employee type for each day
            setEmployeeType(employee);
            
            // Calculate daily wage
            int dailyWage = calculateDailyWage(employee);
            totalMonthlyWage += dailyWage;
            
            System.out.println("Day " + day + ": Rs " + dailyWage);
        }
        
        System.out.println("==================================================");
        return totalMonthlyWage;
    }
}
