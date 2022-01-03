package com.etek.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.etek.controller.service.IEmployeeControllerService;
import com.etek.domain.Employee;
import com.etek.domain.SearchModel;
import com.etek.dto.EtekResponse;
import com.etek.dto.SearchDTOWrapper;
import com.etek.entities.Employees;
import com.etek.exception.EtekInvalidSearchCriteriaException;

/**
 * @author nikhil
 *
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	IEmployeeControllerService empService;
	
	
	@PostMapping("/store")
	public EtekResponse store(@RequestBody Employee emp) {
		return empService.insertEmployee(emp);
	}
	
	@PostMapping
	public EtekResponse insert(@RequestBody Employee emp) {
		return empService.insertEmployee(emp);
	}
	
	@GetMapping("/{id}")
	public Employees search(@PathVariable int id) {
		return empService.listEmployeeById(id);
	}
	
	@DeleteMapping("/{id}")
	public EtekResponse delete(@PathVariable int id) {
		return empService.deleteEmployee(id);
	}
	
	@PutMapping
	public EtekResponse update(@RequestBody Employee emp) {
		return empService.updateEmployee(emp);
	}
	
	@GetMapping
	public ResponseEntity list() {
		Optional<List<Employees>> listEmployeesOptional = empService.listEmployees();
		return listEmployeesOptional.isPresent() ? ResponseEntity.ok(listEmployeesOptional.get()) : ResponseEntity.notFound().build();
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public SearchDTOWrapper searchWithPaging(@RequestBody SearchModel q) throws EtekInvalidSearchCriteriaException {
		
		return empService.searchWithPaging(q);
	}

}
