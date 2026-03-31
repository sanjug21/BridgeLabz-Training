package com.sanju.model;

// UC10: CompanyEmpWage class to represent single company's wage details

import java.util.ArrayList;
import java.util.List;

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
    
    // UC13: Store daily wages along with total wage
    private List<Integer> dailyWages;
    
    // UC10: Constructor to initialize company parameters
    public CompanyEmpWage(String companyName, int wagePerHour, int maxWorkingDays, int maxWorkingHours) {
        this.companyName = companyName;
        this.wagePerHour = wagePerHour;
        this.maxWorkingDays = maxWorkingDays;
        this.maxWorkingHours = maxWorkingHours;
        this.totalWage = 0;
        this.totalWorkingDays = 0;
        this.totalWorkingHours = 0;
        // UC13: Initialize daily wages list
        this.dailyWages = new ArrayList<>();
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
    
    // UC13: Getters and Setters for daily wages
    public List<Integer> getDailyWages() {
        return dailyWages;
    }
    
    public void setDailyWages(List<Integer> dailyWages) {
        this.dailyWages = dailyWages;
    }
    
    // UC13: Add a single daily wage to the list
    public void addDailyWage(int wage) {
        this.dailyWages.add(wage);
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
                ", dailyWages=" + dailyWages +
                '}';
    }
}
