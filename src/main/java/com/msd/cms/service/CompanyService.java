package com.msd.cms.service;

import com.msd.cms.entities.Company;
import com.msd.cms.entities.Office;

import java.time.LocalDate;
import java.util.List;

public interface CompanyService {
	Company createCompany(Company company);
	double calculateIncome(LocalDate startDate, LocalDate endDate);
	List<Company> findAll();
	Company findById(String id);
	void deleteCompanyById(String id);
	Company updateCompany(Company company);
}
