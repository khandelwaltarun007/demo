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

import com.example.demo.model.User;
import com.example.demo.service.IUserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserResource.class)
@WithMockUser
public class UserResourceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IUserService userService;
	
	@Test
	public void createUser() throws Exception {
		User user = new User("Admin", "admin@vodafone.com", 989878989L, "admin", "ADMIN", "admin", true);
		Mockito.when(userService.createUser(Mockito.any())).thenReturn(user);
		String str = "{\"name\":\"Tarun\",\"email\":\"tarun@vodafone.com\",\"contactNo\":12345678}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:8080/user")
				.accept(MediaType.APPLICATION_JSON).content(str)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void updateUser() throws Exception {
		User user = new User("Admin", "admin@vodafone.com", 989878989L, "admin", "ADMIN", "admin", true);
		Mockito.when(userService.createUser(Mockito.any())).thenReturn(user);
		String str = "{\"id\":1,\"name\":\"Tarun\",\"email\":\"tarun@vodafone.com\",\"contactNo\":12345678}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("http://localhost:8080/user")
				.accept(MediaType.APPLICATION_JSON).content(str)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
