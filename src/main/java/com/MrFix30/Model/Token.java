package com.MrFix30.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "token")
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "token", length = 64, updatable = false)
	private String token;
    @Column(name="expiryDateTime")
    private LocalDateTime expiryDateTime;
    @Column(name="isTokenUsed")
    private boolean isTokenUsed;
    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin; 
	}
	@Override
	public String toString() {
		return "Token [id=" + id + ", token=" + token + ", expiryDateTime=" + expiryDateTime + ", isTokenUsed="
				+ isTokenUsed + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getExpiryDateTime() {
		return expiryDateTime;
	}
	public void setExpiryDateTime(LocalDateTime expiryDateTime) {
		this.expiryDateTime = expiryDateTime;
	}
	public boolean isTokenUsed() {
		return isTokenUsed;
	}
	public void setTokenUsed(boolean isTokenUsed) {
		this.isTokenUsed = isTokenUsed;
	}
	
}
