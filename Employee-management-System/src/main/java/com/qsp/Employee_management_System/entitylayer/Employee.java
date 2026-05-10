package com.qsp.Employee_management_System.entitylayer;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "my_seq")
	@SequenceGenerator(name="my_seq",initialValue = 1234,allocationSize = 1)
	private int empid;
	private String name;
	private String email;
	private double salary;
	private String department;
	private String location;
	@CreationTimestamp
	private LocalDateTime creation;
	@UpdateTimestamp
	private LocalDateTime updation;
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDateTime getCreation() {
		return creation;
	}
	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}
	public LocalDateTime getUpdation() {
		return updation;
	}
	public void setUpdation(LocalDateTime updation) {
		this.updation = updation;
	}

}
