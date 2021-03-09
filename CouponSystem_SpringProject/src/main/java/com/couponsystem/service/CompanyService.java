package com.couponsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.dbdao.CompanyDbdao;
import com.couponsystem.dbdao.CouponDbdao;
import com.couponsystem.dbdao.CustomerDbdao;
import com.couponsystem.exceptions.CouponSystemException;

@Service
public class CompanyService extends ClientService {

	@Autowired
	public CompanyService(CompanyDbdao companyDbdao, CustomerDbdao customerDbdao, CouponDbdao couponDbdao) {
		super(companyDbdao, customerDbdao, couponDbdao);
	}
	
//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		// TODO Auto-generated method stub
		return false;
	}

//	------------------------------------------------------------------------------------------------------------

}
