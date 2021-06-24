package br.com.inatel.PowerCompany.controller;


import static org.hamcrest.CoreMatchers.containsString;

import java.net.URI;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.inatel.PowerCompany.model.Client;
import net.minidev.json.JSONObject;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	private static String token;
	
	
	@Test
	void shouldTakeAToken() throws Exception{
		URI uri = new URI("/auth");
		
		JSONObject json = new JSONObject();
		json.put("email", "iagho.cristian@inatel.br");
		json.put("password", "123456");
		
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.post(uri).content(json.toJSONString())
		.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		
		token = response.substring(26,(response.length()-2));
		System.out.println(token);
		System.out.println(response);
	}

	
	@Test
	void shouldCreateAClient() throws Exception {	
		
		URI uri = new URI("/clients");
		Client client = new Client("18765373908","Teste","36198672" ,"Rua Teste", "Distrito Teste", "1276");
		
		JSONObject json = new JSONObject();
		json.put("cpf", client.getCpf());
		json.put("name", client.getName());
		json.put("address", client.getAddress());
		json.put("district", client.getDistrict());
		json.put("number", client.getNumber());
		
		
		mock.perform(MockMvcRequestBuilders.post(uri).content(json.toJSONString()).header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBcGkgUG93ZXJDb21wYW55Iiwic3ViIjoiMSIsImlhdCI6MTYyNDU1MzgxMywiZXhwIjoxNjI0NjQwMjEzfQ.7rrQ0-4i_KS8x4EzaKETJ92NtINjgETHcNUK-Q_WKyA")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201));
		
	}
	
	@Test 
	void shouldGetSomeClients() throws Exception{
		URI uri = new URI("/clients");
		
		mock.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("name")));
	}
	
	@Test
	void shouldntCreateAClient() throws Exception {	
		
		URI uri = new URI("/clients");
		Client client = new Client("18765373908","Teste","36198672" ,"Rua Teste", "Distrito Teste", "1276");
		
		JSONObject json = new JSONObject();
		json.put("cpf", client.getCpf());
		json.put("address", client.getAddress());
		json.put("district", client.getDistrict());
		json.put("number", client.getNumber());
		
		mock.perform(MockMvcRequestBuilders.post(uri).content(json.toJSONString()).header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBcGkgUG93ZXJDb21wYW55Iiwic3ViIjoiMSIsImlhdCI6MTYyNDU1MzgxMywiZXhwIjoxNjI0NjQwMjEzfQ.7rrQ0-4i_KS8x4EzaKETJ92NtINjgETHcNUK-Q_WKyA")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(400));
		
	}

}
