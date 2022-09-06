package com.BScamp.MovieTheater.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(unique = true)
	private String gmail;

	@Column(columnDefinition = "ENUM('admin', 'user')")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	private LocalDate startJoinDate;

	private LocalDate lastJoinDate;

	private int accessCount;

	@Column(nullable = false)
	@ColumnDefault("CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

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

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
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

	public int getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", gmail=" + gmail + ", role=" + role
				+ ", startJoinDate=" + startJoinDate + ", lastJoinDate=" + lastJoinDate + ", accessCount=" + accessCount
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
