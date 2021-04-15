package com.couponsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.couponsystem.beans.LoginForm;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.security.Session;
import com.couponsystem.security.SessionContext;

@Service
@Lazy
public class LoginService {
	
	private SessionContext sessionContext;
	private AdminService adminService;
	private CompanyService companyService;
	private CustomerService customerService;
	
	@Autowired
	public LoginService(SessionContext sessionContext, AdminService adminService, CompanyService companyService,
			CustomerService customerService) {
		super();
		this.sessionContext = sessionContext;
		this.adminService = adminService;
		this.companyService = companyService;
		this.customerService = customerService;
	}
	
	public String login(LoginForm loginForm) throws LogException {		
		
		switch (loginForm.getClientType()) {
		
		case ADMIN:
			
			if (adminService.login(loginForm.getEmail(), loginForm.getPasswoed())) {
				Session session = sessionContext.createSession();
				session.setAttribute(loginForm.getClientType(), adminService);
				return session.token;
			}
			
		case COMPANY:
			
//			companyService = ctx.getBean(CompanyService.class);
			if (companyService.login(loginForm.getEmail(), loginForm.getPasswoed())) {
				int companyId = companyService.findCompanyIdByEmailAndPassword("comp1Email@comp.com", "1111");
				companyService.setCompanyId(companyId);
				Session session = sessionContext.createSession();
				session.setAttribute(loginForm.getClientType(), companyService);
				return session.token;
			}
			
		case CUSTOMER:
			
//			customerService = ctx.getBean(CustomerService.class);
			if (customerService.login(loginForm.getEmail(), "1111")) {
				int customerId = customerService.findCustomerIdByEmailAndPassword("cust1@cust.com", "1111");
				customerService.setCustomerId(customerId);
				Session session = sessionContext.createSession();
				session.setAttribute(loginForm.getClientType(), customerService);
				return session.token;
			}
			
		default:
			throw new LogException();
		}
		
	}
}
