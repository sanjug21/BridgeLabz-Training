package com.sanju.model;

import java.util.ArrayList;
import java.util.List;
/**
 * UC9: Can be used with instance variables for single company
 * UC10: Can manage multiple CompanyEmpWage objects in a collection
 */
public class EmpWageBuilder {
    // UC9: Instance variables for single company (for backward compatibility)
    private String companyName;
    private int wagePerHour;
    private int maxWorkingDays;
    private int maxWorkingHours;
    private int totalWage;
    private int totalWorkingDays;
    private int totalWorkingHours;
    
    // UC10: List to store multiple CompanyEmpWage objects
    private List<CompanyEmpWage> companyWages;
    private boolean isCollectionMode;
    
    // UC9: Constructor with parameters for single company
    public EmpWageBuilder(String companyName, int wagePerHour, int maxWorkingDays, int maxWorkingHours) {
        this.companyName = companyName;
        this.wagePerHour = wagePerHour;
        this.maxWorkingDays = maxWorkingDays;
        this.maxWorkingHours = maxWorkingHours;
        this.totalWage = 0;
        this.totalWorkingDays = 0;
        this.totalWorkingHours = 0;
        this.companyWages = new ArrayList<>();
        this.isCollectionMode = false;
    }
    
    // UC10: Constructor with no parameters for collection mode
    public EmpWageBuilder() {
        this.companyWages = new ArrayList<>();
        this.isCollectionMode = true;
        this.totalWage = 0;
        this.totalWorkingDays = 0;
        this.totalWorkingHours = 0;
    }
    
    // UC9: Getters for single company mode
    public String getCompanyName() {
        return companyName;
    }
    
    public int getWagePerHour() {
        return wagePerHour;
    }
    
    public int getMaxWorkingDays() {
        return maxWorkingDays;
    }
    
    public int getMaxWorkingHours() {
        return maxWorkingHours;
    }
    
    public int getTotalWage() {
        return totalWage;
    }
    
    public int getTotalWorkingDays() {
        return totalWorkingDays;
    }
    
    public int getTotalWorkingHours() {
        return totalWorkingHours;
    }
    
    // UC9: Setters for single company mode
    public void setTotalWage(int totalWage) {
        this.totalWage = totalWage;
    }
    
    public void setTotalWorkingDays(int totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }
    
    public void setTotalWorkingHours(int totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }
    
    // UC10: Add a company wage to the list
    public void addCompanyWage(CompanyEmpWage company) {
        companyWages.add(company);
    }
    
    // UC10: Get company wage by company name
    public CompanyEmpWage getCompanyWage(String companyName) {
        for (CompanyEmpWage company : companyWages) {
            if (company.getCompanyName().equalsIgnoreCase(companyName)) {
                return company;
            }
        }
        return null;
    }
    
    // UC10: Get all company wages
    public List<CompanyEmpWage> getAllCompanyWages() {
        return companyWages;
    }
    
    // UC10: Display all company wages
    public void displayAllCompanyWages() {
        System.out.println("\n========== All Company Wages (Managed by EmpWageBuilder) ==========");
        if (companyWages.isEmpty()) {
            System.out.println("No company wages available.");
        } else {
            for (CompanyEmpWage company : companyWages) {
                System.out.println("\nCompany: " + company.getCompanyName());
                System.out.println("Wage Per Hour: Rs " + company.getWagePerHour());
                System.out.println("Max Working Days: " + company.getMaxWorkingDays());
                System.out.println("Max Working Hours: " + company.getMaxWorkingHours());
                System.out.println("Total Wage: Rs " + company.getTotalWage());
                System.out.println("Total Working Days: " + company.getTotalWorkingDays());
                System.out.println("Total Working Hours: " + company.getTotalWorkingHours());
                System.out.println("-------------------------------------------------------------------");
            }
        }
        System.out.println("====================================================================\n");
    }
    
    // UC10: Get total companies managed
    public int getTotalCompanies() {
        return companyWages.size();
    }
    
    // UC10: Get total wage across all companies
    public int getTotalWageAcrossAllCompanies() {
        int totalWage = 0;
        for (CompanyEmpWage company : companyWages) {
            totalWage += company.getTotalWage();
        }
        return totalWage;
    }
    
    @Override
    public String toString() {
        if (isCollectionMode) {
            return "EmpWageBuilder{" +
                    "totalCompanies=" + companyWages.size() +
                    ", totalWageAcrossAllCompanies=" + getTotalWageAcrossAllCompanies() +
                    '}';
        } else {
            return "EmpWageBuilder{" +
                    "companyName='" + companyName + '\'' +
                    ", totalWage=" + totalWage +
                    ", totalWorkingDays=" + totalWorkingDays +
                    ", totalWorkingHours=" + totalWorkingHours +
                    '}';
        }
    }
}
