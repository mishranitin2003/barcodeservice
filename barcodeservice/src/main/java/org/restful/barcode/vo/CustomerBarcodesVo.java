package org.restful.barcode.vo;

public class CustomerBarcodesVo {

	private Long barcodeId;
	private Long barcodePolicyId;
	private String barcodeApiKey;
	private String barcodeFormat;
	private String domain;
	
	public Long getBarcodeId() {
		return barcodeId;
	}

	public void setBarcodeId(Long barcodeId) {
		this.barcodeId = barcodeId;
	}

	public Long getBarcodePolicyId() {
		return barcodePolicyId;
	}
	
	public void setBarcodePolicyId(Long barcodePolicyId) {
		this.barcodePolicyId = barcodePolicyId;
	}
	
	public String getBarcodeApiKey() {
		return barcodeApiKey;
	}
	
	public void setBarcodeApiKey(String barcodeApiKey) {
		this.barcodeApiKey = barcodeApiKey;
	}
	
	public String getBarcodeFormat() {
		return barcodeFormat;
	}
	
	public void setBarcodeFormat(String barcodeFormat) {
		this.barcodeFormat = barcodeFormat;
	}
	
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
}
