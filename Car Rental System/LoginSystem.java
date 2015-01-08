/*
 * Title: LoginSystem.java
 * Abstract: This class solely handles all login information
 * created in the car rental system.
 */

import java.util.ArrayList;

public class LoginSystem {
	
	private String username;
	private String password;
	
	public static ArrayList<LoginSystem>userCredentials = new ArrayList();

	public LoginSystem(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
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
	

	
}
