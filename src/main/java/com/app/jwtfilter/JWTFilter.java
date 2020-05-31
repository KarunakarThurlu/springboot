package com.app.jwtfilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.jwtutil.JWTUtil;
import com.app.service.impl.UserDetailsServiceImpl;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UserDetailsServiceImpl service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationheader=request.getHeader("Authorization");
		String url=request.getRequestURI();
		String url2=url.substring(1,6);

		StringBuffer url3=request.getRequestURL();
		String token=null;
		String userName=null;
		if(authorizationheader!=null && authorizationheader.startsWith("Bearer ")) {
			token=authorizationheader.substring(7);
			userName=jwtUtil.extractUsername(token);
		}
		if(url2.equalsIgnoreCase("admin") && !userName.equals("admin")) {
			try {
				throw new Exception("Access Denied");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else { 
			if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails=service.loadUserByUsername(userName);
				if(jwtUtil.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,userDetails.getUsername(),userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContext strrr=SecurityContextHolder.getContext();
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}
		filterChain.doFilter(request, response);

	}

}
