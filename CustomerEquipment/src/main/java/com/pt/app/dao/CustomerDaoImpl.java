package com.pt.app.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pt.app.model.Address;
import com.pt.app.model.Customer;
import com.pt.app.model.Equipment;
import com.pt.app.model.Sla;

@Repository
@Transactional(readOnly = false)
public class CustomerDaoImpl implements CustomerDao{

	@Autowired
	private SessionFactory sessionFactory;
		
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Customer> customerList = session.createQuery("from Customer").list();
		return customerList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Customer> customerList = session.createQuery("from Customer").list();
		for (Customer c: customerList){
			if (c.getIdCustomer()==customer.getIdCustomer()){
				session.delete(c);
				session.getTransaction().commit();
				break;
			}
		}
		session.close();
	}

	@Override
	public void addNewCustomerAddress(Customer customer, Address address) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(customer);
		session.saveOrUpdate(address);
		session.getTransaction().commit();
		session.close();		
	}

	@Override
	public Customer getCustomerById(Integer id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Customer c = (Customer) session.createQuery("from Customer where idCustomer = " + id).list().get(0);
		session.close();
		return c;
	}

	@Override
	public void addNewEquipmentSla(Equipment equipment, Sla sla) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(equipment);
		session.saveOrUpdate(sla);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteEquipmentSla(Equipment equipment) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(equipment);
		session.getTransaction().commit();
		session.close();		
	}
	
	
	
	

}
