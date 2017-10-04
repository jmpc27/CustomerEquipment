package com.pt.app.dto;

public class CustomerAddressDto {
	
	private Integer idCustomer;
	private String name;
	private String contactDetails;
	private String contact;
	private Integer idAddress;
	private String primaryAddress;
	private String secondaryAddress;
	
	public Integer getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPrimaryAddress() {
		return primaryAddress;
	}
	public void setPrimaryAddress(String primaryAddress) {
		this.primaryAddress = primaryAddress;
	}
	public String getSecondaryAddress() {
		return secondaryAddress;
	}
	public void setSecondaryAddress(String secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}
	public Integer getIdAddress() {
		return idAddress;
	}
	public void setIdAddress(Integer idAddress) {
		this.idAddress = idAddress;
	}
	
	@Override
	public String toString() {
		return "CustomerAddressDto [idCustomer=" + idCustomer + ", name=" + name + ", contactDetails=" + contactDetails
				+ ", contact=" + contact + ", primaryAddress=" + primaryAddress + ", idAddress=" + idAddress + " secondaryAddress="
				+ secondaryAddress + "]";
	}
	
	public CustomerAddressDto(){};
	
	public CustomerAddressDto(Integer idCustomer, String name, String contactDetails, String contact,
			String primaryAddress, String secondaryAddress, Integer idAddress) {
		super();
		this.idCustomer = idCustomer;
		this.name = name;
		this.contactDetails = contactDetails;
		this.contact = contact;
		this.primaryAddress = primaryAddress;
		this.secondaryAddress = secondaryAddress;
		this.idAddress = idAddress;
	}
	
	
	
}
