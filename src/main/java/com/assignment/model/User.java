package com.assignment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity(name = "User")
@Table(name = "User")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8430902642313305374L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="username", nullable=false,unique=true)
    private String userName;
	@Column(name="status", nullable=false)
    private String status;
	@Column(name="password", nullable=false)
    private String password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User() {
		super();
	}
    
	public static final String ACTIVATED = "Activated";
	public static final String DEACTIVATED = "Deactivated";
}
