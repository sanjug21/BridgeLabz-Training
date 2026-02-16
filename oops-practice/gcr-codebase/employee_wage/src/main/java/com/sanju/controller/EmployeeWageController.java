package com.sanju.controller;

import com.sanju.model.Employee;
import com.sanju.model.CompanyEmpWage;
import com.sanju.service.EmployeeWageService;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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
        
        // Display menu for all use cases
        System.out.println("Select Use Case:");
        System.out.println("1. UC1 - Check Employee Attendance");
        System.out.println("2. UC2 - Calculate Daily Employee Wage");
        System.out.println("3. UC3 - Calculate Wage with Part-time");
        System.out.println("4. UC4 - Employee Type using Switch Case");
        System.out.println("5. UC5 - Calculate Monthly Wage (20 days)");
        System.out.println("6. UC6 - Calculate Wage with Conditions (100 hours or 20 days)");
        System.out.println("7. UC7 - Compute Wage using Class Method (Refactored)");
        System.out.println("8. UC11 - Manage Multiple Companies using ArrayList");
        System.out.println("9. UC12 - Manage Multiple Companies with Advanced ArrayList");
        System.out.print("Enter your choice (1-9): ");
        
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println();
        
        executeUseCase(choice, employee);
        
        scanner.close();
        System.out.println("========================================");
    }
    
    private void executeUseCase(int choice, Employee employee) {
        int wage = 0;
        
        switch (choice) {
            case 1:
                System.out.println("Executing UC1: Check Employee Attendance");
                System.out.println("==========================================");
                employeeWageService.setEmployeeType(employee);
                System.out.println("Attendance Status: " + (employee.isPresent() ? "PRESENT" : "ABSENT"));
                break;
                
            case 2:
                System.out.println("Executing UC2: Calculate Daily Employee Wage");
                System.out.println("=============================================");
                employeeWageService.setEmployeeType(employee);
                wage = employeeWageService.calculateDailyWage(employee);
                System.out.println("Daily Employee Wage: Rs " + wage);
                break;
                
            case 3:
                System.out.println("Executing UC3: Calculate Wage with Part-time");
                System.out.println("=============================================");
                employeeWageService.setEmployeeType(employee);
                wage = employeeWageService.calculateDailyWage(employee);
                System.out.println("Work Type: " + (employee.isPartTime() ? "PART-TIME" : "FULL-TIME"));
                System.out.println("Daily Employee Wage: Rs " + wage);
                break;
                
            case 4:
                System.out.println("Executing UC4: Employee Type using Switch Case");
                System.out.println("===============================================");
                employeeWageService.setEmployeeType(employee);
                wage = employeeWageService.calculateDailyWage(employee);
                System.out.println("Daily Employee Wage: Rs " + wage);
                break;
                
            case 5:
                System.out.println("Executing UC5: Calculate Monthly Wage (20 days)");
                System.out.println("===============================================");
                wage = employeeWageService.calculateMonthlyWage(employee);
                System.out.println();
                System.out.println("Total Monthly Employee Wage: Rs " + wage);
                break;
                
            case 6:
                System.out.println("Executing UC6: Calculate Wage with Conditions");
                System.out.println("==============================================");
                wage = employeeWageService.calculateWageWithConditions(employee);
                System.out.println();
                System.out.println("Total Monthly Employee Wage: Rs " + wage);
                break;
                
            case 7:
                System.out.println("Executing UC7: Compute Wage using Class Method");
                System.out.println("===============================================");
                // UC7: Create CompanyEmpWage object with class variables (Model layer)
                CompanyEmpWage companyWage = new CompanyEmpWage(
                    "Tech Solutions Pvt Ltd", 
                    20,  // Wage per hour
                    20,  // Max working days
                    100  // Max working hours
                );
                // UC7: Call service method to compute employee wage (Service layer)
                employeeWageService.computeCompanyEmployeeWage(companyWage);
                break;
                
            case 8:
                System.out.println("Executing UC11: Manage Multiple Companies using ArrayList");
                System.out.println("=========================================================");
                // UC11: Create ArrayList to manage multiple companies directly
                List<CompanyEmpWage> companyWageList1 = new ArrayList<>(createMultipleCompanies());
                
                // UC11: Compute and display wages using ArrayList
                employeeWageService.computeWageWithArrayList(companyWageList1);
                break;
                
            case 9:
                System.out.println("Executing UC12: Manage Multiple Companies using ArrayList");
                System.out.println("==========================================================");
                // UC12: Create ArrayList to manage multiple companies explicitly
                List<CompanyEmpWage> companyWageList = new ArrayList<>(createMultipleCompanies());
                
                // UC12: Compute wages for ArrayList of companies
                employeeWageService.computeWageWithArrayList(companyWageList);
                break;
                
            default:
                System.out.println("Invalid choice! Please select a valid use case (1-9).");
                break;
        }
    }
    
   
    private List<CompanyEmpWage> createMultipleCompanies() {
        List<CompanyEmpWage> companies = new ArrayList<>();
        
        companies.add(new CompanyEmpWage("Accenture India Pvt Ltd", 22, 21, 110));
        companies.add(new CompanyEmpWage("HCL Technologies", 28, 20, 105));
        companies.add(new CompanyEmpWage("Cognizant India", 26, 19, 95));
        
        return companies;
    }
}
