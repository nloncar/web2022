package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.User;

public class UserDAO {
	private Map<String, User> users = new HashMap<>();
	//private ArrayList<User> users = new ArrayList<User>();
	private String contextPath;

	public UserDAO(String contextPath) {
		this.contextPath = contextPath;
		loadUsers();
	}
	
	public boolean nameExists(String username) {
	/*for(int i = 0; i < users.size(); i++)
		if (users.get(i).getUsername().equals(username)) {
			return true;
		}
		return false;*/
		if (!users.containsKey(username)) {
			return false;
		}
		return true;
		
	}
	
	public User loggedInUser(String username, String password) {
		/*for(int i = 0; i < users.size(); i++)
			if (users.get(i).getUsername().equals(username)) {
				if(users.get(i).getPassword().equals(password))
				{
					return users.get(i);
				}
				return null;
			}
		return null;*/
		if (!users.containsKey(username)) {
			return null;
		}
		User user = users.get(username);
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
	
	public User registerUser(User user) 
	{
		if(nameExists(user.getUsername()))
		{
			return null;
		}
		loadUsers();
		users.put(user.getUsername(), user);	
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(this.contextPath + "/users.json"), users);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void loadUsers()
	{
		try {
			ObjectMapper mapper = new ObjectMapper();
			users = mapper.readValue(new File(this.contextPath + "/users.json"), new TypeReference<HashMap<String, User>>(){});
			if(users.isEmpty())
			{
				mapper.writeValue(new File(this.contextPath + "/users.json"), users);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
