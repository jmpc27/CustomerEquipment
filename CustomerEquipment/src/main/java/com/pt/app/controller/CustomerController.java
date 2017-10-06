package com.pt.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pt.app.dto.CustomerAddressDto;
import com.pt.app.dto.CustomerEquipmentsSlaDto;
import com.pt.app.model.Customer;
import com.pt.app.service.CustomerService;

@Controller
@RequestMapping("/customer")
@EnableWebMvc
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	
	@RequestMapping(method = RequestMethod.GET)
	public String welcome (){
		return "customer";
	}
		
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/list",  method = RequestMethod.GET)
	public @ResponseBody List<CustomerAddressDto> getCustomersAndAddresses(){
		return customerService.getCustomersAndAddresses();
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public @ResponseBody void addCustomer(@RequestBody List <ObjectNode> json){
		for (ObjectNode obj: json){
			this.customerService.mapJsonStringToCustomerAddressDto(obj);
		}
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/del", method=RequestMethod.POST)
	public @ResponseBody void deleteCustomer(@RequestBody List<Customer> customers){
		for  (Customer c : customers){
			this.customerService.deleteCustomer(c);
		}
	}	
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/eqList", method=RequestMethod.PUT)
	public @ResponseBody List<CustomerEquipmentsSlaDto> getEquipmentsAndSLas(@RequestBody Customer customer){
		return customerService.getEquipmentsAndSlaByCustomer(customer);
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/eqAdd", method=RequestMethod.POST)
	public @ResponseBody void addEquipment(@RequestBody List <ObjectNode> json){
		for (ObjectNode obj: json){
			this.customerService.mapJsonStringToEquipmentSlaDto(obj);
		}
	}

	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/eqDel", method=RequestMethod.POST)
	public @ResponseBody void delEquipment(@RequestBody List <ObjectNode> json){
		for (ObjectNode obj: json){
			this.customerService.mapJsonStringToDelEquipmentSlaDto(obj);
		}
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String showLoggedout(){
		return "redirect:/login";
	}
	
}
