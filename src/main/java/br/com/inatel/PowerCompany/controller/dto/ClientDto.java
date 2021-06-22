package br.com.inatel.PowerCompany.controller.dto;

import org.springframework.data.domain.Page;

import br.com.inatel.PowerCompany.model.Client;

public class ClientDto {
	
	private Long id;
	private String name;
	
	public ClientDto(Client client) {
		this.id = client.getId();
		this.name = client.getName();
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public static Page<ClientDto> converter(Page<Client> clients) {
		return clients.map(ClientDto::new);
	}
}
