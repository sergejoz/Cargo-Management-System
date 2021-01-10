package com.msd.cms.repository;

import com.msd.cms.entities.Company;
import com.msd.cms.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OfficeRepository extends JpaRepository<Office, String> {
	Optional<Office> findByAddress(String address);

	Set<Office> findOfficesByCompany(Company company);
}
