package com.sanju.service;

import com.sanju.model.Employee;
import com.sanju.model.CompanyEmpWage;
import com.sanju.model.EmpWageBuilder;
import com.sanju.repository.EmployeeWageRepository;

public class EmployeeWageService {
    // UC9: Repository instance to save company wage builders
    private EmployeeWageRepository repository;
    
    // UC1: Constants for attendance
    public static final int IS_ABSENT = 0;
    public static final int IS_PART_TIME = 1;
    public static final int IS_FULL_TIME = 2;
    
    // UC2 & UC3: Constants for wage calculation - Full Day Hour is 8, Part Time Hour is 4
    public static final int FULL_DAY_HOURS = 8;
    public static final int PART_TIME_HOURS = 4;
    
    // UC5: Constants for monthly wage calculation - Assume 20 Working Days per Month
    public static final int WORKING_DAYS_PER_MONTH = 20;
    
    // UC6: Constants for wage calculation with conditions - Max 100 hours and 20 days
    public static final int MAX_WORKING_HOURS_PER_MONTH = 100;
    public static final int MAX_WORKING_DAYS_PER_MONTH = 20;
    
    // UC9: Constructor to initialize repository
    public EmployeeWageService() {
        this.repository = new EmployeeWageRepository();
    }
    
    // UC9: Constructor with repository injection
    public EmployeeWageService(EmployeeWageRepository repository) {
        this.repository = repository;
    }

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
    
    // UC6: Helper method to get hours worked based on employee status
    private int getHoursWorked(Employee employee) {
        if (!employee.isPresent()) {
            return 0;
        } else if (employee.isPartTime()) {
            return PART_TIME_HOURS;
        } else {
            return FULL_DAY_HOURS;
        }
    }
    
    //UC6: Calculate Wages till a condition of total working hours or days is reached
    
    public int calculateWageWithConditions(Employee employee) {
        int totalWage = 0;
        int totalWorkingDays = 0;
        int totalWorkingHours = 0;
        
        System.out.println("\nCalculating Wage with Conditions:");
        System.out.println("Max Working Hours: " + MAX_WORKING_HOURS_PER_MONTH);
        System.out.println("Max Working Days: " + MAX_WORKING_DAYS_PER_MONTH);
        System.out.println("==================================================");
        
        while (totalWorkingHours < MAX_WORKING_HOURS_PER_MONTH && totalWorkingDays < MAX_WORKING_DAYS_PER_MONTH) {
            totalWorkingDays++;
            
            // UC6: Set employee type using existing method
            setEmployeeType(employee);
            
            // UC6: Get hours worked based on employee type
            int hoursWorked = getHoursWorked(employee);
            
            // UC6: Calculate daily wage using existing method
            int dailyWage = calculateDailyWage(employee);
            
            // Update totals
            totalWorkingHours += hoursWorked;
            totalWage += dailyWage;
            
            System.out.println("Day " + totalWorkingDays + ": Hours: " + hoursWorked + ", Wage: Rs " + dailyWage);
        }
        
        System.out.println("==================================================");
        System.out.println("Total Working Days: " + totalWorkingDays);
        System.out.println("Total Working Hours: " + totalWorkingHours);
        
        return totalWage;
    }
    
   // UC7: Compute Company Employee Wage using Class Variables and Methods
     
    public void computeCompanyEmployeeWage(CompanyEmpWage company) {
        int totalWage = 0;
        int totalWorkingDays = 0;
        int totalWorkingHours = 0;
        
        System.out.println("\nComputing Employee Wage for: " + company.getCompanyName());
        System.out.println("Wage Per Hour: Rs " + company.getWagePerHour());
        System.out.println("Max Working Days: " + company.getMaxWorkingDays());
        System.out.println("Max Working Hours: " + company.getMaxWorkingHours());
        System.out.println("==================================================");
        
        while (totalWorkingHours < company.getMaxWorkingHours() && 
               totalWorkingDays < company.getMaxWorkingDays()) {
            totalWorkingDays++;
            
            // Get employee type and hours worked
            int empType = checkEmployeeType();
            int hoursWorked = getHoursWorkedByType(empType);
            int dailyWage = company.getWagePerHour() * hoursWorked;
            
            totalWorkingHours += hoursWorked;
            totalWage += dailyWage;
            
            System.out.println("Day " + totalWorkingDays + ": " + getEmployeeStatusText(empType) + 
                             " - Hours: " + hoursWorked + ", Wage: Rs " + dailyWage);
        }
        
        System.out.println("==================================================");
        System.out.println("Total Working Days: " + totalWorkingDays);
        System.out.println("Total Working Hours: " + totalWorkingHours);
        System.out.println("Total Employee Wage: Rs " + totalWage);
        
        // Update company object with results
        company.setTotalWage(totalWage);
        company.setTotalWorkingDays(totalWorkingDays);
        company.setTotalWorkingHours(totalWorkingHours);
    }
    
    // UC7: Helper method to get hours worked based on employee type
    private int getHoursWorkedByType(int empType) {
        switch (empType) {
            case IS_ABSENT:
                return 0;
            case IS_PART_TIME:
                return PART_TIME_HOURS;
            case IS_FULL_TIME:
                return FULL_DAY_HOURS;
            default:
                return 0;
        }
    }
    
    // UC7: Helper method to get employee status text
    private String getEmployeeStatusText(int empType) {
        switch (empType) {
            case IS_ABSENT:
                return "ABSENT";
            case IS_PART_TIME:
                return "PART-TIME";
            case IS_FULL_TIME:
                return "FULL-TIME";
            default:
                return "ABSENT";
        }
    }
    
    //UC8: Compute Employee Wage using Function Parameters (not class variables)
     
    public int computeEmployeeWageWithParams(String companyName, int wagePerHour, int maxWorkingDays, int maxWorkingHours) {
        int totalWage = 0;
        int totalWorkingDays = 0;
        int totalWorkingHours = 0;
        
        System.out.println("\nComputing Employee Wage for: " + companyName);
        System.out.println("Wage Per Hour: Rs " + wagePerHour);
        System.out.println("Max Working Days: " + maxWorkingDays);
        System.out.println("Max Working Hours: " + maxWorkingHours);
        System.out.println("==================================================");
        
        while (totalWorkingHours < maxWorkingHours && totalWorkingDays < maxWorkingDays) {
            totalWorkingDays++;
            
            // Get employee type and hours worked
            int empType = checkEmployeeType();
            int hoursWorked = getHoursWorkedByType(empType);
            int dailyWage = wagePerHour * hoursWorked;
            
            totalWorkingHours += hoursWorked;
            totalWage += dailyWage;
            
            System.out.println("Day " + totalWorkingDays + ": " + getEmployeeStatusText(empType) + 
                             " - Hours: " + hoursWorked + ", Wage: Rs " + dailyWage);
        }
        
        System.out.println("==================================================");
        System.out.println("Total Working Days: " + totalWorkingDays);
        System.out.println("Total Working Hours: " + totalWorkingHours);
        System.out.println("Total Employee Wage: Rs " + totalWage);
        
        return totalWage;
    }
    
    //UC9: Compute and Save Employee Wage using EmpWageBuilder with Instance Variables
    
    public void computeAndSaveEmployeeWage(EmpWageBuilder builder) {
        int totalWage = 0;
        int totalWorkingDays = 0;
        int totalWorkingHours = 0;
        
        System.out.println("\nComputing Employee Wage for: " + builder.getCompanyName());
        System.out.println("Wage Per Hour: Rs " + builder.getWagePerHour());
        System.out.println("Max Working Days: " + builder.getMaxWorkingDays());
        System.out.println("Max Working Hours: " + builder.getMaxWorkingHours());
        System.out.println("==================================================");
        
        while (totalWorkingHours < builder.getMaxWorkingHours() && 
               totalWorkingDays < builder.getMaxWorkingDays()) {
            totalWorkingDays++;
            
            // Get employee type and hours worked
            int empType = checkEmployeeType();
            int hoursWorked = getHoursWorkedByType(empType);
            int dailyWage = builder.getWagePerHour() * hoursWorked;
            
            totalWorkingHours += hoursWorked;
            totalWage += dailyWage;
            
            System.out.println("Day " + totalWorkingDays + ": " + getEmployeeStatusText(empType) + 
                             " - Hours: " + hoursWorked + ", Wage: Rs " + dailyWage);
        }
        
        System.out.println("==================================================");
        System.out.println("Total Working Days: " + totalWorkingDays);
        System.out.println("Total Working Hours: " + totalWorkingHours);
        System.out.println("Total Employee Wage: Rs " + totalWage);
        
        // UC9: Save computed values to builder instance variables
        builder.setTotalWage(totalWage);
        builder.setTotalWorkingDays(totalWorkingDays);
        builder.setTotalWorkingHours(totalWorkingHours);
        
        // UC9: Save builder to repository
        repository.addCompanyWageBuilder(builder);
        System.out.println("\n*** Saved wage for " + builder.getCompanyName() + " to repository ***");
    }
    
    // UC9: Get repository instance (for displaying saved wages)
    public EmployeeWageRepository getRepository() {
        return repository;
    }
    
    // UC10: Compute and save Employee Wage for multiple companies using EmpWageBuilder
     
    public void computeWageForMultipleCompanies(EmpWageBuilder empWageBuilder) {
        System.out.println("\nComputing wages for all companies managed by EmpWageBuilder...");
        System.out.println("==================================================================");
        
        // UC10: Process each CompanyEmpWage in the builder
        for (CompanyEmpWage company : empWageBuilder.getAllCompanyWages()) {
            computeWageForCompany(company);
        }
        
        System.out.println("==================================================================");
        // UC10: Display summary
        empWageBuilder.displayAllCompanyWages();
        
        System.out.println("Total Companies Managed: " + empWageBuilder.getTotalCompanies());
        System.out.println("Total Wage Across All Companies: Rs " + empWageBuilder.getTotalWageAcrossAllCompanies());
    }
    
    // UC10: Helper method to compute wage for a single company
    private void computeWageForCompany(CompanyEmpWage company) {
        int totalWage = 0;
        int totalWorkingDays = 0;
        int totalWorkingHours = 0;
        
        System.out.println("\nProcessing: " + company.getCompanyName());
        
        while (totalWorkingHours < company.getMaxWorkingHours() && 
               totalWorkingDays < company.getMaxWorkingDays()) {
            totalWorkingDays++;
            
            // Get employee type and hours worked
            int empType = checkEmployeeType();
            int hoursWorked = getHoursWorkedByType(empType);
            int dailyWage = company.getWagePerHour() * hoursWorked;
            
            totalWorkingHours += hoursWorked;
            totalWage += dailyWage;
        }
        
        // UC10: Save computed values to company object
        company.setTotalWage(totalWage);
        company.setTotalWorkingDays(totalWorkingDays);
        company.setTotalWorkingHours(totalWorkingHours);
        
        System.out.println("  -> Total Wage: Rs " + totalWage + ", Days: " + totalWorkingDays + ", Hours: " + totalWorkingHours);
    }
}
