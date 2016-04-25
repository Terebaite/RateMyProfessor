package rateMyProfessor;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 3879447800883724352L;
	private int id;
	private String name;
	private String password;
	private String email;

	public User() {
		// Empty constructor
	}

	public User(String email, String name, String password, int id) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
