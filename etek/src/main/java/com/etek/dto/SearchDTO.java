package com.etek.dto;

/**
 * @author nikhil.wankhade
 *
 */
public class SearchDTO {
	
	String sts;
	String name;
	long id;
	String companyName;
	long salary;
	String phoneNumber;
	String email;
	
	public SearchDTO() {
	}
	
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public SearchDTO(String sts, String name, long id, String companyName) {
		super();
		this.sts = sts;
		this.name = name;
		this.id = id;
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "AngularTest [sts=" + sts + ", name=" + name + ", id=" + id + ", companyName=" + companyName + "]";
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
