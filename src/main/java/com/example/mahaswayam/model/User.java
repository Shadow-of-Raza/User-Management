package com.example.mahaswayam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import java.util.UUID;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User entity representing system users with various attributes.
 */

@Entity
@Table(name = "users")
public class User {

	@Id
	@UuidGenerator
	@Column(name = "user_id", updatable = false, nullable = false)
	private UUID userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_type_id", nullable = false)
	private UserType userType;

	@Column(name = "username", unique = true, nullable = false)
	@NotBlank(message = "Username is required")
	@Size(min = 4, max = 255, message = "Username must be between 4 and 255 characters")
	private String username;

	@JsonIgnore
	@Column(name = "password_hash", nullable = false)
	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String passwordHash;

	@Column(name = "email", unique = true, nullable = false)
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@Column(name = "phone", unique = true, nullable = false)
	@Pattern(message = "Invalid Phone Number", regexp = "^[6-9]\\d{9}$")
	private String phone;

	@Column(name = "first_name")
	@Size(max = 255, message = "First name must not exceed 255 characters")
	private String firstName;

	@Column(name = "last_name")
	@Size(max = 255, message = "Last name must not exceed 255 characters")
	private String lastName;

	@Column(name = "aadhaar_number", unique = true, nullable = false)
	private String aadhaarNumber;

	@Column(name = "address")
	@Size(max = 255)
	private String address;

	@Column(name = "city")
	@Size(max = 255)
	private String city;

	@Column(name = "state")
	@Size(max = 255)
	private String state;

	@Column(name = "pincode")
	@Pattern(regexp = "^\\d{6}$", message = "Pincode must be exactly 6 digits")
	private String pincode;

	@Column(name = "is_active")
	private boolean isActive = true;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	@JsonIgnore
	private User createdBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	@JsonIgnore
	private User updatedBy;

	@Column(name = "profile_completion_percentage")
	@Min(value = 0, message = "Profile completion percentage cannot be negative")
	@Max(value = 100, message = "Profile completion percentage cannot exceed 100")
	private Integer profileCompletionPercentage = 0;

	@Column(name = "profile_picture_url")
	private String profilePictureUrl;

	public User() {
		super();
	}

	public User(UUID userId, UserType userType, String username, String passwordHash, String email, String phone,
			String firstName, String lastName, String aadhaarNumber, String address, String city, String state,
			String pincode, boolean isActive, LocalDateTime lastLogin, LocalDateTime createdAt, LocalDateTime updatedAt,
			User createdBy, User updatedBy, Integer profileCompletionPercentage, String profilePictureUrl) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.aadhaarNumber = aadhaarNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.isActive = isActive;
		this.lastLogin = lastLogin;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.profileCompletionPercentage = profileCompletionPercentage;
		this.profilePictureUrl = profilePictureUrl;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getProfileCompletionPercentage() {
		return profileCompletionPercentage;
	}

	public void setProfileCompletionPercentage(Integer profileCompletionPercentage) {
		this.profileCompletionPercentage = profileCompletionPercentage;
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

}
