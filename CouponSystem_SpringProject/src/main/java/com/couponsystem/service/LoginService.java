package com.couponsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.couponsystem.beans.LoginForm;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.security.Session;
import com.couponsystem.security.SessionContext;

@Service
@Lazy
public class LoginService {
	
	public ApplicationContext ctx;
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
//			adminService = ctx.getBean(AdminService.class);
//			AdminService adminService = ctx.getBean(AdminService.class);
			if (adminService.login(loginForm.getEmail(), loginForm.getPassword())) {
				
				Session session = sessionContext.createSession();
				session.setAttribute("ClientType", loginForm.getClientType());
				session.setAttribute("ADMIN", adminService);
				return session.token;
			}
			
		case COMPANY:
			
//			companyService = ctx.getBean(CompanyService.class);
//			CompanyService companyService = ctx.getBean(CompanyService.class);
			if (companyService.login(loginForm.getEmail(), loginForm.getPassword())) {
				
				int companyId = companyService.findCompanyIdByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
				companyService.setCompanyId(companyId);
				
				Session session = sessionContext.createSession();
				session.setAttribute("ClientType", loginForm.getClientType());
				session.setAttribute("COMPANY", companyService);
				return session.token;
			}
			
		case CUSTOMER:
			
//			customerService = ctx.getBean(CustomerService.class);
//			CustomerService customerService = ctx.getBean(CustomerService.class);
			if (customerService.login(loginForm.getEmail(), loginForm.getPassword())) {
				
				int customerId = customerService.findCustomerIdByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
				customerService.setCustomerId(customerId);
				
				Session session = sessionContext.createSession();
				session.setAttribute("ClientType", loginForm.getClientType());
				session.setAttribute("CUSTOMER", customerService);
				return session.token;
			}
			
		default:
			throw new LogException();
		}
	}
	
}