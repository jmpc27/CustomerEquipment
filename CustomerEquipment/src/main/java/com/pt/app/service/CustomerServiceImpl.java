package com.pt.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pt.app.dao.CustomerDao;
import com.pt.app.dto.CustomerAddressDto;
import com.pt.app.dto.CustomerEquipmentsSlaDto;
import com.pt.app.model.Address;
import com.pt.app.model.Customer;
import com.pt.app.model.Equipment;
import com.pt.app.model.Sla;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public List<Customer> getCustomers() {
		return this.customerDao.getCustomers();
	}

	@Override
	public List<CustomerAddressDto> getCustomersAndAddresses() {
		List<CustomerAddressDto> ca = new ArrayList<CustomerAddressDto>();
		for (Customer c: this.customerDao.getCustomers()){
			Set<Address> customerAddresses = c.getAddresses();
			for (Address a : customerAddresses){
				ca.add(new CustomerAddressDto(c.getIdCustomer(), c.getName(), c.getContactDetails(), c.getContact(), a.getPrimaryAddress(), a.getSecondaryAddress(), a.getIdAddress()));
			}
		}
		return ca;
	}

	@Override
	public List<CustomerEquipmentsSlaDto> getEquipmentsAndSlaByCustomer(Customer customer) {
		List<CustomerEquipmentsSlaDto> ce = new ArrayList<CustomerEquipmentsSlaDto>();
		for (Customer c: this.customerDao.getCustomers()){
			if(c.getIdCustomer().equals(customer.getIdCustomer())){
				Set<Equipment> customerEquipments = c.getEquipments();
				for (Equipment e : customerEquipments){
					ce.add(new CustomerEquipmentsSlaDto(e.getIdEquipment(), e.getSerialNumber(), e.getVendor(), e.getModel(), e.getInstallationAddress(), e.getSoftwareVersion(), e.getSoftwareLastVersion(), e.getSoftwareUpdate(), e.getUpdateDate(), e.getSla().getSla(), e.getSla().getContractStart(), e.getSla().getContractEnd(), e.getSla().getInstallationDate(), c.getIdCustomer(), e.getSla().getIdSla()));
				}
			}
		}
		
		return ce;
	}

	@Override
	public void deleteCustomer(Customer customer) {
		this.customerDao.deleteCustomer(customer);
	}
	

	@Override
	public void mapJsonStringToCustomerAddressDto(ObjectNode json) {
		CustomerAddressDto customerAddressDto = new CustomerAddressDto();
		if(!json.get("idCustomer").asText().isEmpty() && !json.get("idAddress").asText().isEmpty()){
			customerAddressDto.setIdCustomer(json.get("idCustomer").asInt());
			customerAddressDto.setIdAddress(json.get("idAddress").asInt());
		}		
		customerAddressDto.setName(json.get("name").asText());
		customerAddressDto.setContactDetails(json.get("contactDetails").asText());
		customerAddressDto.setContact(json.get("contact").asText());
		customerAddressDto.setPrimaryAddress(json.get("primaryAddress").asText());
		customerAddressDto.setSecondaryAddress(json.get("secondaryAddress").asText());
		this.addNewCustomerAddress(customerAddressDto);		
	}

	@Override
	public void addNewCustomerAddress(CustomerAddressDto customerAddressDto) {
		Customer c = new Customer();
		Address a = new Address();
		if(customerAddressDto.getIdCustomer()!=null && customerAddressDto.getIdAddress()!=null){
			c.setIdCustomer(customerAddressDto.getIdCustomer());
			a.setIdAddress(customerAddressDto.getIdAddress());
		}
		c.setName(customerAddressDto.getName());
		c.setContactDetails(customerAddressDto.getContactDetails());
		c.setContact(customerAddressDto.getContact());
		a.setPrimaryAddress(customerAddressDto.getPrimaryAddress());
		a.setSecondaryAddress(customerAddressDto.getSecondaryAddress());
		a.setCustomer(c);
		Set<Address> addresses = new HashSet<Address>();
		addresses.add(a);
		c.setAddresses(addresses);
		
		this.customerDao.addNewCustomerAddress(c, a);
	}


	@Override
	public void mapJsonStringToEquipmentSlaDto(ObjectNode json) {
		
		CustomerEquipmentsSlaDto customerEquipmentsSlaDto = new CustomerEquipmentsSlaDto();
		if(!json.get("idEquipment").asText().isEmpty() && !json.get("idSla").asText().isEmpty()){
			customerEquipmentsSlaDto.setIdEquipment(json.get("idEquipment").asInt());
			customerEquipmentsSlaDto.setIdSla(json.get("idSla").asInt());
		}	
		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			customerEquipmentsSlaDto.setVendor(json.get("vendor").asText());
			customerEquipmentsSlaDto.setModel(json.get("model").asText());
			customerEquipmentsSlaDto.setSerialNumber(json.get("serialNumber").asText());
			customerEquipmentsSlaDto.setContractStart(sdf.parse(json.get("contractStart").asText()));
			customerEquipmentsSlaDto.setContractEnd(sdf.parse(json.get("contractEnd").asText()));
			customerEquipmentsSlaDto.setInstallationAddress(json.get("installationAddress").asText());
			customerEquipmentsSlaDto.setInstallationDate(sdf.parse(json.get("installationDate").asText()));
			customerEquipmentsSlaDto.setSla(json.get("sla").asText());
			customerEquipmentsSlaDto.setSoftwareLastVersion(json.get("softwareLastVersion").asText());
			customerEquipmentsSlaDto.setSoftwareUpdate(json.get("softwareUpdate").asBoolean());
			customerEquipmentsSlaDto.setSoftwareVersion(json.get("softwareVersion").asText());
			customerEquipmentsSlaDto.setUpdateDate(sdf.parse(json.get("updateDate").asText()));
			customerEquipmentsSlaDto.setIdCustomer(json.get("idCustomer").asInt());
		} catch (ParseException e) {
			System.out.println(e);
		}
		
		this.addNewEquipmentSla(customerEquipmentsSlaDto);
		
	}

	public void addNewEquipmentSla(CustomerEquipmentsSlaDto customerEquipmentsSlaDto) {
		Equipment e = new Equipment();
		Sla s = new Sla();
		if(customerEquipmentsSlaDto.getIdEquipment()!=null && customerEquipmentsSlaDto.getIdSla()!=null){
			e.setIdEquipment(customerEquipmentsSlaDto.getIdEquipment());
			s.setIdSla(customerEquipmentsSlaDto.getIdSla());
		}
		
		Customer c = this.customerDao.getCustomerById(customerEquipmentsSlaDto.getIdCustomer());
		
		e.setInstallationAddress(customerEquipmentsSlaDto.getInstallationAddress());
		e.setModel(customerEquipmentsSlaDto.getModel());
		e.setSerialNumber(customerEquipmentsSlaDto.getModel());
		e.setSoftwareLastVersion(customerEquipmentsSlaDto.getSoftwareLastVersion());
		e.setUpdateDate(customerEquipmentsSlaDto.getUpdateDate());
		e.setSoftwareVersion(customerEquipmentsSlaDto.getSoftwareVersion());
		e.setVendor(customerEquipmentsSlaDto.getVendor());
		e.setCustomer(c);
		e.setSoftwareUpdate(customerEquipmentsSlaDto.getSoftwareUpdate());
		
		Set<Equipment> equipments = new HashSet<Equipment>();
		equipments.add(e);
		s.setEquipments(equipments);
		s.setSla(customerEquipmentsSlaDto.getSla());
		c.setEquipments(equipments);
		s.setContractEnd(customerEquipmentsSlaDto.getContractEnd());
		s.setContractStart(customerEquipmentsSlaDto.getContractStart());
		s.setInstallationDate(customerEquipmentsSlaDto.getInstallationDate());
		e.setSla(s);
		
		this.customerDao.addNewEquipmentSla(e, s);
		
	}

	@Override
	public void mapJsonStringToDelEquipmentSlaDto(ObjectNode json) {

		CustomerEquipmentsSlaDto customerEquipmentsSlaDto = new CustomerEquipmentsSlaDto();
				
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			customerEquipmentsSlaDto.setIdEquipment(json.get("idEquipment").asInt());
			customerEquipmentsSlaDto.setIdSla(json.get("idSla").asInt());
			customerEquipmentsSlaDto.setVendor(json.get("vendor").asText());
			customerEquipmentsSlaDto.setModel(json.get("model").asText());
			customerEquipmentsSlaDto.setSerialNumber(json.get("serialNumber").asText());
			customerEquipmentsSlaDto.setContractStart(sdf.parse(json.get("contractStart").asText()));
			customerEquipmentsSlaDto.setContractEnd(sdf.parse(json.get("contractEnd").asText()));
			customerEquipmentsSlaDto.setInstallationAddress(json.get("installationAddress").asText());
			customerEquipmentsSlaDto.setInstallationDate(sdf.parse(json.get("installationDate").asText()));
			customerEquipmentsSlaDto.setSla(json.get("sla").asText());
			customerEquipmentsSlaDto.setSoftwareLastVersion(json.get("softwareLastVersion").asText());
			customerEquipmentsSlaDto.setSoftwareUpdate(json.get("softwareUpdate").asBoolean());
			customerEquipmentsSlaDto.setSoftwareVersion(json.get("softwareVersion").asText());
			customerEquipmentsSlaDto.setUpdateDate(sdf.parse(json.get("updateDate").asText()));
			customerEquipmentsSlaDto.setIdCustomer(json.get("idCustomer").asInt());
		} catch (ParseException e) {
			System.out.println(e);
		}
		
		this.addNewToDelEquipmentSla(customerEquipmentsSlaDto);
		
	}

	@Override
	public void addNewToDelEquipmentSla(CustomerEquipmentsSlaDto customerEquipmentsSlaDto) {
		Equipment e = new Equipment();
		Sla s = new Sla();
		
//		Customer c = this.customerDao.getCustomerById(customerEquipmentsSlaDto.getIdCustomer());
		e.setIdEquipment(customerEquipmentsSlaDto.getIdEquipment());
		e.setInstallationAddress(customerEquipmentsSlaDto.getInstallationAddress());
		e.setModel(customerEquipmentsSlaDto.getModel());
		e.setSerialNumber(customerEquipmentsSlaDto.getModel());
		e.setSoftwareLastVersion(customerEquipmentsSlaDto.getSoftwareLastVersion());
		e.setUpdateDate(customerEquipmentsSlaDto.getUpdateDate());
		e.setSoftwareVersion(customerEquipmentsSlaDto.getSoftwareVersion());
		e.setVendor(customerEquipmentsSlaDto.getVendor());
//		e.setCustomer(c);
		e.setSoftwareUpdate(customerEquipmentsSlaDto.getSoftwareUpdate());
		
		Set<Equipment> equipments = new HashSet<Equipment>();
		equipments.add(e);
		s.setIdSla(customerEquipmentsSlaDto.getIdSla());
		s.setEquipments(equipments);
		s.setSla(customerEquipmentsSlaDto.getSla());
//		c.setEquipments(equipments);
		s.setContractEnd(customerEquipmentsSlaDto.getContractEnd());
		s.setContractStart(customerEquipmentsSlaDto.getContractStart());
		s.setInstallationDate(customerEquipmentsSlaDto.getInstallationDate());
		e.setSla(s);
		
		this.customerDao.deleteEquipmentSla(e);
		
	}

	
	
	
}
