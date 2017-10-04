package com.test.customer.dataAccess;

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

import com.pt.app.model.Customer;
import org.springframework.context.annotation.FilterType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/application-config.xml",
		"file:src/main/webapp/WEB-INF/mvc-config.xml"})

//@EnableWebMvc 
@Configuration
@ComponentScan(basePackages = { "com.pt.app" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
public class TestRetrieveCustoimerByIdFromDB {
	
//	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void retrieveData(){
		
		
//		***********************************************
//		retrieve data form database
//		***********************************************		
		Session  session = sessionFactory.openSession();
		session.beginTransaction();
		
		Customer c = (Customer) session.createQuery("from Customer where idCustomer = " + 16).list().get(0);
			
		System.out.println("************* Customer info with id:" + c.getName() + " | " + c.getContact() + "*******************");
			
		session.close();
	}

}
