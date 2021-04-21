package com.couponsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.beans.LoginForm;
import com.couponsystem.beans.LoginResponse;
import com.couponsystem.exceptions.LogException;

import com.couponsystem.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private final LoginService loginService;

	
	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {		

		HttpHeaders responseHeaders = new HttpHeaders();

		try {
			String token = loginService.login(loginForm);
			responseHeaders.set("CouponSystem_Header", token);
			
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setToken(token);
			loginResponse.setType(loginForm.getClientType().toString());
			
			return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);

		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

}