package beans;

import java.rmi.server.UID;
import java.time.LocalDate;

public class Membership {

	enum Type {month, year};
	
	private String id;
	private Type type;
	private LocalDate billingDate;
	private LocalDate expirationDate;
	private Customer customer;
	private Boolean status;
	private int entries; //-1 = unlimited
	public Membership(String id, Type type, LocalDate billingDate, LocalDate expirationDate, Customer customer,
			Boolean status, int entries) {
		super();
		this.id = id;
		this.type = type;
		this.billingDate = billingDate;
		this.expirationDate = expirationDate;
		this.customer = customer;
		this.status = status;
		this.entries = entries;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public LocalDate getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getEntries() {
		return entries;
	}
	public void setEntries(int entries) {
		this.entries = entries;
	}
	
	
}
