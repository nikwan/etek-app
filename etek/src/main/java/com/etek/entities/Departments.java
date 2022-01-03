package com.etek.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nikhil
 *
 */
@Entity
@Table(name = "departments")
public class Departments {
	
	@Column(name = "department_id")
	@Id
	long departmentId;
	
	@Column(name = "manager_id")
	long managerId;
	
	@Column(name = "department_name")
	String departmentName;

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Departments(long departmentId, long managerId,
			String departmentName) {
		super();
		this.departmentId = departmentId;
		this.managerId = managerId;
		this.departmentName = departmentName;
	}
	
	

}
