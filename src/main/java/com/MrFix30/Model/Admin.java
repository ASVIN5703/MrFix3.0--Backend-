package com.MrFix30.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "admin_name")
	private String admin_name;
	@Column(name = "admin_pass")
	private String admin_pass;
	@Column(name = "admin_email")
	private String admin_email;
	@Column(name = "admin_enable")
	private boolean admin_enable;
	
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", admin_name=" + admin_name + ", admin_pass=" + admin_pass + ", admin_email="
				+ admin_email + ", admin_enable=" + admin_enable + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_pass() {
		return admin_pass;
	}
	public void setAdmin_pass(String admin_pass) {
		this.admin_pass = admin_pass;
	}
	public String getAdmin_email() {
		return admin_email;
	}
	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	public boolean isAdmin_enable() {
		return admin_enable;
	}
	public void setAdmin_enable(boolean admin_enable) {
		this.admin_enable = admin_enable;
	}
	
}
