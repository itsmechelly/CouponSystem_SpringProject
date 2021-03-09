package com.couponsystem.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.exceptions.CouponSystemException;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController extends ClientController {
	
//	------------------------------------------------------------------------------------------------------------

	@Override
	@PostMapping("/login")
	public ResponseEntity<?> ClientLogin(String email, String password) throws CouponSystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> ClientLogout() throws CouponSystemException {
		// TODO Auto-generated method stub
		return null;
	}

//	------------------------------------------------------------------------------------------------------------

}
