package com.couponsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	int findIdByEmailAndPassword(String email, String password);

	Customer findCustomerByEmailAndPassword(String email, String password);
	
	//****************************
	boolean  findByEmailAndPassword(String email, String password);//Added to use in Services.login methods;
	Customer findCustomerById(int customerId);//Added to use in customerService;
	Customer findCustomerByEmail(String email);//Added to use in adminService.addCustomer;

}
