package com.etek.controller.service;

import java.util.List;
import java.util.Optional;

import com.etek.domain.Employee;
import com.etek.domain.SearchModel;
import com.etek.dto.EtekResponse;
import com.etek.dto.SearchDTOWrapper;
import com.etek.entities.Employees;

/**
 * @author nikhil
 *
 */
public interface IEmployeeControllerService {

	Optional<List<Employees>> listEmployees();


	EtekResponse updateEmployee(Employee emp);

	EtekResponse insertEmployee(Employee emp);

	Employees listEmployeeById(long id);


	EtekResponse deleteEmployee(long id);


	SearchDTOWrapper searchWithPaging(SearchModel search);

}
