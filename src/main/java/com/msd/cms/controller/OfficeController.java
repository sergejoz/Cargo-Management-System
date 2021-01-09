package com.msd.cms.controller;

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
import java.util.List;

@Controller
@RequestMapping("/offices")
public class OfficeController  {
    private final OfficeService officeService;
    private final CompanyService companyService;
    private final EmployeeService employeeService;


    @Autowired
    public OfficeController(OfficeService officeService, CompanyService companyService, EmployeeService employeeService) {
        this.officeService = officeService;
        this.companyService=companyService;
        this.employeeService = employeeService;
    }


    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addOffice(Model model, @ModelAttribute(name = "office") Office office) {
        model.addAttribute("office", office);
        model.addAttribute("offices",officeService.findAllOffices());
        return "office/add";
    }



    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addOfficeConfirm(Model model,
                                   @Valid @ModelAttribute(name = "office") Office office, BindingResult bindingResult) {

        if(!bindingResult.hasErrors()) {
            this.officeService.createOffice(office);
            office.setCompany(companyService.findAll().get(0));
            model.addAttribute("office", office);
            model.addAttribute("offices", officeService.findAllOffices());
            return "office/all";
        }
        else {
            return "office/add";
        }
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public String allOffices(Model model) {
        model.addAttribute("offices",officeService.findAllOffices());
        return  "office/all";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteOffice(@PathVariable String id, Model model) {
        Office office = this.officeService.findById(id);
        model.addAttribute("model", office);
        return "office/delete";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteOfficeConfirm(@PathVariable String id,Model model) {
        this.officeService.deleteOffice(id);
        model.addAttribute("offices",officeService.findAllOffices());
        return "office/all";
    }

    @GetMapping("/employees/all/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String allEmployeesFromOffice(@PathVariable String id, Model model) {
        Office office = officeService.findById(id);
        model.addAttribute("model", employeeService.findEmployeesByOffice(office));
        return "office/listed-employees";
    }

    @GetMapping("/offices/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String edit(@PathVariable String id,Model model){
        Office office = this.officeService.findById(id);
        model.addAttribute("model", office);
        return "office/edit";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editOfficeConfirm(@PathVariable String id,Model model, @ModelAttribute(name = "office") Office office) {

        office.setId(id);
        this.officeService.updateOffice(office);
//        model.addAttribute("office",office);
        model.addAttribute("offices",officeService.findAllOffices());
        return  "office/listed-offices";
    }



    @GetMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public List<Office> fetchOffices() {
        List<Office> offices = this.officeService.findAllOffices();
        return offices;

    }
}
