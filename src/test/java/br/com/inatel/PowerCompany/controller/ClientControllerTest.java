package br.com.inatel.PowerCompany.controller;


import java.net.URI;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
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
		
		mock.perform(MockMvcRequestBuilders.post(uri).content(json.toJSONString()).header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBcGkgUG93ZXJDb21wYW55Iiwic3ViIjoiMSIsImlhdCI6MTYyNDU0MjE4NSwiZXhwIjoxNjI0NjI4NTg1fQ.nUyiLHdfXCZ0JN8wL90W3g9w03c25jn42T_efrz2-bQ")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201));
		
	}
	
	@Test 
	void shouldGetSomeClients() throws Exception{
		URI uri = new URI("/clients");
		
		mock.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("name")));
	}
	

}
