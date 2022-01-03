package com.etek.h2.config;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.etek.dao.EmployeeDAO;
import com.etek.dto.EtekMockDataBean;
import com.etek.entities.Employees;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author nikhil.wankhade
 *
 */
@Service
public class EtekH2MockData {
	
	@Autowired
	EmployeeDAO employeeDao;
	
	private static final Logger log = LoggerFactory.getLogger(EtekH2MockData.class);
	
	@Bean
	public void loadInMemoryData(){
		log.info("inside loadInMemoryData");
		
		log.info("loading data....");
		
		ObjectMapper map = new ObjectMapper();
		
		try (InputStream is = getClass().getResourceAsStream("/MOCK_DATA.json")){
			
			//String f = getClass().getClassLoader().getResource("MOCK_DATA.json").getFile();
			//String f = getClass().getResourceAsStream("MOCK_DATA.json");
			
			//List<MockDataBean> mockList = map.readValue(new File(f), new TypeReference<List<MockDataBean>>(){});
			List<EtekMockDataBean> mockList = map.readValue(is, new TypeReference<List<EtekMockDataBean>>(){});
			
			log.debug("===total {} records loaded===", mockList.size());
			long sal = 1;
			
			List<Employees> entitiesList = mockList.stream()
			.map(e -> getEmp(e))
			.collect(Collectors.toList());
				
			employeeDao.saveAll(entitiesList);
			
			log.info("===data inserted in employees table===");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		log.info("loading data finished....");
		
	}
	
	Employees getEmp(EtekMockDataBean t){
		
		Employees emp = new Employees(t.getFirstName(), t.getLastName(), "9372512233", new Random().nextInt(60000));
		emp.setEmail(t.getEmail());
		//emp.setHireDate(new Date(System.currentTimeMillis()));
		LocalDate ld1 = LocalDate.of(2021, 1, 1);
		LocalDate ld2 = LocalDate.of(2021, 12, 1);
		long fDay = ld1.toEpochDay();
		long tDay = ld2.toEpochDay();
		long random = fDay +  new Random().nextInt((int)(tDay - fDay));
		LocalDate ld = LocalDate.ofEpochDay(random);
		emp.setHireDate(Date.valueOf(ld));
		emp.setDepartmentId(1);
		emp.setManagerId(1);
		
		return emp;
	}

}
