package br.com.inatel.PowerCompany.config.security;



import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.inatel.PowerCompany.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${powercompany.jwt.expiration}")
	private String expiration;
	
	@Value("${powercompany.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		User logged = (User) authentication.getPrincipal();
		Date today = new Date();
		Date expirationDate = new Date(today.getTime()+ Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("Api PowerCompany")
				.setSubject(logged.getId().toString())
				.setIssuedAt(today)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}
