package com.ziad.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ziad.enums.Role;

@Entity
@Table(name = "user")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	private int active;

	private String roles = "";

	private String sms = "";

	public User(String username, String password, String roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.active = 1;

	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", active=" + active
				+ ", roles=" + roles + "]";
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getActive() {
		return active;
	}

	public String getRoles() {
		return roles;
	}

	public List<String> getRoleList() {
		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (roles.length() == 0) {
			roles = role.getRole();
		} else {
			roles = roles + "," + role.getRole();
		}
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

}
