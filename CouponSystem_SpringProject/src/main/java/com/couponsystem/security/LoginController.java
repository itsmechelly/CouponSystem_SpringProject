package com.couponsystem.security;

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

	//TODO - the token variable below is only for CLR testing, not for production!
	public String token;
	private final LoginService loginService;
	
	public LoginController(String token, LoginService loginService) {
		super();
		this.token = token;
		this.loginService = loginService;
	}

	public String getToken() {
		return this.token;
	}


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {		

		HttpHeaders responseHeaders = new HttpHeaders();

		try {
			
			this.token = loginService.login(loginForm);
			
			responseHeaders.set("CouponSystem_Header", this.token);
			
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setToken(this.token);
			loginResponse.setType(loginForm.getClientType());
			
			return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);

		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

}