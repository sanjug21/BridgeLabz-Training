package com.sanju.model;

import java.util.List;

/**
 * UC11: Interface for managing Employee Wage of multiple companies
 * Provides contract for managing collection of CompanyEmpWage objects
 */
public interface IEmpWageBuilder {
  
    void addCompanyWage(CompanyEmpWage company);
   
    CompanyEmpWage getCompanyWage(String companyName);
    
    
    List<CompanyEmpWage> getAllCompanyWages();
    
    
    void displayAllCompanyWages();
    
    
    int getTotalCompanies();
    
   
    int getTotalWageAcrossAllCompanies();
}
