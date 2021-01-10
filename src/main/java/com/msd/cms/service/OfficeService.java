package com.msd.cms.service;


import com.msd.cms.entities.Company;
import com.msd.cms.entities.Office;

import java.util.List;
import java.util.Set;

public interface OfficeService {

	List<Office> findAllOffices();

	Office findById(String id);

	Office createOffice(Office office);

	void deleteOffice(String id);

	Office updateAddress(Office office, String address);

	Office updateOffice(Office office);

	Set<Office> findOfficesByCompany(Company company);
}
