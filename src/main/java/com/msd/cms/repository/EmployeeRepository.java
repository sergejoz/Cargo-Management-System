package com.msd.cms.repository;

import com.msd.cms.entities.Employee;
import com.msd.cms.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByUsername(String username);

    Set<Employee> findEmployeesByOffice(Office office);
}
