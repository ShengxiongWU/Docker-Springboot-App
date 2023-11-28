package main.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class user {
	@Id
	@GeneratedValue
	private Integer userId;
	private String firstName;
	private String LastName;
	private String type;
	@Column(name = "username", unique = true)
	@NotNull
	private String username;
	@NotNull
	private String password;

	private String email;
	
	
	
	public user() {
		
	}



	public user(Integer userId, String firstName, String lastName, String type, String username, String password, String email) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		LastName = lastName;
		this.type = type;
		this.username = username;
		this.password = password;
		this.email = email;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return LastName;
	}



	public void setLastName(String lastName) {
		LastName = lastName;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
