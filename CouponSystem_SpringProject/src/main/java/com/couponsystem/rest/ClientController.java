package com.couponsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.couponsystem.exceptions.CouponSystemException;

@Service
public abstract class ClientController {
	
	@Autowired
	public ClientController() {
	}

	public abstract ResponseEntity<?> ClientLogin(String email, String password) throws CouponSystemException;

	public abstract ResponseEntity<?> ClientLogout() throws CouponSystemException;
	
}