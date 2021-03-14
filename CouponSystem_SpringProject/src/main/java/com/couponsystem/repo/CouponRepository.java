package com.couponsystem.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Coupon;
import com.couponsystem.enums.CouponCategory;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findCouponById (int id);//Added to use in customerService.purchaseCoupon;
	Coupon findCouponByTitle(String title);//Added to use in companyService.addCompanyCoupon;
	List<Coupon> findByEndDateBefore(Date date);// Added to use in CouponExpirationDailyJob.applyExpirationJob;
////////////
	boolean existsByCompanyIdAndTitle(int companyId, String title);
	Coupon findCouponByCompanyIdAndTitle(int companyId, String title);
	boolean existsByTitleAndIdNot(String title, int id);
	List<Coupon> findByCompanyId(int Id);
	List<Coupon> findByCompanyIdAndCategory(int companyId, CouponCategory couponCategory);
	List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double price);
	

}