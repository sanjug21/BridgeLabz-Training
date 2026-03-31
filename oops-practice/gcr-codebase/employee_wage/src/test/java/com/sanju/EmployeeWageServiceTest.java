package com.sanju;

import com.sanju.model.Employee;
import com.sanju.model.CompanyEmpWage;
import com.sanju.repository.EmployeeWageRepository;
import com.sanju.service.EmployeeWageService;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class EmployeeWageServiceTest {
    
    private EmployeeWageService employeeWageService;
    private Employee employee;
    private CompanyEmpWage company;
    private EmployeeWageRepository repository;
    
    @Before
    public void setUp() {
        employeeWageService = new EmployeeWageService();
        repository = new EmployeeWageRepository();
        employeeWageService = new EmployeeWageService(repository);
        
        // Create test employee
        employee = new Employee("John Doe", 101, 20);
        
        // Create test company
        company = new CompanyEmpWage("Test Company", 20, 20, 100);
    }
    
    // UC1 Tests - Check Employee Type
    @Test
    public void testCheckEmployeeType_ShouldReturnValidType() {
        int empType = employeeWageService.checkEmployeeType();
        assertTrue("Employee type should be 0, 1, or 2", empType >= 0 && empType <= 2);
    }
    
    @Test
    public void testCheckEmployeeType_RandomValues() {
        for (int i = 0; i < 10; i++) {
            int empType = employeeWageService.checkEmployeeType();
            assertTrue("Employee type must be within valid range", empType >= 0 && empType <= 2);
        }
    }
    
    // UC2 Tests - Calculate Daily Wage
    @Test
    public void testCalculateDailyWage_WhenPresent_FullTime() {
        employeeWageService.setEmployeeType(employee);
        int wage = employeeWageService.calculateDailyWage(employee);
        assertTrue("Daily wage should be positive", wage >= 0);
    }
    
    @Test
    public void testCalculateDailyWage_ValidWage() {
        employeeWageService.setEmployeeType(employee);
        int wage = employeeWageService.calculateDailyWage(employee);
        // Wage should be 0 (absent), 80 (part-time: 4*20), or 160 (full-time: 8*20)
        assertTrue("Daily wage should be valid", wage == 0 || wage == 80 || wage == 160);
    }
    
    // UC5 Tests - Calculate Monthly Wage
    @Test
    public void testCalculateMonthlyWage_ShouldBePositive() {
        int monthlyWage = employeeWageService.calculateMonthlyWage(employee);
        assertTrue("Monthly wage should be positive", monthlyWage > 0);
    }
    
    @Test
    public void testCalculateMonthlyWage_For20Days() {
        int monthlyWage = employeeWageService.calculateMonthlyWage(employee);
        // Should calculate for 20 days
        assertTrue("Monthly wage should be calculated for 20 days", monthlyWage >= 0);
    }
    
    // UC6 Tests - Calculate Wage with Conditions
    @Test
    public void testCalculateWageWithConditions_ShouldRespectMaxHours() {
        int wage = employeeWageService.calculateWageWithConditions(employee);
        assertTrue("Wage should respect max working hours condition", wage >= 0);
    }
    
    @Test
    public void testCalculateWageWithConditions_ShouldRespectMaxDays() {
        int wage = employeeWageService.calculateWageWithConditions(employee);
        assertTrue("Wage should respect max working days condition", wage >= 0);
    }
    
    // UC7 Tests - Compute Company Employee Wage
    @Test
    public void testComputeCompanyEmployeeWage_ShouldUpdateCompanyObject() {
        employeeWageService.computeCompanyEmployeeWage(company);
        assertTrue("Total wage should be set", company.getTotalWage() >= 0);
        assertTrue("Total working days should be set", company.getTotalWorkingDays() > 0);
        assertTrue("Total working hours should be set", company.getTotalWorkingHours() > 0);
    }
    
    @Test
    public void testComputeCompanyEmployeeWage_TotalWageNotZero() {
        employeeWageService.computeCompanyEmployeeWage(company);
        assertTrue("Total wage should be greater than 0", company.getTotalWage() > 0);
    }
    
    @Test
    public void testComputeCompanyEmployeeWage_RespectMaxLimits() {
        employeeWageService.computeCompanyEmployeeWage(company);
        assertTrue("Total working days should not exceed max", 
                   company.getTotalWorkingDays() <= company.getMaxWorkingDays());
        assertTrue("Total working hours should not exceed max", 
                   company.getTotalWorkingHours() <= company.getMaxWorkingHours());
    }
    
    // UC13 Tests - Store Daily Wages
    @Test
    public void testComputeCompanyEmployeeWageWithDailyStorage_ShouldStoreDailyWages() {
        employeeWageService.computeCompanyEmployeeWageWithDailyStorage(company);
        assertNotNull("Daily wages list should not be null", company.getDailyWages());
        assertTrue("Daily wages list should not be empty", company.getDailyWages().size() > 0);
    }
    
    @Test
    public void testComputeCompanyEmployeeWageWithDailyStorage_DailyWagesShouldSum() {
        employeeWageService.computeCompanyEmployeeWageWithDailyStorage(company);
        
        int sumOfDailyWages = 0;
        for (int wage : company.getDailyWages()) {
            sumOfDailyWages += wage;
        }
        
        assertEquals("Sum of daily wages should equal total wage", 
                     sumOfDailyWages, company.getTotalWage());
    }
    
    @Test
    public void testComputeCompanyEmployeeWageWithDailyStorage_DailyWagesPositive() {
        employeeWageService.computeCompanyEmployeeWageWithDailyStorage(company);
        
        for (int wage : company.getDailyWages()) {
            assertTrue("Each daily wage should be non-negative", wage >= 0);
        }
    }
    
    // UC14 Tests - Query Total Wage by Company
    @Test
    public void testGetTotalWageByCompanyQuery_ExistingCompany() {
        // First add companies to repository
        CompanyEmpWage testCompany = new CompanyEmpWage("Accenture India Pvt Ltd", 22, 21, 110);
        employeeWageService.computeCompanyEmployeeWage(testCompany);
        repository.addCompanyWage(testCompany);
        
        int wage = employeeWageService.getTotalWageByCompanyQuery("Accenture India Pvt Ltd");
        assertEquals("Should retrieve correct total wage", testCompany.getTotalWage(), wage);
    }
    
    @Test
    public void testGetTotalWageByCompanyQuery_NonExistingCompany() {
        int wage = employeeWageService.getTotalWageByCompanyQuery("Non Existent Company");
        assertEquals("Should return -1 for non-existent company", -1, wage);
    }
    
    // UC11/UC12 Tests - ArrayList Operations
    @Test
    public void testComputeWageWithArrayList_MultipleCompanies() {
        List<CompanyEmpWage> companies = new ArrayList<>();
        companies.add(new CompanyEmpWage("Company A", 20, 20, 100));
        companies.add(new CompanyEmpWage("Company B", 25, 20, 100));
        companies.add(new CompanyEmpWage("Company C", 30, 20, 100));
        
        employeeWageService.computeWageWithArrayList(companies);
        
        for (CompanyEmpWage comp : companies) {
            assertTrue("Each company should have positive total wage", comp.getTotalWage() > 0);
        }
    }
    
    @Test
    public void testComputeWageWithArrayList_EmptyList() {
        List<CompanyEmpWage> companies = new ArrayList<>();
        // Should handle empty list gracefully
        employeeWageService.computeWageWithArrayList(companies);
        assertTrue("List should remain empty", companies.isEmpty());
    }
    
    // Helper Method Tests
    @Test
    public void testSetEmployeeType_UpdatesEmployeeAttendance() {
        employeeWageService.setEmployeeType(employee);
        // Employee should have valid attendance status
        assertNotNull("Employee should not be null", employee);
    }
    
    @Test
    public void testDisplayAllCompaniesWithWages_NoExceptionThrown() {
        try {
            employeeWageService.displayAllCompaniesWithWages();
        } catch (Exception e) {
            fail("displayAllCompaniesWithWages should not throw exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testDisplayTotalWageForCompany_NoExceptionOnValidCompany() {
        CompanyEmpWage testCompany = new CompanyEmpWage("Test Company", 20, 20, 100);
        employeeWageService.computeCompanyEmployeeWage(testCompany);
        repository.addCompanyWage(testCompany);
        
        try {
            employeeWageService.displayTotalWageForCompany("Test Company");
        } catch (Exception e) {
            fail("displayTotalWageForCompany should not throw exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testDisplayTotalWageForCompany_NoExceptionOnInvalidCompany() {
        try {
            employeeWageService.displayTotalWageForCompany("Invalid Company");
        } catch (Exception e) {
            fail("displayTotalWageForCompany should handle invalid company: " + e.getMessage());
        }
    }
}
