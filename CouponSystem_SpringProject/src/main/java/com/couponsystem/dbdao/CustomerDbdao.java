package com.couponsystem.dbdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.couponsystem.beans.Customer;
import com.couponsystem.repo.CustomerRepository;

@Repository
public class CustomerDbdao {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerDbdao(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

//	------------------------------------------------------------------------------------------------------------

	public int findIdByEmailAndPassword(String email, String password) {
		return customerRepository.findIdByEmailAndPassword(email, password);
	}

	public Customer findCustomerByEmailAndPassword(String email, String password) {
		return customerRepository.findCustomerByEmailAndPassword(email, password);
	}
}
