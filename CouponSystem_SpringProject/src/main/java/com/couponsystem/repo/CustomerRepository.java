package com.couponsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	int findIdByEmailAndPassword(String email, String password);

	Customer findCustomerByEmailAndPassword(String email, String password);
}
