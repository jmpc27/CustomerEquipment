package com.pt.app.dao;

import java.util.List;

import com.pt.app.model.Address;
import com.pt.app.model.Customer;
import com.pt.app.model.Equipment;
import com.pt.app.model.Sla;

public interface CustomerDao {
	public List<Customer> getCustomers();
	public void deleteCustomer(Customer customer);
	public void addNewCustomerAddress(Customer customer, Address address);
	public void addNewEquipmentSla(Equipment equipment, Sla sla);
	public void deleteEquipmentSla(Equipment equipment);
	public Customer getCustomerById(Integer id);
}
