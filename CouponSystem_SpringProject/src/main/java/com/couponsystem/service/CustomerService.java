package com.couponsystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
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

	public Coupon purchaseCoupon(Coupon coupon) throws PurchaseCouponException, LogException {

		Customer custFromDb = customerDbdao.findCustomerById(customerId);
		List<Coupon> coupListFromDb = customerDbdao.findCustomerById(customerId).getCoupons();
		Coupon coupFromDb = couponDbdao.findByTitle(coupon.getTitle());

		if (coupListFromDb.contains(coupFromDb)) {
			throw new PurchaseCouponException(
					"Purchasing this type of coupon is limited to one use only. you are welcome to choose another coupon.");
		}
		if (coupFromDb.getAmount() == 0) {
			throw new PurchaseCouponException("Coupon out of stock, you are welcome to choose another coupon.");
		}
		if (coupFromDb.getEndDate().before(java.sql.Date.valueOf(LocalDate.now()))) {
			throw new PurchaseCouponException("This coupon has expired, you are welcome to choose another coupon.");
		}

		coupListFromDb.add(coupon);
		custFromDb.setCoupons(coupListFromDb);
		customerDbdao.updateCustomer(custFromDb);

		coupon.setAmount(coupon.getAmount() - 1);
		couponDbdao.updateCoupon(coupon);

		return coupon;
	}

	public List<Coupon> getAllCustomerCoupons() throws NotFoundException, LogException {

		Optional<Customer> customerFromDb = Optional.of(customerDbdao.findCustomerById(customerId));

		if (customerFromDb.isEmpty()) {
			throw new NotFoundException("coupons details.");
		}

		return customerFromDb.get().getCoupons();
	}

	public List<Coupon> getAllCouponsByCategory(CouponCategory couponCategory) throws NotFoundException, LogException {

		List<Coupon> coupFromDbById = customerDbdao.findCustomerById(customerId).getCoupons();
		if (coupFromDbById.isEmpty()) {
			throw new NotFoundException("coupons details.");
		}

		List<Coupon> coupByCategory = new ArrayList<Coupon>();
		for (Coupon c : coupFromDbById) {
			if (c.getCategory().equals(couponCategory)) {
				coupByCategory.add(c);
			}
		}

		if (coupByCategory.isEmpty()) {
			throw new NotFoundException("coupons of category type " + couponCategory + ".");
		}
		return coupByCategory;
	}

	public List<Coupon> getAllCouponsUnderMaxPrice(double maxPrice) throws LogException, NotFoundException {

		List<Coupon> coupFromDbById = customerDbdao.findCustomerById(customerId).getCoupons();
		if (coupFromDbById.isEmpty()) {
			throw new NotFoundException("coupons details.");
		}

		List<Coupon> CouponsUnderMaxPrice = new ArrayList<Coupon>();
		for (Coupon c : coupFromDbById) {
			if (c.getPrice() < maxPrice) {
				CouponsUnderMaxPrice.add(c);
			}
		}

		if (CouponsUnderMaxPrice.isEmpty()) {
			throw new NotFoundException("coupons under price ", maxPrice);
		}
		return CouponsUnderMaxPrice;
	}

	public Customer getCustomerDetails() throws NotFoundException, LogException {

		Optional<Customer> customerFromDb = Optional.of(customerDbdao.findCustomerById(customerId));
		if (customerFromDb.isPresent()) {
			return customerDbdao.findCustomerById(customerId);
		}
		throw new NotFoundException("customer details.");
	}

}