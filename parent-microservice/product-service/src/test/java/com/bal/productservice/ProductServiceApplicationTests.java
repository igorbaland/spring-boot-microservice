package com.bal.productservice;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.bal.productservice.dto.ProductRequest;
import com.bal.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {


	@Container
	static MySQLContainer MY_SQL_CONTAINER = 
		new MySQLContainer("mysql:8.0.33");
	
	@Autowired
	private MockMvc mockMVC;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
		dynamicPropertyRegistry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
	}
	
	
	
	@Test
	void shouldCreateProduct() throws Exception {
		 ProductRequest productRequest = getProductRequest();
	        String productRequestString = objectMapper.writeValueAsString(productRequest);
	        mockMVC.perform(MockMvcRequestBuilders.post("/api/product")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(productRequestString))
	                .andExpect(status().isCreated());
	        Assertions.assertEquals(1, productRepository.findAll().size());
	    }

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("iPhone 13")
				.description("call")
				.price(BigDecimal.valueOf(1200))
				.build();
		
	}

}
