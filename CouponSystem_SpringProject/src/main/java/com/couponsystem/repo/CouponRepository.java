package com.couponsystem.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Coupon;
import com.couponsystem.enums.CouponCategory;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findById (int id);
	Coupon findByTitle(String title);
	List<Coupon> findByEndDateBefore(Date date);
//////////////////////////////////////////////////////////////////////////////////////////
	boolean existsByCompanyIdAndTitle(int companyId, String title);
	Coupon findByCompanyIdAndTitle(int companyId, String title);
	boolean existsByTitleAndIdNot(String title, int id);
	List<Coupon> findByCompanyId(int Id);
	List<Coupon> findByCompanyIdAndCategory(int companyId, CouponCategory category);
	List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double price);
//////////////////////////////////////////////////////////////////////////////////////////
	List<Coupon> getCouponsByCustomersId(int id);
	List<Coupon> getCouponsByCustomersIdAndCategory(int id, CouponCategory category);
	List<Coupon> getCouponsByCustomersIdAndPriceLessThan(int id, double price);

	
}