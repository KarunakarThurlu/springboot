package com.app.jwtutil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class JWTUtil{
	
	String secret="oakta";
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		final String userName=extractUsername(token);
		return (userName.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}

	private <T> T extractClaim(String token,Function<Claims, T> claimsResolver) {
		final Claims cliams=extractAllCliams(token);
		return claimsResolver.apply(cliams);
	}

	private  Claims extractAllCliams(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	//===
	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}
	
	
	//====
	
	public String genarateToken(String userName) {
		Map<String,Object> claims=new HashMap<>();
		return createToken(claims,userName);
	}
	private String createToken(Map<String, Object> claims, String userName) {
		// TODO Auto-generated method stub
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 *60 * 10))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
}
