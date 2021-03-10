package com.couponsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.couponsystem.beans.Customer;
import com.couponsystem.dbdao.CompanyDbdao;
import com.couponsystem.dbdao.CouponDbdao;
import com.couponsystem.dbdao.CustomerDbdao;
import com.couponsystem.exceptions.CouponSystemException;

import lombok.Setter;

@Service
@Scope("prototype")
@Setter
public class CustomerService extends ClientService {

	public int customerId;

	@Autowired
	public CustomerService(CompanyDbdao companyDbdao, CustomerDbdao customerDbdao, CouponDbdao couponDbdao) {
		super(companyDbdao, customerDbdao, couponDbdao);
	}

//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		Customer customer = customerDbdao.findCustomerByEmailAndPassword(email, password);
		if (customer != null)
			return true;
		return false;
	}

	public int findIdByEmailAndPassword(String email, String password) {
		return customerDbdao.findIdByEmailAndPassword(email, password);
	}
//	------------------------------------------------------------------------------------------------------------

}
