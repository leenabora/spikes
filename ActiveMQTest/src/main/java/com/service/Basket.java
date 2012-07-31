package com.service;

/**
 * TODO - This might not be required if json is persisted to DB
 * @author pavan
 *
 */
public class Basket {

	private String branch;
	private String cashier;
	private String tillno;
	private String receiptno;
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getCashier() {
		return cashier;
	}
	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
	public String getTillno() {
		return tillno;
	}
	public void setTillno(String tillno) {
		this.tillno = tillno;
	}
	public String getReceiptno() {
		return receiptno;
	}
	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}
	
	
}
