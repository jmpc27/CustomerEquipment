package com.test.customer.dataAccess;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.pt.app.model.Address;
import com.pt.app.model.Customer;
import com.pt.app.model.Equipment;
import org.springframework.context.annotation.FilterType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/application-config.xml",
		"file:src/main/webapp/WEB-INF/mvc-config.xml"})

//@EnableWebMvc 
@Configuration
@ComponentScan(basePackages = { "com.pt.app" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
public class TestRetrieveDataFromDataBase {
	
//	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void retrieveData(){
		
		
//		***********************************************
//		retrieve all data form database
//		***********************************************		
		Session  session = sessionFactory.openSession();
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Customer> customerList = session.createQuery("from Customer").list();
		for(Customer c: customerList){
			
			System.out.println("************* Customer info with id:" + c.getIdCustomer() + "*******************");
			
			System.out.println(c.getName());
			System.out.println(c.getContact());
			System.out.println(c.getContactDetails());
			
			System.out.println("######### Customer Address ##############");
			Set<Address> customerAddresses = c.getAddresses();
			for (Address a : customerAddresses){
				System.out.println(a.getPrimaryAddress());
				System.out.println(a.getSecondaryAddress());
			}


			System.out.println("######### Customer Equipment ##############");
			Set<Equipment> customerEquipments = c.getEquipments();
			for (Equipment ce : customerEquipments){
				System.out.println(ce.getInstallationAddress());
				System.out.println(ce.getModel());
				System.out.println(ce.getSerialNumber());
				System.out.println(ce.getSoftwareLastVersion());
				System.out.println(ce.getSoftwareVersion());
				System.out.println(ce.getVendor());
				System.out.println("######### Customer Equipment SLA ##############");
				System.out.println(ce.getSla().getSla());
				System.out.println(ce.getSla().getContractStart());
				System.out.println(ce.getSla().getContractEnd());
				System.out.println(ce.getSla().getInstallationDate());
				
			}
			
			System.out.println("**********************************************************************************");
			
		}
		
		session.close();
	}

}
