package com.couponsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.couponsystem.beans.Company;
import com.couponsystem.dbdao.CompanyDbdao;
import com.couponsystem.dbdao.CouponDbdao;
import com.couponsystem.dbdao.CustomerDbdao;
import com.couponsystem.exceptions.CouponSystemException;

import lombok.Setter;

@Service
@Scope("prototype")
@Setter
public class CompanyService extends ClientService {

	public int companyId;

	@Autowired
	public CompanyService(CompanyDbdao companyDbdao, CustomerDbdao customerDbdao, CouponDbdao couponDbdao) {
		super(companyDbdao, customerDbdao, couponDbdao);
	}
	
//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		Company company = companyDbdao.findCompanyByEmailAndPassword(email, password);
		if (company != null)
			return true;
		return false;
	}

	public int findIdByEmailAndPassword(String email, String password) {
		return companyDbdao.findIdByEmailAndPassword(email, password);
	}


//	------------------------------------------------------------------------------------------------------------

}
