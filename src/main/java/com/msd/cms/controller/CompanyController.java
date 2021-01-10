package com.msd.cms.controller;

import com.msd.cms.entities.Company;
import com.msd.cms.entities.Customer;
import com.msd.cms.service.CompanyService;
import com.msd.cms.service.EmployeeService;
import com.msd.cms.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final OfficeService officeService;
    private final CompanyService companyService;
    private final EmployeeService employeeService;


    @Autowired
    public CompanyController(OfficeService officeService, CompanyService companyService, EmployeeService employeeService) {
        this.officeService = officeService;
        this.companyService=companyService;
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public String allCompanies(Model model) {
        model.addAttribute("companies",companyService.findAll());
        return  "company/all";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCustomer(@PathVariable String id, Model model) {
        companyService.deleteCompanyById(id);
        model.addAttribute("companies", companyService.findAll());
        return "company/all";
    }
}
