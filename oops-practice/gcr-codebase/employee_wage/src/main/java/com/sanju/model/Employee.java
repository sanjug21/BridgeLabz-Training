package com.sanju.model;

public class Employee {
    private String name;
    private int employeeId;
    private int wagePerHour;
    private boolean isPresent;
    private boolean isPartTime; // UC3: Track if employee is part-time

    public Employee(String name, int employeeId, int wagePerHour) {
        this.name = name;
        this.employeeId = employeeId;
        this.wagePerHour = wagePerHour;
        this.isPresent = false;
        this.isPartTime = false;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getWagePerHour() {
        return wagePerHour;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
    
    // UC3: Part-time getter and setter
    public boolean isPartTime() {
        return isPartTime;
    }

    public void setPartTime(boolean partTime) {
        isPartTime = partTime;
    }
}
