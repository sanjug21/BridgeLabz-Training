package com.sanju.controller;

import com.sanju.model.Employee;
import com.sanju.model.CompanyEmpWage;
import com.sanju.model.EmpWageBuilder;
import com.sanju.service.EmployeeWageService;
import java.util.Scanner;

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
        System.out.println("8. UC8 - Compute Wage for Multiple Companies (Function Parameters)");
        System.out.println("9. UC9 - Save Total Wage for Each Company (Instance Variables)");
        System.out.println("10. UC10 - Manage Multiple Companies via EmpWageBuilder Array ");
        System.out.print("Enter your choice (1-10): ");
        
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
                System.out.println("Executing UC8: Compute Wage for Multiple Companies");
                System.out.println("==================================================");
                // UC8: Compute wage for multiple companies using function parameters
                // Each company has different wage, working days, and working hours
                
                System.out.println("\n--- Company 1: Tech Solutions Pvt Ltd ---");
                employeeWageService.computeEmployeeWageWithParams(
                    "Tech Solutions Pvt Ltd",
                    20,   // Rs 20 per hour
                    20,   // 20 working days
                    100   // 100 working hours
                );
                
                System.out.println("\n--- Company 2: Infosys Limited ---");
                employeeWageService.computeEmployeeWageWithParams(
                    "Infosys Limited",
                    25,   // Rs 25 per hour
                    22,   // 22 working days
                    120   // 120 working hours
                );
                
                System.out.println("\n--- Company 3: Wipro Technologies ---");
                employeeWageService.computeEmployeeWageWithParams(
                    "Wipro Technologies",
                    30,   // Rs 30 per hour
                    18,   // 18 working days
                    90    // 90 working hours
                );
                break;
                
            case 9:
                System.out.println("Executing UC9: Save Total Wage for Each Company");
                System.out.println("===============================================");
                // UC9: Create EmpWageBuilder instances with instance variables for each company
                
                EmpWageBuilder company1 = new EmpWageBuilder(
                    "Tech Solutions Pvt Ltd",
                    20,   // Rs 20 per hour
                    20,   // 20 working days
                    100   // 100 working hours
                );
                
                EmpWageBuilder company2 = new EmpWageBuilder(
                    "Infosys Limited",
                    25,   // Rs 25 per hour
                    22,   // 22 working days
                    120   // 120 working hours
                );
                
                EmpWageBuilder company3 = new EmpWageBuilder(
                    "Wipro Technologies",
                    30,   // Rs 30 per hour
                    18,   // 18 working days
                    90    // 90 working hours
                );
                
                // UC9: Compute and save wage for each company
                System.out.println("\n--- Computing for Company 1 ---");
                employeeWageService.computeAndSaveEmployeeWage(company1);
                
                System.out.println("\n--- Computing for Company 2 ---");
                employeeWageService.computeAndSaveEmployeeWage(company2);
                
                System.out.println("\n--- Computing for Company 3 ---");
                employeeWageService.computeAndSaveEmployeeWage(company3);
                
                // UC9: Display all saved company wages from repository
                employeeWageService.getRepository().displayAllCompanyWages();
                break;
                
            case 10:
                System.out.println("Executing UC10: Manage Multiple Companies via EmpWageBuilder");
                System.out.println("=============================================================");
                // UC10: Create one EmpWageBuilder to manage multiple companies
                EmpWageBuilder wageBuilder = new EmpWageBuilder();
                
                // UC10: Create CompanyEmpWage objects and add to builder
                CompanyEmpWage compWage1 = new CompanyEmpWage(
                    "Tech Solutions Pvt Ltd",
                    20,   // Rs 20 per hour
                    20,   // 20 working days
                    100   // 100 working hours
                );
                
                CompanyEmpWage compWage2 = new CompanyEmpWage(
                    "Infosys Limited",
                    25,   // Rs 25 per hour
                    22,   // 22 working days
                    120   // 120 working hours
                );
                
                CompanyEmpWage compWage3 = new CompanyEmpWage(
                    "Wipro Technologies",
                    30,   // Rs 30 per hour
                    18,   // 18 working days
                    90    // 90 working hours
                );
                
                // UC10: Add all companies to a single EmpWageBuilder
                wageBuilder.addCompanyWage(compWage1);
                wageBuilder.addCompanyWage(compWage2);
                wageBuilder.addCompanyWage(compWage3);
                
                // UC10: Compute wages for all companies managed by builder
                employeeWageService.computeWageForMultipleCompanies(wageBuilder);
                break;
                
            default:
                System.out.println("Invalid choice! Please select a valid use case (1-10).");
                break;
        }
    }
}
