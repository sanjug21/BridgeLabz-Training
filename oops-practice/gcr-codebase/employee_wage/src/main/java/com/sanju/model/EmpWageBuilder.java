package com.sanju.model;

// UC9: EmpWageBuilder class with Instance Variables
 
public class EmpWageBuilder {
    // UC9: Instance Variables for company parameters
    private String companyName;
    private int wagePerHour;
    private int maxWorkingDays;
    private int maxWorkingHours;
    
    // UC9: Instance Variables to store computed results
    private int totalWage;
    private int totalWorkingDays;
    private int totalWorkingHours;
    
    // UC9: Constructor with instance variables
    public EmpWageBuilder(String companyName, int wagePerHour, int maxWorkingDays, int maxWorkingHours) {
        this.companyName = companyName;
        this.wagePerHour = wagePerHour;
        this.maxWorkingDays = maxWorkingDays;
        this.maxWorkingHours = maxWorkingHours;
        this.totalWage = 0;
        this.totalWorkingDays = 0;
        this.totalWorkingHours = 0;
    }
    
    // Getters for instance variables
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
    
    // Setters for computed results
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
        return "EmpWageBuilder{" +
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
