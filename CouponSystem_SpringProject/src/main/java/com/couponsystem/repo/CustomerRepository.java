package com.couponsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	boolean  findByEmailAndPassword(String email, String password);//Added to use in Services.login methods;
	Customer findCustomerById(int customerId);//Added to use in customerService;
	boolean existsByEmail(String email);
	Customer findCustomerByEmailAndPassword(String email, String password);//Added to use in LoginManager.login methods;	

}
