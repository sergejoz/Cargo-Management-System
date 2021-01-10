package com.msd.cms.controller;

import com.msd.cms.entities.Company;
import com.msd.cms.entities.Customer;
import com.msd.cms.entities.Office;
import com.msd.cms.service.CompanyService;
import com.msd.cms.service.EmployeeService;
import com.msd.cms.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/companies/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String edit(@PathVariable String id,Model model){
        Company company = this.companyService.findById(id);
        model.addAttribute("company", company);
        return "company/edit";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCompanyConfirm(@PathVariable String id,Model model, @ModelAttribute(name = "companies") Company company) {
        company.setId(id);
        this.companyService.updateCompany(company);
        model.addAttribute("companies", companyService.findAll());
        return "company/all";
    }

    @GetMapping("/offices/all/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String allEmployeesFromOffice(@PathVariable String id, Model model) {
        Company company = companyService.findById(id);
        model.addAttribute("offices", officeService.findOfficesByCompany(company));
        return "company/listed-offices";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCompany (Model model, @ModelAttribute(name = "company") Company company) {
        model.addAttribute("company", company);
        model.addAttribute("companies", companyService.findAll());
        return "company/add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addOfficeConfirm(Model model,
                                   @Valid @ModelAttribute(name = "company") Company company, BindingResult bindingResult) {

        if(!bindingResult.hasErrors()) {
            this.companyService.createCompany(company);
            model.addAttribute("company", company);
            model.addAttribute("companies", companyService.findAll());
            return "company/all";
        }
        else {
            return "company/add";
        }
    }
}
