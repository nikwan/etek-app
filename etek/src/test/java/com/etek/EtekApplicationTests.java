package com.etek;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.etek.domain.Employee;
import com.etek.domain.SearchModel;
import com.etek.dto.SearchDTOWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.out;


/**
 * @author nikhil
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EtekApplicationTests {
	
	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
    private ObjectMapper mapper;

	@Test
	void shouldListAllEmployee() {
		ParameterizedTypeReference<List<Employee>> b = new ParameterizedTypeReference<List<Employee>>(){};
		ResponseEntity<List<Employee>> res = restTemplate.exchange("/employee/", HttpMethod.GET, null, b);
		
		out.println(res.getBody().size());
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	void shouldSearchEmployeByGivenId() {
		
		ResponseEntity<Employee> forEntity = restTemplate.getForEntity("/employee/1", Employee.class);
		
		out.println(forEntity.getBody());
		
	}
	
	@Test
	void shouldListAllEmployeeWithPaging() throws JsonProcessingException {
		SearchModel model = new SearchModel();
		model.setPageNumber(1);
		model.setPageSize(10);
		model.setQ("a");
		
		HttpEntity<String> stringHttpEntity = getStringHttpEntity(model);
		
		ResponseEntity<SearchDTOWrapper> res = restTemplate.postForEntity("/employee/list", stringHttpEntity, SearchDTOWrapper.class);
		
		out.println(res.getBody());
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	private HttpEntity<String> getStringHttpEntity(Object object) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonMeterData = mapper.writeValueAsString(object);
        return (HttpEntity<String>) new HttpEntity(jsonMeterData, headers);
    }

}
