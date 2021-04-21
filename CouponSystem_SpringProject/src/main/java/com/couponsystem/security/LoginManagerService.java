package com.couponsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.couponsystem.enums.ClientType;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.service.AdminService;
import com.couponsystem.service.CompanyService;
import com.couponsystem.service.CustomerService;
//TODO DELETE???
@Service
@Lazy
public class LoginManagerService {

	private ApplicationContext ctx;
	private TokenManager tokenManager;
	private final AdminService adminService;
	private CompanyService companyService;
	private CustomerService customerService;
	
	@Autowired
	public LoginManagerService(ApplicationContext ctx, TokenManager tokenManager, AdminService adminService,
			CompanyService companyService, CustomerService customerService) {
		super();
		this.ctx = ctx;
		this.tokenManager = tokenManager;
		this.adminService = adminService;
		this.companyService = companyService;
		this.customerService = customerService;
	}
	
	public String login(String email, String password, ClientType clientType) throws LogException {

		switch (clientType) {
		
		case ADMIN:
			if (adminService.login(email, password)) {
				return tokenManager.addToken(adminService);
			}
			
		case COMPANY:
			companyService = ctx.getBean(CompanyService.class);
			if (companyService.login(email, password)) {
				int companyId = companyService.findCompanyIdByEmailAndPassword(email, password);
				companyService.setCompanyId(companyId);
				return tokenManager.addToken(companyService);
			}
			
		case CUSTOMER:
			customerService = ctx.getBean(CustomerService.class);
			if (customerService.login(email, password)) {
				int customerId = customerService.findCustomerIdByEmailAndPassword(email, password);
				customerService.setCustomerId(customerId);
				return tokenManager.addToken(customerService);
			}
		default:
			throw new LogException();
		}
	}

}
