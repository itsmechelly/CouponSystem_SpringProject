package com.couponsystem.security;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.service.ClientService;

@Component
public class TokenManager {

	private String token;
	private HashMap<String, CustomSession> tokensInMemory;

	@Autowired
	public TokenManager(String token, HashMap<String, CustomSession> tokensInMemory) {
		super();
		this.token = token;
		this.tokensInMemory = tokensInMemory;
	}

	public String addToken(ClientService clientService) {
		this.token = UUID.randomUUID().toString();
		tokensInMemory.put(token, new CustomSession(clientService, new Date(Instant.now().toEpochMilli())));
		return token;
	}
	
	public ClientService getClientService(String token) {
		return tokensInMemory.getOrDefault(token, null).getClientService();
	}
	
	public boolean isTokenExist(String token) throws NotFoundException {
		CustomSession customSession = tokensInMemory.get(token);
		if (customSession != null) {
			return true;
		}
		throw new NotFoundException("token");
	}
	
	public String tokenForClrTest() throws NotFoundException {
		CustomSession customSession = tokensInMemory.get(this.token);
		if (customSession != null) {
			return this.token;
		}
		throw new NotFoundException("token");
	}

}
