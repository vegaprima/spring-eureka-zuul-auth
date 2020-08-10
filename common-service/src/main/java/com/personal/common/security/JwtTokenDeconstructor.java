package com.personal.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenDeconstructor {

	@Autowired
	JwtConfig jwtConfig;

	public String getAuthenticatedUserFromHeader(String header) {
		String token = header.replace(jwtConfig.getPrefix(), "");

		try { // exceptions might be thrown in creating the claims if for example the token is
				// expired

			// 4. Validate the token
			Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret().getBytes()).parseClaimsJws(token)
					.getBody();

			String username = claims.getSubject();
			return username;
		} catch (Exception e) {
			return "";
		}
	}
}
