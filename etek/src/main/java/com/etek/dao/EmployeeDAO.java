package com.etek.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etek.entities.Employees;

/**
 * @author nikhil
 *
 */
@Repository
public interface EmployeeDAO extends JpaRepository<Employees, Long>{
	
	@Query(nativeQuery = true, value =  "SELECT * FROM employees  where lower(first_name) like ?% limit ? offset ?")
	List<Employees> searchWithFirstNamePaginate(String query, int limit, int offset);
	
	@Query(nativeQuery = true, value =  "SELECT * FROM employees  where lower(first_name) like ?% limit ? offset ?")
	List<Employees> searchStartWithCompanyNamePaginate(String query, int limit, int offset);
	
	@Query(nativeQuery = true, value =  "SELECT count(1) over() as totalRec FROM employees  where lower(first_name) like ?% limit 1")
	Integer searchTotalRecordsByCompanyName(String query, int limit, int offset);

}
