package br.com.inatel.PowerCompany.controller.dto;

public class TokenDto {

	private String type;
	private String token;
	
	public TokenDto(String type, String token) {
		this.type = type;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	
}
