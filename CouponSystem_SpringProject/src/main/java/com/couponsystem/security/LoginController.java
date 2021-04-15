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
import com.couponsystem.enums.ClientType;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.service.AdminService;
import com.couponsystem.service.CompanyService;
import com.couponsystem.service.CustomerService;
import com.couponsystem.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private SessionContext sessionContext;
	private final AdminService adminService;
	private CompanyService companyService;
	private CustomerService customerService;
	private LoginService loginService;

	
	public LoginController(LoginService loginService, SessionContext sessionContext, AdminService adminService, CompanyService companyService, CustomerService customerService) {
		super();
		this.sessionContext = sessionContext;
		this.adminService = adminService;
		this.companyService = companyService;
		this.customerService = customerService;	
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