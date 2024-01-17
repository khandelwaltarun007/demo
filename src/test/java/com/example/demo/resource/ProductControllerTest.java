package com.example.demo.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.Product;
import com.example.demo.service.IProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductResource.class)
@WithMockUser
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IProductService productService;
	
	@Test
	public void createProduct() throws Exception {
		Product product = new Product("Apple", 987, 89.43);
		Mockito.when(productService.createProduct(Mockito.any())).thenReturn(product);
		String productJsonString ="{\"productName\":\"IPhone\",\"quantity\":200,\"price\":145000}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:8080/product")
				.accept(MediaType.APPLICATION_JSON).content(productJsonString)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void updateProduct() throws Exception {
		Product product = new Product(1L,"Apple", 987, 89.43);
		Mockito.when(productService.updateProduct(Mockito.any())).thenReturn(product);
		String productJsonString = "{\"id\":1,\"productName\":\"IPhone\",\"quantity\":200,\"price\":145000}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("http://localhost:8080/product")
				.accept(MediaType.APPLICATION_JSON).content(productJsonString)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
