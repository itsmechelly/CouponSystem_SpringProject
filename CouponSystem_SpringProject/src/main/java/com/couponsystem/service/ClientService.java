package com.couponsystem.service;

import org.springframework.stereotype.Service;

import com.couponsystem.exceptions.CouponSystemException;

@Service
public abstract class ClientService {

//TODO DELETE ((
//	protected final CompanyDbdao companyDbdao;
//	protected final CustomerDbdao customerDbdao;
//	protected final CouponDbdao couponDbdao;
//
//	@Autowired
//	public ClientService(CompanyDbdao companyDbdao, CustomerDbdao customerDbdao, CouponDbdao couponDbdao) {
//		super();
//		this.companyDbdao = companyDbdao;
//		this.customerDbdao = customerDbdao;
//		this.couponDbdao = couponDbdao;
//	}

	public abstract boolean login(String email, String password) throws CouponSystemException;
	
}