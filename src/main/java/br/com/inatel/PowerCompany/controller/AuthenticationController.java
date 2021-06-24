package br.com.inatel.PowerCompany.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.PowerCompany.config.security.TokenService;
import br.com.inatel.PowerCompany.controller.dto.TokenDto;
import br.com.inatel.PowerCompany.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> auth(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken loginData = form.convert();
		
		try {
			Authentication authentication = authManager.authenticate(loginData);
			String token = tokenService.generateToken(authentication);
			
			return ResponseEntity.ok(new TokenDto("Bearer",token));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
