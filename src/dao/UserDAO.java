package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import beans.User;

public class UserDAO {
	private Map<String, User> users = new HashMap<>();
	private String contextPath;
	
	public UserDAO() {
		
	}
	

	public UserDAO(String contextPath) {
		loadUsers(contextPath);
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
					users.put(username, new User(username, password, name, surname, birthday, gender));
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
	
	public boolean RegisterUser(String username, String password, String name, String surname, String birthday, String gender)
	{
		
		System.out.println(username + password);
		if (users.containsKey(username)) {
			return false;
		}

		User user = new User(username, password, name, surname, birthday, gender);
		
		BufferedWriter out = null;
		try 
		{
			File file = new File(this.contextPath + "/users.txt");
	    	if(!file.exists()){
	     	   file.createNewFile();
	     	}
	    	users.put(user.getUsername(), user);
			out = new BufferedWriter(new FileWriter(file, true));
			out.newLine();
			out.write(user.getUsername() + ";" + user.getPassword() + ";" + user.getName()
			+ ";" + user.getSurname() + ";" + user.getBirthday() + ";" + user.getGender());
			return true;
		}
		catch (Exception ex) {
		ex.printStackTrace();
		return false;
	} finally {
		if (out != null) {
			try {
				out.close();
			}
			catch (Exception e) { 
			}
		}
	}
	}
	
	
}
