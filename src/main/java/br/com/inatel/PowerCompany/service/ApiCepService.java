package br.com.inatel.PowerCompany.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.inatel.PowerCompany.controller.dto.CepDto;
import br.com.inatel.PowerCompany.model.Client;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiCepService {


	public Client consult(String cep) {
		
		Client client = new Client();
		try {
			log.info("Comunicando com a API");
			String url = "https://viacep.com.br/ws/"+cep+"/json/";
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");
	        
	        if (conn.getResponseCode() != 200) {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
            }
	     
	        BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String output = "";
	        String line;
	        while ((line = buffer.readLine()) != null){

	        	output+= line + "\n";

	        }
	        conn.disconnect();	       
	        
	        ObjectMapper mapper = new ObjectMapper();
	        CepDto cepDto = mapper.readValue(output, CepDto.class);
	        client = cepDto.convert();
	        
			 
		}catch(Exception e){
			log.warn("Ocorreu um erro ao comunicar com API");
		}
		
		return client;
	}
}
