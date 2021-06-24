package br.com.inatel.PowerCompany.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.PowerCompany.model.Client;
import br.com.inatel.PowerCompany.service.ApiCepService;

public class ClientForm {
	
	@NotNull @NotEmpty
	private String cpf;
	
	@NotNull @NotEmpty
	private String name;
	
	private String cep;
	
	private String address;
	
	private String district;
	
	@NotNull @NotEmpty
	private String number;
	
	public String getCep() {
		return cep;
	}

	public void setCEP(String cep) {
		this.cep = cep;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Client convert() {
		return new Client(cpf,name,cep,address,district,number);
	}

	public boolean usingCEP() {
		if(cep == null || cep.isEmpty() || cep.length() != 8) {
			return false;
		}
		return true;
	}

	public Client convertWithCep(ApiCepService api) {
		Client client= api.consult(this.cep);
		client.setCpf(cpf);
		client.setName(name);
		client.setNumber(number);
		return client;
	}
	
}
