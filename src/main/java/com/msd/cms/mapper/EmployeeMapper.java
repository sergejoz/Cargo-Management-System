package com.msd.cms.mapper;

import com.msd.cms.entities.Employee;
import com.msd.cms.entities.Office;
import com.msd.cms.models.binding.EmployeeDTO;
import com.msd.cms.service.OfficeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {


    @Autowired
    OfficeService officeService;

    @Autowired
    ModelMapper modelMapper;

    public Employee mapEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeType(employeeDTO.getEmployeeType());
        employee.setName(employeeDTO.getName());
        employee.setUsername(employeeDTO.getUsername());
        employee.setPassword(employeeDTO.getPassword());


        Office office = officeService.findById(employeeDTO.getOfficeId());
        employee.setOffice(office);

       employee.setAuthorities(employeeDTO.getAuthorities());


        return employee;
    }
}
