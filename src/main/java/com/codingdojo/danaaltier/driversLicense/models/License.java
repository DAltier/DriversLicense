package com.codingdojo.danaaltier.driversLicense.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="licenses")
public class License {
 
	// Attributes
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String number;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiration_date;
    @Column
    private String state;
    @Column(updatable=false)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date createdAt;
    @Column
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date updatedAt;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="person_id",nullable=false)
    private Person person;
	 
	
    // Constructors
	public License() { 
		
	}
	public License(String number, Date expiration_date, String state, Person person) {
		this.number = number;
		this.expiration_date = expiration_date;
		this.state = state;
	}
	
	
	// Getters
	public Long getId() {
		return id;
	}
	public String getNumber() {
		return number;
	}
	public Date getExpiration_date() {
		return expiration_date;
	}
	public String getState() {
		return state;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public Person getPerson() {
		return person;
	}
	
	
	// Setters
	public void setId(Long id) {
		this.id = id;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	 
	
	// Methods
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}


