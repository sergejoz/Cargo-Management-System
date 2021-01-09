package com.msd.cms.service;

import com.msd.cms.entities.Customer;
import com.msd.cms.entities.Employee;
import com.msd.cms.entities.Shipment;
import com.msd.cms.models.binding.ShipmentDTO;

import java.util.List;

public interface ShipmentService {

	List<Shipment> findAllShipments();

	Shipment findShipmentById(String id);

	Shipment createShipment(ShipmentDTO shipment);

	Shipment updateShipment(Shipment shipment);

	void deleteShipment(String id);

	List<Shipment> getRegisteredShipments();

	List<Shipment> findCheckedShipments();

	List<Shipment> findUncheckedShipments();

	List<Shipment> findDeliveredShipments();


	List<Shipment> findUndeliveredShipmentsByCustomer(Customer customer,boolean isDelivered);

	List<Shipment> findDeliveredShipmentsByCustomer(Customer customer,boolean isDelivered);

	List<Shipment> findUndeliveredCheckedShipments();

	List<Shipment> findSentShipmentsByCustomer(Customer customer);


	List<Shipment> findRegisteredShipmentsByEmployee(Employee employee);
}
