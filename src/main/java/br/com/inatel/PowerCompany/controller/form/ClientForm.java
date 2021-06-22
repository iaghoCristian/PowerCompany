package br.com.inatel.PowerCompany.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.PowerCompany.model.Client;

public class ClientForm {
	
	@NotNull @NotEmpty
	private String cpf;
	
	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String address;
	
	@NotNull @NotEmpty
	private String district;
	
	@NotNull @NotEmpty
	private String number;
	
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

	public Client converter() {
		return new Client(cpf,name,address,district,number);
	}
	
	
}
