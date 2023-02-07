package beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	
	String username;
	String password;
	String name;
	String surname;
	String birthday;
	String gender;
	UserType type;
	
	
	public User() {
	}
	
	public User(String username, String password, String name, String surname, String birthday, String gender, String type) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.gender = gender;
		this.type = UserType.valueOf(type);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
	
}

