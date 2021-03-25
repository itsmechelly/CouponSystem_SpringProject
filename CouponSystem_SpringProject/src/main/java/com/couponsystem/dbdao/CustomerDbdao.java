package com.couponsystem.dbdao;

import java.util.List;
import java.util.Optional;

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

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Customer customer) {
		return customerRepository.saveAndFlush(customer);
	}

	public void deleteCustomer(int customerId) {
		customerRepository.deleteById(customerId);
	}

	public boolean findByEmailAndPassword(String email, String password) {
		return customerRepository.findByEmailAndPassword(email, password);
	}

	public Customer findCustomerById(int customerId) {
		return customerRepository.findCustomerById(customerId);
	}

	public Optional<Customer> findById(int customerId) {
		return customerRepository.findById(customerId);
	}
	
	public boolean existsByEmail(String email) {
		return customerRepository.existsByEmail(email);
	}

	public Customer findCustomerByEmailAndPassword(String email, String password) {
		return customerRepository.findCustomerByEmailAndPassword(email, password);
	}

	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}
	
}