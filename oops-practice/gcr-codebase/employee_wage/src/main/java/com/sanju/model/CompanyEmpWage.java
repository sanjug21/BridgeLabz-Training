package com.sanju.model;

// UC10: CompanyEmpWage class to represent single company's wage details

public class CompanyEmpWage {
    // UC10: Instance variables for company parameters
    private String companyName;
    private int wagePerHour;
    private int maxWorkingDays;
    private int maxWorkingHours;
    
    // UC10: Instance variables to store computation results
    private int totalWage;
    private int totalWorkingDays;
    private int totalWorkingHours;
    
    // UC10: Constructor to initialize company parameters
    public CompanyEmpWage(String companyName, int wagePerHour, int maxWorkingDays, int maxWorkingHours) {
        this.companyName = companyName;
        this.wagePerHour = wagePerHour;
        this.maxWorkingDays = maxWorkingDays;
        this.maxWorkingHours = maxWorkingHours;
        this.totalWage = 0;
        this.totalWorkingDays = 0;
        this.totalWorkingHours = 0;
    }
    
    // Getters for company parameters
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
