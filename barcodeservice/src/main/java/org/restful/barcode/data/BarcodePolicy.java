package org.restful.barcode.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="barcode_policy")
public class BarcodePolicy {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/*
	 * S - System Domain
	 * M - Phone Domain
	 * T - Tablet Domain
	 */
	@Column(nullable=false)
	private String domain;
	
	@ManyToOne
	@JoinColumn(nullable=false, name="barcode_id")
	private Barcode barcode;
	
	private Integer width;
	private Integer height;
	private boolean active;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public Barcode getBarcode() {
		return barcode;
	}
	
	public void setBarcode(Barcode barcode) {
		
		this.barcode = barcode;
	}
	public Integer getWidth() {
		return width;
	}
	
	public void setWidth(Integer width) {
		
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	

	
}
