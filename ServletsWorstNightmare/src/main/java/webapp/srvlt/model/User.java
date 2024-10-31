package webapp.srvlt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "users")
public class User {
	@Id
	@Column (name = "id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "login", nullable = false, unique = true)
	private String login;
	
	@Column (name = "passw", nullable = false)
	private byte[] passw;
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", passw=" + passw + "]";
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String login, byte[] passw) {
		this.login = login;
		this.passw = passw;
	}
	
	public User(int id, String login, byte[] passw) {
		this (login, passw);
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public byte[] getPassw() {
		return passw;
	}

	public void setPassw(byte[] passw) {
		this.passw = passw;
	}
	

}
