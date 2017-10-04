package com.pt.app.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pt.app.dto.CustomerAddressDto;
import com.pt.app.dto.CustomerEquipmentsSlaDto;
import com.pt.app.model.Customer;

public interface CustomerService {
	public List<Customer> getCustomers();
	public List<CustomerAddressDto> getCustomersAndAddresses();
	public List<CustomerEquipmentsSlaDto> getEquipmentsAndSlaByCustomer(Customer customer);
	public void addNewCustomerAddress (CustomerAddressDto customerAddressDto);
	public void addNewEquipmentSla (CustomerEquipmentsSlaDto customerEquipmentsSlaDto);
	public void addNewToDelEquipmentSla (CustomerEquipmentsSlaDto customerEquipmentsSlaDto);
	public void mapJsonStringToCustomerAddressDto(ObjectNode json);
	public void mapJsonStringToEquipmentSlaDto(ObjectNode json);
	public void mapJsonStringToDelEquipmentSlaDto(ObjectNode json);
	public void deleteCustomer(Customer customer);
}
