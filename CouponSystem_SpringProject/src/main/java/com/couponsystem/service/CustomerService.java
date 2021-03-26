package com.couponsystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.Customer;
import com.couponsystem.dbdao.CompanyDbdao;
import com.couponsystem.dbdao.CouponDbdao;
import com.couponsystem.dbdao.CustomerDbdao;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.exceptions.PurchaseCouponException;

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
	public boolean login(String email, String password) {
		Customer customer = customerDbdao.findCustomerByEmailAndPassword(email, password);
		if (customer != null)
			return true;
		return false;
	}

	public int findCustomerIdByEmailAndPassword(String email, String password) {
		Customer cForCustomerId = customerDbdao.findCustomerByEmailAndPassword(email, password);
		return cForCustomerId.getId();
	}
//	------------------------------------------------------------------------------------------------------------

	public Coupon purchaseCoupon(Coupon coupon) throws PurchaseCouponException, LogException, NotFoundException {

			
		Coupon coupFromDb = couponDbdao.findById(coupon.getId());
		Customer custFromDb = customerDbdao.findCustomerById(customerId);
		List<Coupon> coupListFromDb = couponDbdao.getCouponsByCustomersId(customerId);
		
		if (couponDbdao.existsByCustomersIdAndTitle(customerId, coupon.getTitle())) 
			throw new PurchaseCouponException("Purchasing this type of coupon is limited to one use only. you are welcome to choose another coupon.");
		if (coupFromDb.getAmount() < 1)
			throw new PurchaseCouponException("Coupon out of stock, you are welcome to choose another coupon.");
		if (coupFromDb.getEndDate().before(java.sql.Date.valueOf(LocalDate.now())))
			throw new PurchaseCouponException("This coupon has expired, you are welcome to choose another coupon.");
		
		coupFromDb.setAmount(coupon.getAmount() - 1);
		coupListFromDb.add(coupFromDb);
		custFromDb.setCoupons(coupListFromDb);
		customerDbdao.updateCustomer(custFromDb);

		return coupFromDb;
	}

	public List<Coupon> getAllCoupons() throws NotFoundException, LogException {

		List<Coupon> customerFromDb = couponDbdao.getCouponsByCustomersId(customerId);

		if (customerFromDb.isEmpty())
			throw new NotFoundException("coupons details.");
		
		return customerFromDb;
	}

	public List<Coupon> getAllCouponsByCategory(CouponCategory category) throws NotFoundException, LogException {

		List<Coupon> coupFromDb = couponDbdao.getCouponsByCustomersIdAndCategory(customerId, category);

		if (coupFromDb.isEmpty())
			throw new NotFoundException("coupons from category type " + category + ".");
		
		return coupFromDb;
	}

	public List<Coupon> getAllCouponsUnderMaxPrice(double maxPrice) throws LogException, NotFoundException {

		List<Coupon> coupFromDb = couponDbdao.getCouponsByCustomersIdAndPriceLessThan(customerId, maxPrice);

		if (coupFromDb.isEmpty())
			throw new NotFoundException("coupons under price ", maxPrice);
		
		return coupFromDb;
	}

	public Customer getCustomerDetails() throws NotFoundException, LogException {

		Optional<Customer> customerFromDb = Optional.of(customerDbdao.findCustomerById(customerId));

		if (customerFromDb.isEmpty())
			throw new NotFoundException("customer details.");
		
		return customerDbdao.findCustomerById(customerId);
	}

}