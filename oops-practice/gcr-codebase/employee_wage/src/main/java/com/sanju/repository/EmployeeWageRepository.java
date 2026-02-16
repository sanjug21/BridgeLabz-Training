package com.sanju.repository;

import com.sanju.model.EmpWageBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * UC9: Repository to store EmpWageBuilder instances for multiple companies
 */
public class EmployeeWageRepository {
    // UC9: Instance variable to store company wage builders
    private List<EmpWageBuilder> companyWageBuilders;
    
    public EmployeeWageRepository() {
        this.companyWageBuilders = new ArrayList<>();
    }
    
    // UC9: Add company wage builder to repository
    public void addCompanyWageBuilder(EmpWageBuilder builder) {
        companyWageBuilders.add(builder);
    }
    
    // UC9: Get all company wage builders
    public List<EmpWageBuilder> getAllCompanyWageBuilders() {
        return companyWageBuilders;
    }
    
    // UC9: Get company wage builder by name
    public EmpWageBuilder getCompanyWageBuilder(String companyName) {
        for (EmpWageBuilder builder : companyWageBuilders) {
            if (builder.getCompanyName().equalsIgnoreCase(companyName)) {
                return builder;
            }
        }
        return null;
    }
    
    // UC9: Display all saved company wages
    public void displayAllCompanyWages() {
        System.out.println("\n========== Saved Company Wages ==========");
        if (companyWageBuilders.isEmpty()) {
            System.out.println("No company wages saved yet.");
        } else {
            for (EmpWageBuilder builder : companyWageBuilders) {
                System.out.println("\nCompany: " + builder.getCompanyName());
                System.out.println("Total Wage: Rs " + builder.getTotalWage());
                System.out.println("Total Working Days: " + builder.getTotalWorkingDays());
                System.out.println("Total Working Hours: " + builder.getTotalWorkingHours());
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("=========================================\n");
    }
}
