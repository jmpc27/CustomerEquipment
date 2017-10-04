package com.pt.app.dto;

import java.util.Date;

public class CustomerEquipmentsSlaDto {
	
	private Integer idEquipment;
	private String serialNumber;
	private String vendor;
	private String model;
	private String installationAddress;
	private String softwareVersion;
	private String softwareLastVersion;
	private Boolean softwareUpdate;
	private Date updateDate;
	private Integer idSla;
	private String sla;
	private Date contractStart;
	private Date contractEnd;
	private Date installationDate;
	private Integer idCustomer;
	
	public CustomerEquipmentsSlaDto(){}
	
	public CustomerEquipmentsSlaDto(Integer idEquipment, String serialNumber, String vendor, String model,
			String installationAddress, String softwareVersion, String softwareLastVersion, Boolean softwareUpdate,
			Date updateDate, String sla, Date contractStart, Date contractEnd, Date installationDate, Integer idCustomer, Integer idSla) {
		super();
		this.idEquipment = idEquipment;
		this.serialNumber = serialNumber;
		this.vendor = vendor;
		this.model = model;
		this.installationAddress = installationAddress;
		this.softwareVersion = softwareVersion;
		this.softwareLastVersion = softwareLastVersion;
		this.softwareUpdate = softwareUpdate;
		this.updateDate = updateDate;
		this.sla = sla;
		this.contractStart = contractStart;
		this.contractEnd = contractEnd;
		this.installationDate = installationDate;
		this.idCustomer = idCustomer;
		this.idSla = idSla;
	}

	public Integer getIdEquipment() {
		return idEquipment;
	}

	public void setIdEquipment(Integer idEquipment) {
		this.idEquipment = idEquipment;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getInstallationAddress() {
		return installationAddress;
	}

	public void setInstallationAddress(String installationAddress) {
		this.installationAddress = installationAddress;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getSoftwareLastVersion() {
		return softwareLastVersion;
	}

	public void setSoftwareLastVersion(String softwareLastVersion) {
		this.softwareLastVersion = softwareLastVersion;
	}

	public Boolean getSoftwareUpdate() {
		return softwareUpdate;
	}

	public void setSoftwareUpdate(Boolean softwareUpdate) {
		this.softwareUpdate = softwareUpdate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getSla() {
		return sla;
	}

	public void setSla(String sla) {
		this.sla = sla;
	}

	public Date getContractStart() {
		return contractStart;
	}

	public void setContractStart(Date contractStart) {
		this.contractStart = contractStart;
	}

	public Date getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}

	public Date getInstallationDate() {
		return installationDate;
	}

	public void setInstallationDate(Date installationDate) {
		this.installationDate = installationDate;
	}

	public Integer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Integer getIdSla() {
		return idSla;
	}

	public void setIdSla(Integer idSla) {
		this.idSla = idSla;
	}

	@Override
	public String toString() {
		return "CustomerEquipmentsSlaDto [idEquipment=" + idEquipment + ", serialNumber=" + serialNumber + ", vendor="
				+ vendor + ", model=" + model + ", installationAddress=" + installationAddress + ", softwareVersion="
				+ softwareVersion + ", softwareLastVersion=" + softwareLastVersion + ", softwareUpdate="
				+ softwareUpdate + ", updateDate=" + updateDate + ", sla=" + sla + ", contractStart=" + contractStart
				+ ", contractEnd=" + contractEnd + ", installationDate=" + installationDate + idCustomer +"]";
	}
	
	
	
	
	
}
