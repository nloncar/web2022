package beans;

import java.time.LocalDate;
import java.util.UUID;

public class Membership {

	private String id;
	private String customer;
	private MembershipType type;
	private LocalDate billingDate;
	private LocalDate expirationDate;
	private Boolean status;
	private int maxEntries; //0 = unlimited
	private int usedEntries;
	private int price;
	
	public Membership()
	{
		
	}
	
	public Membership(String id, String customer, MembershipType type, LocalDate billingDate, LocalDate expirationDate,
			Boolean status, int maxEntries, int usedEntries, int price) {
		super();
		if(id == null)
		{
			this.id = UUID.randomUUID().toString();
		}
		else
		{
			this.id = id;
		}
		this.customer = customer;
		this.type = type;
		this.billingDate = billingDate;
		this.expirationDate = expirationDate;
		this.status = status;
		this.maxEntries = maxEntries;
		this.usedEntries = usedEntries;
		this.price = price;
	}
	public MembershipType getType() {
		return type;
	}
	public void setType(MembershipType type) {
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
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getMaxEntries() {
		return maxEntries;
	}
	public void setMaxEntries(int maxEntries) {
		this.maxEntries = maxEntries;
	}
	public int getUsedEntries() {
		return usedEntries;
	}
	public void setUsedEntries(int usedEntries) {
		this.usedEntries = usedEntries;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	
}
