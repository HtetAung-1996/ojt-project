package com.BScamp.MovieTheater.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(unique = true)
	private String gmail;

	private String role;

	private LocalDate startJoinDate;

	private LocalDate lastJoinDate;

	private int accessCount;

	private LocalDate createdAt;

	private LocalDate updatedAt;

	private LocalDate deletedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDate getStartJoinDate() {
		return startJoinDate;
	}

	public void setStartJoinDate(LocalDate startJoinDate) {
		this.startJoinDate = startJoinDate;
	}

	public LocalDate getLastJoinDate() {
		return lastJoinDate;
	}

	public void setLastJoinDate(LocalDate lastJoinDate) {
		this.lastJoinDate = lastJoinDate;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public int getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDate getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDate deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role + ", startJoinDate="
				+ startJoinDate + ", lastJoinDate=" + lastJoinDate + ", gmail=" + gmail + ", accessCount=" + accessCount
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
	}

}
