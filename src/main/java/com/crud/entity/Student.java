package com.crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String rollNo;
	    private String name;
	    private String email;
	    private String address;
		public Student(Long id, String rollNo, String name, String email, String address) {
			super();
			this.id = id;
			this.rollNo = rollNo;
			this.name = name;
			this.email = email;
			this.address = address;
		}
		public Student() {
			super();
			
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getRollNo() {
			return rollNo;
		}
		public void setRollNo(String rollNo) {
			this.rollNo = rollNo;
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
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
 
}
