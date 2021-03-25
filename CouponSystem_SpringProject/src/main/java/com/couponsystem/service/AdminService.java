package com.couponsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.Customer;
import com.couponsystem.dbdao.CompanyDbdao;
import com.couponsystem.dbdao.CouponDbdao;
import com.couponsystem.dbdao.CustomerDbdao;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;
import com.couponsystem.exceptions.NotFoundException;

@Service
public class AdminService extends ClientService {

	@Autowired
	public AdminService(CompanyDbdao companyDbdao, CustomerDbdao customerDbdao, CouponDbdao couponDbdao) {
		super(companyDbdao, customerDbdao, couponDbdao);
	}
	
//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) {
		if (email.equalsIgnoreCase("admin@admin.com") && password.equals("admin")) {
			return true;
		}
		return false;
	}
//	------------------------------------------------------------------------------------------------------------

	public Company addCompany(Company company) throws AlreadyExistException, LogException {
		
		if (companyDbdao.existsByEmail(company.getEmail()))
			throw new AlreadyExistException("Company email ", company.getEmail());
		if (companyDbdao.existsByName(company.getName()))
			throw new AlreadyExistException("Company name ", company.getName());

		return companyDbdao.addCompany(company);
	}

	public Company updateCompany(Company company) throws NotFoundException, NotAllowedException, LogException {
		
		Optional<Company> compFromDb = companyDbdao.findById(company.getId());
		
		if (compFromDb.isEmpty()) {
			throw new NotFoundException("company details.");
		}
		if (!company.getName().equalsIgnoreCase(compFromDb.get().getName())) {
			throw new NotAllowedException("company name to", company.getName());
		}
		
		List<Coupon> coupListFromDb = couponDbdao.findByCompanyId(compFromDb.get().getId());
		for (Coupon coupons : coupListFromDb) {
			company.getCoupons().add(coupons);
		}
		
		return companyDbdao.updateCompany(company);
	}

	public String deleteCompany(int companyId) throws NotFoundException, LogException {
		
		if (!companyDbdao.existsById(companyId))
			throw new NotFoundException("company details.");
		companyDbdao.deleteCompany(companyId);
		return "Request accomplished, company with id number " + companyId + " has been deleted from the system.";
	}

	public Company getOneCompanyById(int companyId) throws NotFoundException, LogException {
		
		if (!companyDbdao.existsById(companyId))
			throw new NotFoundException("company details.");
		return companyDbdao.findCompanyById(companyId);
	}

	public List<Company> getAllCompanies() throws NotFoundException, LogException {
		Optional<List<Company>> compFromDb = Optional.of(companyDbdao.findAllCompanies());
		if (compFromDb.isEmpty()) {
			throw new NotFoundException("companies details.");
		}
		return companyDbdao.findAllCompanies();
	}

	public Customer addCustomer(Customer customer) throws AlreadyExistException, LogException {
		
		if (customerDbdao.existsByEmail(customer.getEmail()))
			throw new AlreadyExistException("Customer email ", customer.getEmail());

		return customerDbdao.addCustomer(customer);
	}

	public Customer updateCustomer(Customer customer) throws NotFoundException, LogException {
		Optional<Customer> custFromDb = Optional.of(customerDbdao.findCustomerById(customer.getId()));
		if (custFromDb.isEmpty()) {
			throw new NotFoundException("customer details.");
		}

		List<Coupon> coupListFromDb = custFromDb.get().getCoupons();
		for (Coupon coupons : coupListFromDb) {
			customer.getCoupons().add(coupons);
		}
		return customerDbdao.updateCustomer(customer);
	}

	public String deleteCustomer(int customerId) throws NotFoundException, LogException {
		
		if (!customerDbdao.existsById(customerId))
			throw new NotFoundException("customer details.");
		customerDbdao.deleteCustomer(customerId);
		return "Request accomplished, customer with id number " + customerId + " has been deleted from the system.";
	}

	public Customer getOneCustomerById(int customerId) throws NotFoundException, LogException {

		if (!customerDbdao.existsById(customerId))
			throw new NotFoundException("customer details.");
		return customerDbdao.findCustomerById(customerId);
	}

	public List<Customer> getAllCustomers() throws NotFoundException, LogException {
		Optional<List<Customer>> custListFromDb = Optional.of(customerDbdao.findAllCustomers());
		if (custListFromDb.isEmpty()) {
			throw new NotFoundException("customers details.");
		}
		return customerDbdao.findAllCustomers();
	}

}
