package com.MrFix30.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="complaints")
public class Complaints {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comp_id;
	public int getComp_id() {
		return comp_id;
	}
	public void setComp_id(int comp_id) {
		this.comp_id = comp_id;
	}
	public String getComplainant() {
		return complainant;
	}
	public void setComplainant(String complainant) {
		this.complainant = complainant;
	}
	public String getComp_sub() {
		return comp_sub;
	}
	public void setComp_sub(String comp_sub) {
		this.comp_sub = comp_sub;
	}
	public String getComp_issue() {
		return comp_issue;
	}
	public void setComp_issue(String comp_issue) {
		this.comp_issue = comp_issue;
	}
	public String getComp_status() {
		return comp_status;
	}
	public void setComp_status(String comp_status) {
		this.comp_status = comp_status;
	}
	@Column(name="complainant")
	private  String complainant;
	@Override
	public String toString() {
		return "Complaints [comp_id=" + comp_id + ", complainant=" + complainant + ", comp_sub=" + comp_sub
				+ ", comp_issue=" + comp_issue + ", comp_status=" + comp_status + "]";
	}
	@Column(name="comp_sub")
	private String comp_sub;
	@Column(name="comp_issue")
	private String comp_issue;
	@Column(name="comp_status")
	private String comp_status;
	
}
