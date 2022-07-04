package com.BScamp.SpringMVCDemo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	@Column(name="user_name")
	private String name;
	private String password;
	private String role;
	private LocalDate start_join_date;
	private LocalDate last_join_date;
	@Column(unique = true)
	private String gmail;
	private int access_count;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public LocalDate getStart_join_date() {
		return start_join_date;
	}
	public void setStart_join_date(LocalDate start_join_date) {
		this.start_join_date = start_join_date;
	}
	public LocalDate getLast_join_date() {
		return last_join_date;
	}
	public void setLast_join_date(LocalDate last_join_date) {
		this.last_join_date = last_join_date;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public int getAccess_count() {
		return access_count;
	}
	public void setAccess_count(int access_count) {
		this.access_count = access_count;
	}
	@Override
	public String toString() {
		return "MovieUser [user_id=" + user_id + ", name=" + name + ", password=" + password + ", role=" + role
				+ ", start_join_date=" + start_join_date + ", last_join_date=" + last_join_date + ", gmail=" + gmail
				+ ", access_count=" + access_count + "]";
	}
	
	
	

}
