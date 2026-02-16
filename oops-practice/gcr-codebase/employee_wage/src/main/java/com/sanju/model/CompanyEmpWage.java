package com.sanju.model;

// UC7: Model class to represent Company Employee Wage with Class Variables

public class CompanyEmpWage {
    // UC7: Class Variables for company parameters
    private final String companyName;
    private final int wagePerHour;
    private final int maxWorkingDays;
    private final int maxWorkingHours;
    
    // UC7: Class Variables to store computation results
    private int totalWage;
    private int totalWorkingDays;
    private int totalWorkingHours;
    
    // UC7: Constructor to initialize company wage parameters
    public CompanyEmpWage(String companyName, int wagePerHour, int maxWorkingDays, int maxWorkingHours) {
        this.companyName = companyName;
        this.wagePerHour = wagePerHour;
        this.maxWorkingDays = maxWorkingDays;
        this.maxWorkingHours = maxWorkingHours;
        this.totalWage = 0;
        this.totalWorkingDays = 0;
        this.totalWorkingHours = 0;
    }
    
    // Getters for class variables
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
    
    // Setters for computation results
    public void setTotalWage(int totalWage) {
        this.totalWage = totalWage;
    }
    
    public void setTotalWorkingDays(int totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }
    
    public void setTotalWorkingHours(int totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }
    
    @Override
    public String toString() {
        return "CompanyEmpWage{" +
                "companyName='" + companyName + '\'' +
                ", wagePerHour=" + wagePerHour +
                ", maxWorkingDays=" + maxWorkingDays +
                ", maxWorkingHours=" + maxWorkingHours +
                ", totalWage=" + totalWage +
                ", totalWorkingDays=" + totalWorkingDays +
                ", totalWorkingHours=" + totalWorkingHours +
                '}';
    }
}
