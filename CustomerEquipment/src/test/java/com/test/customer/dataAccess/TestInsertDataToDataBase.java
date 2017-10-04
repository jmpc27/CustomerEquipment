package com.test.customer.dataAccess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
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
import com.pt.app.model.Sla;

import org.springframework.context.annotation.FilterType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/application-config.xml",
		"file:src/main/webapp/WEB-INF/mvc-config.xml"})

//@EnableWebMvc 
@Configuration
@ComponentScan(basePackages = { "com.pt.app" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
public class TestInsertDataToDataBase {
	
//	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void insertRecords(){
		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Customer customerA = new Customer();
		Address addressA = new Address();
		Equipment equipmentA = new Equipment();
		Sla slaA = new Sla();

		
//		***********************************************
//		Creation of customer
//		***********************************************
//		customerA.setIdCustomer(00001);
		customerA.setName("PT");
		customerA.setContactDetails("joao.manuel@xptoit.cenas.com");
		customerA.setContact("916630586");
//		***********************************************
//		***********************************************
		
		
//		***********************************************
//		Creation of address a add it to customer
//		***********************************************
		addressA.setPrimaryAddress("Av. Jaques Dolores 1024");
		addressA.setCustomer(customerA);
		Set<Address> addresses = new HashSet<Address>();
		addresses.add(addressA);
		customerA.setAddresses(addresses);
//		***********************************************
//		***********************************************
		
//		***********************************************
//		Creation of equipment a add it to customer
//		***********************************************		
		try{
			equipmentA.setInstallationAddress("4 Edificio Imopolis - Estrada da Outurela, nº118 - Bloco C 2790­-114 Carnaxide ");
			equipmentA.setModel("AF250");
			equipmentA.setSerialNumber("81340F00219");
			equipmentA.setSoftwareLastVersion("V6.3.4.1");
			equipmentA.setUpdateDate(sdf.parse("2017-06-20"));
			equipmentA.setSoftwareVersion("V6.3.3.1");
			equipmentA.setVendor("Cisco");
			equipmentA.setCustomer(customerA);
			equipmentA.setSoftwareUpdate(Boolean.TRUE);
		}catch(ParseException pe){
			System.out.println(pe);
		}
//		***********************************************
//		***********************************************

//		***********************************************
//		Creation of sla a add it to equipment
//		***********************************************
		try{
			Set<Equipment> equipments = new HashSet<Equipment>();
			equipments.add(equipmentA);
			slaA.setEquipments(equipments);
			slaA.setSla("24x7");
			customerA.setEquipments(equipments);
			slaA.setContractEnd(sdf.parse("2017-06-20"));
			slaA.setContractStart(sdf.parse("2017-06-20"));
			slaA.setInstallationDate(sdf.parse("2017-06-20"));
			equipmentA.setSla(slaA);
		}catch(ParseException pe){
			System.out.println(pe);
		}
//		***********************************************
//		***********************************************
		
//		***********************************************
//		Insert all data to database
//		***********************************************		
		Session  session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(equipmentA);
		session.save(slaA);
		session.save(customerA);
		session.save(addressA);
				
		
//		session.getTransaction().rollback();
		session.getTransaction().commit();
		
		session.close();
		
//		***********************************************
//		***********************************************
		
		
		
		
	}

}
