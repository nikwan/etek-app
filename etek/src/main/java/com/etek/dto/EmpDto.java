package com.etek.dto;

/**
 * @author nikhil
 *
 */
public class EmpDto {
	
	long id;
	String fristName;
	String lastName;
	long salary;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFristName() {
		return fristName;
	}
	public void setFristName(String fristName) {
		this.fristName = fristName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public EmpDto(long id, String fristName, String lastName, long salary) {
		super();
		this.id = id;
		this.fristName = fristName;
		this.lastName = lastName;
		this.salary = salary;
	}
	
	

}
