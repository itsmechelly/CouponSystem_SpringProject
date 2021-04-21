package com.couponsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.couponsystem.exceptions.CouponSystemException;
//TODO DELETE??????????? i think this class is not in use - from the old login technique!
@Service
public abstract class ClientController {
	
	@Autowired
	public ClientController() {
	}

	public abstract ResponseEntity<?> ClientLogin(String email, String password) throws CouponSystemException;

	public abstract ResponseEntity<?> ClientLogout() throws CouponSystemException;
	
}