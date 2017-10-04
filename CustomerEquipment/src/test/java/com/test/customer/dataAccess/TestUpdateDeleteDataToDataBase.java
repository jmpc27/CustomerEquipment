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
public class TestUpdateDeleteDataToDataBase {
	
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
		
		for (Customer c: customerList){
			
//			**************** Test case for updating a record as their relationships ***************
//			if (c.getIdCustomer() == 6){
//				Set<Address> addresses = c.getAddresses();
//				for (Address a:addresses){
//					a.setPrimaryAddress("Rua Mario Miguel de Sousa Rama 2870-225 Montijo");
//					updateRecord(a, session);
//				}
//				c.setContact("111662444");
//				updateRecord(c, session);
//			}
//			****************************************************************************************
			
//			**************** Test case for deleting a record and their relationships ***************
			if (c.getIdCustomer() == 31){
				session.delete(c);
			}
//			****************************************************************************************
		}
		
		session.getTransaction().commit();
//		session.getTransaction().rollback();
		session.close();
	}
	
//	private void updateRecord(Object o, Session  session){
//		session.saveOrUpdate(o);
//		
//	}

}
