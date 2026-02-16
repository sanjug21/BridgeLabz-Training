package com.sanju.repository;

import com.sanju.model.CompanyEmpWage;
import java.util.ArrayList;
import java.util.List;

/**
 * UC12: Repository to store CompanyEmpWage instances for multiple companies
 * Simplified for essential models only
 */
public class EmployeeWageRepository {
    // UC12: Instance variable to store company wages
    private List<CompanyEmpWage> companyWages;
    
    public EmployeeWageRepository() {
        this.companyWages = new ArrayList<>();
    }
    
    // UC12: Add company wage to repository
    public void addCompanyWage(CompanyEmpWage wage) {
        companyWages.add(wage);
    }
    
    // UC12: Get all company wages
    public List<CompanyEmpWage> getAllCompanyWages() {
        return companyWages;
    }
    
    // UC12: Get company wage by name
    public CompanyEmpWage getCompanyWage(String companyName) {
        for (CompanyEmpWage wage : companyWages) {
            if (wage.getCompanyName().equalsIgnoreCase(companyName)) {
                return wage;
            }
        }
        return null;
    }
    
    // UC12: Display all saved company wages
    public void displayAllCompanyWages() {
        System.out.println("\n========== Saved Company Wages ==========");
        if (companyWages.isEmpty()) {
            System.out.println("No company wages saved yet.");
        } else {
            for (CompanyEmpWage wage : companyWages) {
                System.out.println("\nCompany: " + wage.getCompanyName());
                System.out.println("Total Wage: Rs " + wage.getTotalWage());
                System.out.println("Total Working Days: " + wage.getTotalWorkingDays());
                System.out.println("Total Working Hours: " + wage.getTotalWorkingHours());
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("=========================================\n");
    }
}
