package com.msd.cms.service;

import com.msd.cms.entities.Company;
import com.msd.cms.entities.Office;
import com.msd.cms.entities.Shipment;
import com.msd.cms.repository.CompanyRepository;
import com.msd.cms.repository.OfficeRepository;
import com.msd.cms.repository.ShipmentRepository;
import com.msd.cms.util.CalculateShipmentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	@Autowired
	private OfficeRepository officeRepository;

	@Override
	public Company createCompany(Company company) {
		return this.companyRepository.save(company);
	}

	@Override
	public double calculateIncome(LocalDate startDate, LocalDate endDate){
		List<Shipment> allShipments = this.shipmentRepository.findAll();
		double income = 0.0;
		for(Shipment shipment : allShipments) {
			if(shipment.getReceivedDate().isBefore(endDate) && shipment.getReceivedDate().isAfter(startDate)) {
				Optional<Office> office = this.officeRepository.findByAddress(shipment.getAddress());
				if(!office.isPresent()) {
					income += 2;
				}
				income += CalculateShipmentUtil.calculateShipmentPrice(shipment,officeRepository.findAll());
			}
		}
		return income;
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Company findById(String id) {
		return companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}

	@Override
	public void deleteCompanyById(String id) {
		companyRepository.deleteById(id);
	}

	@Override
	public Company updateCompany(Company company) {
		return companyRepository.save(company);
	}
}
