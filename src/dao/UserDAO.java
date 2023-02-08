package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import beans.Customer;
import beans.Membership;
import beans.MembershipType;
import beans.Product;
import beans.TrainingSession;
import beans.User;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDAO {
	
	private Map<String, User> users = new HashMap<>();
	private Map<String, Membership> memberships = new HashMap<>();
	private Map<String, Customer> customers = new HashMap<>();
	
	private String contextPath;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-mm-DD");  
	
	public UserDAO() {
		
	}
	

	public UserDAO(String contextPath) {
		loadUsers(contextPath);
		loadCustomers(contextPath);
		loadMemberships(contextPath);
		this.contextPath = contextPath;
	}
	
	/*public void generateMemberPackages()
	{
		Membership trial = new Membership(null, null, MembershipType.valueOf("trial"), null, null, false, 4, 0, 500);
		Membership monthly = new Membership(null, null, MembershipType.valueOf("monthly"), null, null, false, 10, 0, 5000);
		Membership halfyearly = new Membership(null, null, MembershipType.valueOf("halfyearly"), null, null, false, 80, 0, 25000);
		Membership yearly = new Membership(null, null, MembershipType.valueOf("yearly"), null, null, false, 0, 0, 50000);
		
		memberships.put(trial.getId(), trial);
		memberships.put(monthly.getId(), monthly);
		memberships.put(halfyearly.getId(), halfyearly);
		memberships.put(yearly.getId(), yearly);
		
		writeMemberships();
		System.out.println("generated member packages");
	}*/

	public User find(String username, String password) {
		if (!users.containsKey(username)) {
			return null;
		}
		User user = users.get(username);
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
	
	public Collection<User> findAll() {
		return users.values();
	}

	private void loadUsers(String contextPath) {
		System.out.println("loading");
		File file = new File(contextPath + "/users.json");
		try {
			ObjectMapper mapper = new ObjectMapper();
			users = mapper.readValue(file, new TypeReference<Map<String, User>>(){});
			System.out.println("Loaded users");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public User editUser(String username, String password, String name, String surname, String birthday, String gender)
	{
		System.out.println("editing:");
		User user = new User(username, password, name, surname, birthday, gender, "customer");
		Customer customer = new Customer(username, password, name, surname, birthday, gender, "customer");
		users.put(username, user);
		customers.put(username, customer);
		writeUsers();
		System.out.println(user.getUsername() + "se sad zove " + user.getName());
		return user;
	}
	
	public User RegisterUser(String username, String password, String name, String surname, String birthday, String gender)
	{
		
		System.out.println(username + password);
		if (users.containsKey(username)) {
			return null;
		}

		User user = new User(username, password, name, surname, birthday, gender, "customer");
		Customer customer = new Customer(username, password, name, surname, birthday, gender, "customer");
		users.put(username, user);
		customers.put(username, customer);
		System.out.println("added");
		writeUsers();
		writeCustomers();
		return user;
	}
	
	public boolean writeUsers()
	{
		File file = new File(this.contextPath + "/users.json");
		
		if(!file.exists()){
	     	   try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     	}
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(file, users);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
}
	private void loadCustomers(String contextPath) {
		File file = new File(contextPath + "/customers.json");
		try {
			ObjectMapper mapper = new ObjectMapper();
			customers = mapper.readValue(file, new TypeReference<Map<String, Customer>>(){});
			System.out.println("Loaded customers");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean writeCustomers()
	{
		File file = new File(this.contextPath + "/customers.json");
		
		if(!file.exists()){
	     	   try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     	}
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(file, customers);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Customer findCustomer(String username) {
		return customers.get(username);
	}
		
		//MEMBERSHIPS//
		
		private void loadMemberships(String contextPath) {
			File file = new File(contextPath + "/memberships.json");
			try {
				ObjectMapper mapper = new ObjectMapper();
				memberships = mapper.readValue(file, new TypeReference<Map<String, Membership>>(){});
				System.out.println("Loaded memberships");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public boolean writeMemberships()
		{
			File file = new File(this.contextPath + "/memberships.json");
			
			if(!file.exists()){
		     	   try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     	}
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(file, memberships);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}
		
		public Collection<Membership> findAllMemberships()
		{
			return memberships.values();
		}
		
		public Collection<Membership> getMemberPackages()
		{
			ArrayList<Membership> ret = new ArrayList<Membership>();
			ArrayList<Membership> memberships = new ArrayList<Membership>(this.memberships.values()); 
			for(Membership membership: memberships)
			{
				if(membership.getCustomer() == null)
				{
					ret.add(membership);
					System.out.println("Loaded member package:" + membership.getType());
				}
			}
			return ret;
		}
		
		public Membership createMembership(String customer, String type)
		{
			LocalDate billingDate = LocalDate.now();
			LocalDate expirationDate = billingDate;
			int maxEntries = 0;
			int price = 0;
			if(type.toString() == "trial")
			{
				maxEntries = 4;
				expirationDate = billingDate.plusDays(3);
				price = 500;
			}
			else if (type.toString() == "monthly")
			{
				maxEntries = 10;
				expirationDate = billingDate.plusMonths(1);
				price = 5000;
			}
			else if (type.toString() == "halfyearly")
			{
				maxEntries = 80;
				expirationDate = billingDate.plusMonths(6);
				price = 25000;
			}
			else if (type.toString() == "yearly")
			{
				maxEntries = 0;
				expirationDate = billingDate.plusYears(1);
				price = 50000;
			}
			
			Membership membership = new Membership(null, customer, MembershipType.valueOf(type), billingDate, expirationDate, true, maxEntries, 0, price);
			memberships.put(membership.getId(), membership);
			writeMemberships();
			
			Customer toAdd = customers.get(customer);
			toAdd.setMembership(membership);
			customers.put(customer, toAdd);
			writeCustomers();
			
			return membership;
		}
		
		public void membershipExpired()
		{
			ArrayList<Membership> memberships = new ArrayList<Membership>(this.memberships.values()); 
			for(Membership membership: memberships)
			{
				if(membership.getCustomer() != null)
				{
				if(membership.getExpirationDate().isAfter(LocalDate.now()))
				{
					int points = 0;
					membership.setStatus(false);
					if(membership.getMaxEntries() == 0)
					{
						points = membership.getPrice() / 1000 * membership.getUsedEntries();
					}
					else
					{
						if(membership.getUsedEntries() < membership.getMaxEntries()/3)
						{
							points = membership.getPrice()/1000 * 133 * 4;
						}
						else
						{
							points = membership.getPrice() / 1000 * membership.getUsedEntries();
						}
					}
					this.memberships.put(membership.getId(), membership);
					writeMemberships();

					Customer toAdd = customers.get(membership.getCustomer());
					toAdd.setBodovi(toAdd.getBodovi() + points);
					toAdd.setMembership(membership);
					customers.put(membership.getCustomer(), toAdd);
					writeCustomers();
					}
				}
			}
		}
		
		public Membership getMembershipByUser(String username)
		{
			Customer customer = customers.get(username);
			if(customer.getMembership() != null)
			{
			return customer.getMembership();
			}
			return null;
		}
}
	
