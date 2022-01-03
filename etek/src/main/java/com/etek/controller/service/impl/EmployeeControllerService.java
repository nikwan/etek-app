package com.etek.controller.service.impl;

import static java.util.stream.Collectors.toList;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etek.controller.service.IEmployeeControllerService;
import com.etek.dao.EmployeeDAO;
import com.etek.domain.Employee;
import com.etek.domain.SearchModel;
import com.etek.dto.EtekResponse;
import com.etek.dto.SearchDTO;
import com.etek.dto.SearchDTOWrapper;
import com.etek.entities.Employees;
import com.etek.exception.EtekInvalidSearchCriteriaException;

/**
 * @author nikhil
 *
 */
@Service
public class EmployeeControllerService implements IEmployeeControllerService{
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeControllerService.class);
	
	@Autowired
	EmployeeDAO employeeDao;

	@Override
	public Optional<List<Employees>> listEmployees() {
		return Optional.ofNullable(employeeDao.findAll());
	}

	@Override
	public Employees listEmployeeById(long id) {
		return employeeDao.findById(id).orElse(null);
	}

	@Override
	public EtekResponse updateEmployee(Employee emp) {
		Optional<Employees> e = employeeDao.findById(emp.getId());
		EtekResponse etekResponse;
		if(e.isPresent()) {
			Employees employee = e.get();
			employee.setFirstName(emp.getFirstName());
			employee.setLastName(emp.getLastName());
			employee.setPhoneNumber(emp.getPhoneNumber());log.debug("phone:{}", emp.getPhoneNumber());
			employee.setSalary(emp.getSalary());
			employee.setEmail(emp.getEmail());
			//save entity here - nikhilW
			employeeDao.save(employee);
			etekResponse = new EtekResponse(1, "record updated!");
		}else {
			etekResponse = new EtekResponse(0, 100, "error:unable to update employee!");
		}
		return etekResponse;
	}

	@Override
	public EtekResponse insertEmployee(Employee emp) {
		//TODO validation and throw etek exception
		
		employeeDao.save(getEmp(emp));
		
		return new EtekResponse(1, "record inserted!");
	}

	private Employees getEmp(Employee emp) {
		Employees e = new Employees(emp.getFirstName(), emp.getLastName(), emp.getPhoneNumber(), emp.getSalary());
		e.setHireDate(new Date(System.currentTimeMillis()));
		return e;
	}

	@Override
	public EtekResponse deleteEmployee(long id) {
		employeeDao.deleteById(id);
		return new EtekResponse(1, "record deleted!");
	}
	
	@Override
	public SearchDTOWrapper searchWithPaging(SearchModel search){
		log.info("inside searchWithPaging");

		List<SearchDTO> searcList = null;
		
		SearchDTOWrapper w = null;
		
		int limit = 0;
		int offset = 0;
		String query = "";
		int totalRec = 0;
		List<Employees> searchByCompanyName;
		
		try {
			query = search.getQ();
			limit = search.getPageSize(); //10 items per page
			offset = search.getPageNumber() * search.getPageSize(); // 1 * pagePerSize
			
			log.debug("query {}", query);
			log.debug("pageNumber {} pageSize {}", search.getPageNumber(), search.getPageSize());
			log.debug("query {} limit {} offset {}", query, limit, offset);
			
			Optional<String> q = Optional.ofNullable(query);
			
			log.debug("q.isPresent() {}", q.isPresent());
			//log.debug("q.isEmpty() {}", q.get().isEmpty());
						
			if(q.isPresent()) {
				if(query.isEmpty()) throw new EtekInvalidSearchCriteriaException("ERR101:search input can't be empty string!");
			} else {
				throw new EtekInvalidSearchCriteriaException("ERR102:please provide proper search input!");
			}
			
			log.debug("query length:{}", query.length());
			
			searcList = new ArrayList<>();
			
			searchByCompanyName = employeeDao.searchStartWithCompanyNamePaginate(query.toLowerCase(), limit, offset);
			
			if(searchByCompanyName != null & searchByCompanyName.size() > 0 ) {
				totalRec = employeeDao.searchTotalRecordsByCompanyName(query.toLowerCase(), limit, offset);
				log.debug("total records {}", totalRec);
			}
			
			searcList = searchByCompanyName.stream()
					.map((e) -> getSearchDTO(e))
					.collect(toList());
			
			w = new SearchDTOWrapper(searcList, totalRec, 1);
			
		} catch (EtekInvalidSearchCriteriaException e) {
			searcList = new ArrayList<>();
			searcList.add(new SearchDTO("FAIL", e.getMessage(), -1, query));
			w = new SearchDTOWrapper(searcList, totalRec, 1);
		}
		
		
		return w;
	}
	
	SearchDTO getSearchDTO(Employees e) {
		SearchDTO	s = new SearchDTO("OK", e.getFirstName() + " " + e.getLastName(), e.getEmployeeId(), e.getLastName());
		s.setSalary(e.getSalary());
		s.setPhoneNumber(e.getPhoneNumber());
		s.setEmail(e.getEmail());
		
		return s;
	}

}
