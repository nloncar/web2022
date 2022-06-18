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

import model.User;

public class UserDAO {
	//private Map<String, User> users = new HashMap<>();
	private ArrayList<User> users = new ArrayList<User>();

	public UserDAO(String contextPath) {
		//loadUsers(contextPath);
	}
	
	public boolean nameExists(String username) {
	for(int i = 0; i < users.size(); i++)
		if (users.get(i).getUsername().equals(username)) {
			return true;
		}
		return false;
	}
	
	public User loggedInUser(String username, String password) {
		for(int i = 0; i < users.size(); i++)
			if (users.get(i).getUsername().equals(username)) {
				if(users.get(i).getPassword().equals(password))
				{
					return users.get(i);
				}
				return null;
			}
		return null;
	}
	
	public boolean registerUser(User user) 
	{
		if(nameExists(user.getUsername()))
		{
			return false;
		}
		loadUsers();
		users.add(user);
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File("users.json"), users);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void loadUsers()
	{
		try {
			ObjectMapper mapper = new ObjectMapper();
			users = mapper.readValue(new File("users.json"), new TypeReference<ArrayList<User>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
