package com.couponsystem.security;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
