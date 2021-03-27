package com.couponsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.couponsystem.dbdao.CompanyDbdao;
//import com.couponsystem.dbdao.CouponDbdao;
//import com.couponsystem.dbdao.CustomerDbdao;
import com.couponsystem.exceptions.CouponSystemException;

@Service
public abstract class ClientService {

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
