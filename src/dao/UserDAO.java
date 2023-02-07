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
		loadMemberships(contextPath);
		this.contextPath = contextPath;
	}
	

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
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/users.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					String username = st.nextToken().trim();
					String password = st.nextToken().trim();
					String name = st.nextToken().trim();
					String surname = st.nextToken().trim();
					String birthday = st.nextToken().trim();
					String gender = st.nextToken().trim();
					String type = st.nextToken().trim();
					users.put(username, new User(username, password, name, surname, birthday, gender, type));
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
	}
	
	public User editUser(String username, String password, String name, String surname, String birthday, String gender)
	{
		System.out.println("editing:");
		User user = new User(username, password, name, surname, birthday, gender, "customer");
		users.put(username, user);
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
		users.put(username, user);
		System.out.println("added");
		writeUsers();
		return user;
	}
	
	public boolean writeUsers()
	{
		File file = new File(this.contextPath + "/users.txt");
		
		if(!file.exists()){
	     	   try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     	}
		
		System.out.println(this.contextPath + "/users.txt");
    	PrintWriter out; 
    	
		try 
		{
			ArrayList<User> usersBase = new ArrayList<User>(this.users.values()); 
			out = new PrintWriter(file);
	    	System.out.println("writing");
	    	
			for(User user : usersBase)
			{
				out.print(user.getUsername() + ";" + user.getPassword() + ";" + user.getName()
				+ ";" + user.getSurname() + ";" + user.getBirthday() + ";" + user.getGender());
				
				out.print(System.getProperty("line.separator"));
							
				
				System.out.println("written " +user.getUsername() + " " + user.getGender());
			}
			out.close();
			
			return true;
		}
		catch (Exception ex) {
		ex.printStackTrace();
		return false;
	}
}
	private void loadCustomers(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/customers.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					ArrayList<String> visitedObjects = new ArrayList<String>();
					String username = st.nextToken().trim();
					String membership = st.nextToken().trim();
					int points = Integer.parseInt(st.nextToken().trim());
					String type = st.nextToken().trim();
					String objects = st.nextToken().trim();
					objects = objects.trim();
					int i = 0;
					StringTokenizer st_ob = new StringTokenizer(objects, "+");
						while(st_ob.hasMoreTokens()){
							visitedObjects.add(st_ob.nextToken().trim());
													}
					customers.put(username, new Customer(username, memberships.get(membership), visitedObjects, points, type));
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
	}
	
	public boolean writeCustomers()
	{
		File file = new File(this.contextPath + "/customers.txt");
		
		if(!file.exists()){
	     	   try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     	}
		
    	PrintWriter out; 
    	
		try 
		{
			ArrayList<Customer> customerBase = new ArrayList<Customer>(this.customers.values()); 
			out = new PrintWriter(file);
	    	System.out.println("writing");
	    	
			for(Customer c: customerBase)
			{
				String member = "";
				if(c.getMembership().getId() == null)
				{
					member = "null";
				}
				else
				{
					member = c.getMembership().getId();
				}
				out.print(c.getUsername() + ";" + member + ";" + c.getBodovi() + ";" + c.getTip() + ";");
				for(String p : c.getPoseceniObjekti())
				{
					out.print(p);
				}
				
				out.print(System.getProperty("line.separator"));
				
			}
			out.close();
			
			return true;
		}
		catch (Exception ex) {
		ex.printStackTrace();
		return false;
	}
}
		
		//MEMBERSHIPS//
		
		private void loadMemberships(String contextPath) {
			BufferedReader in = null;
			try {
				File file = new File(contextPath + "/memberships.txt");
				in = new BufferedReader(new FileReader(file));
				String line;
				StringTokenizer st;
				while ((line = in.readLine()) != null) {
					line = line.trim();
					if (line.equals("") || line.indexOf('#') == 0)
						continue;
					st = new StringTokenizer(line, ";");
					while (st.hasMoreTokens()) {
						String id = st.nextToken().trim();
						String username = st.nextToken().trim();
						MembershipType type = MembershipType.valueOf(st.nextToken().trim());
						LocalDate billingDate = LocalDate.parse(st.nextToken().trim(), dateFormatter);
						LocalDate expirationDate = LocalDate.parse(st.nextToken().trim(), dateFormatter);
						Boolean status = Boolean.parseBoolean(st.nextToken().trim());
						int maxEntries = Integer.parseInt(st.nextToken().trim());
						int usedEntries = Integer.parseInt(st.nextToken().trim());
						int price = Integer.parseInt(st.nextToken().trim());
						memberships.put(username, new Membership(id, username, type, billingDate, expirationDate, status, maxEntries, usedEntries, price));
					}
					
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					}
					catch (Exception e) { }
				}
			}
		}
		
		public boolean writeMemberships()
		{
			File file = new File(this.contextPath + "/memberships.txt");
			
			if(!file.exists()){
		     	   try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     	}
	    	PrintWriter out; 
	    	
			try 
			{
				ArrayList<Membership> membershipBase = new ArrayList<Membership>(this.memberships.values()); 
				out = new PrintWriter(file);
		    	System.out.println("writing");
		    	
				for(Membership membership : membershipBase)
				{
					out.print(membership.getCustomer() + ";" + membership.getType().toString() + ";" + membership.getBillingDate().toString()
					+ ";" + membership.getExpirationDate().toString() + ";" + membership.getStatus().toString() + ";" + membership.getMaxEntries() + ";" + membership.getUsedEntries()
					+ ";" + membership.getPrice());
					
					out.print(System.getProperty("line.separator"));
								
				}
				out.close();
				
				return true;
			}
			catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
		
		public Collection<Membership> findAllMemberships()
		{
			return memberships.values();
		}
		
		public ArrayList<Membership> getMemberPackages()
		{
			ArrayList<Membership> ret = new ArrayList<Membership>();
			ArrayList<Membership> memberships = new ArrayList<Membership>(this.memberships.values()); 
			for(Membership membership: memberships)
			{
				if(membership.getCustomer() == null)
				{
					ret.add(membership);
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
				int points = 0;
				if(membership.getExpirationDate().isAfter(LocalDate.now()))
				{
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
					toAdd.setMembership(membership);
					customers.put(membership.getCustomer(), toAdd);
					writeCustomers();
				}
			}
		}
		
		public Membership getMembershipByUser(String username)
		{
			ArrayList<Membership> membershipBase = new ArrayList<Membership>(this.memberships.values()); 
			
			for(Membership m : membershipBase)
			{
				if(m.getCustomer().equals(username))
				{
					return m;
				}
			}
			return null;
		}
}
	
