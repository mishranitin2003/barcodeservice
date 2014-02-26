package org.restful.barcode.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="barcode", uniqueConstraints=
		@UniqueConstraint(columnNames={"barcode_api_key"}))
public class Barcode {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="barcode_id")
	private Long id;
	
	@Column(name="barcode_api_key")
	private String barcodeApiKey;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	
	@Column(name="barcode_format")
	private String barcodeFormat;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="barcode")
	private List<BarcodePolicy> barcodePolicies = new ArrayList<BarcodePolicy>();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBarcodeApiKey() {
		return barcodeApiKey;
	}
	
	public void setBarcodeApiKey(String barcodeApiKey) {
		this.barcodeApiKey = barcodeApiKey;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getBarcodeFormat() {
		return barcodeFormat;
	}

	public void setBarcodeFormat(String barcodeFormat) {
		this.barcodeFormat = barcodeFormat;
	}

	public List<BarcodePolicy> getBarcodePolicies() {
		return barcodePolicies;
	}

	public void setBarcodePolicies(List<BarcodePolicy> barcodePolicies) {
		this.barcodePolicies = barcodePolicies;
	}
	
}
