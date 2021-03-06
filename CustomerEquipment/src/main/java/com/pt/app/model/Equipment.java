package com.pt.app.model;
// Generated Jun 4, 2017 1:26:36 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Equipment generated by hbm2java
 */
@Entity
@Table(name = "equipment", catalog = "mydb")
public class Equipment {

	private Integer idEquipment;
	private Customer customer;
	private Sla sla;
	private String serialNumber;
	private String vendor;
	private String model;
	private String installationAddress;
	private String softwareVersion;
	private String softwareLastVersion;
	private Boolean softwareUpdate;
	private Date updateDate;

	public Equipment() {
	}

	public Equipment(Customer customer, Sla sla, String serialNumber, String vendor, String model,
			String installationAddress) {
		this.customer = customer;
		this.sla = sla;
		this.serialNumber = serialNumber;
		this.vendor = vendor;
		this.model = model;
		this.installationAddress = installationAddress;
	}

	public Equipment(Customer customer, Sla sla, String serialNumber, String vendor, String model,
			String installationAddress, String softwareVersion, String softwareLastVersion, Boolean softwareUpdate,
			Date updateDate) {
		this.customer = customer;
		this.sla = sla;
		this.serialNumber = serialNumber;
		this.vendor = vendor;
		this.model = model;
		this.installationAddress = installationAddress;
		this.softwareVersion = softwareVersion;
		this.softwareLastVersion = softwareLastVersion;
		this.softwareUpdate = softwareUpdate;
		this.updateDate = updateDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idEquipment", unique = true, nullable = true)
	public Integer getIdEquipment() {
		return this.idEquipment;
	}

	public void setIdEquipment(Integer idEquipment) {
		this.idEquipment = idEquipment;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCustomer", nullable = true)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "idSLA", nullable = true)
	public Sla getSla() {
		return this.sla;
	}

	public void setSla(Sla sla) {
		this.sla = sla;
	}

	@Column(name = "SerialNumber", nullable = true, length = 45)
	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "Vendor", nullable = true, length = 45)
	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	@Column(name = "Model", nullable = true, length = 45)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "InstallationAddress", nullable = true, length = 250)
	public String getInstallationAddress() {
		return this.installationAddress;
	}

	public void setInstallationAddress(String installationAddress) {
		this.installationAddress = installationAddress;
	}

	@Column(name = "SoftwareVersion", length = 15)
	public String getSoftwareVersion() {
		return this.softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	@Column(name = "SoftwareLastVersion", length = 15)
	public String getSoftwareLastVersion() {
		return this.softwareLastVersion;
	}

	public void setSoftwareLastVersion(String softwareLastVersion) {
		this.softwareLastVersion = softwareLastVersion;
	}

	@Column(name = "SoftwareUpdate")
	public Boolean getSoftwareUpdate() {
		return this.softwareUpdate;
	}

	public void setSoftwareUpdate(Boolean softwareUpdate) {
		this.softwareUpdate = softwareUpdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UpdateDate", length = 10)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
