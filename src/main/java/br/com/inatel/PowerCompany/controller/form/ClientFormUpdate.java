package br.com.inatel.PowerCompany.controller.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.PowerCompany.model.Client;
import br.com.inatel.PowerCompany.repository.ClientRepository;

public class ClientFormUpdate {
	
	@NotNull @NotEmpty
	private String address;
	
	@NotNull @NotEmpty
	private String district;
	
	@NotNull @NotEmpty
	private String number;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Client update(Long id, ClientRepository clientRepository) {
		Client client = clientRepository.findById(id).get();
		client.setAddress(this.address);
		client.setDistrict(this.district);
		client.setNumber(this.number);
		return client;
	}
	
	
}
