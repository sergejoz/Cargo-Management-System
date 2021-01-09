package com.msd.cms.mapper;

import com.msd.cms.entities.Customer;
import com.msd.cms.entities.Shipment;
import com.msd.cms.models.binding.ShipmentDTO;
import com.msd.cms.service.CustomerService;
import com.msd.cms.service.ShipmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShipmentMapper {

    @Autowired
    ShipmentService shipmentService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ModelMapper modelMapper;

    public Shipment mapShipmentDTOToShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = new Shipment();

        shipment.setAddress(shipmentDTO.getAddress());
        shipment.setPrice(shipmentDTO.getPrice());
        shipment.setWeight(shipmentDTO.getWeight());
        shipment.setReceivedDate(null);

        Customer sender = customerService.findCustomerById(shipmentDTO.getSenderId());
        shipment.setSender(sender);

        Customer recipient = customerService.findCustomerById(shipmentDTO.getRecipientId());
        shipment.setRecipient(recipient);

        shipment.setEmployee(null);
        shipment.setStatus(false);
        shipment.setDelivered(false);
        shipment.setDriver(null);


        return shipment;
    }
}
