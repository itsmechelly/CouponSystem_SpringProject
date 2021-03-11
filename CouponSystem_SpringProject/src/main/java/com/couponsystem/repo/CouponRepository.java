package com.couponsystem.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Coupon;
import com.couponsystem.enums.CouponCategory;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	//****************************
	Coupon findCouponById (int id);//Added to use in customerService.purchaseCoupon;
	Coupon findCouponByTitle(String title);//Added to use in companyService.addCompanyCoupon;
	List<Coupon> findByTitle(String title);// Added to use in customerService.purchaseCoupon;
	List<Coupon> findByEndDateBefore(Date date);// Added to use in CouponExpirationDailyJob.applyExpirationJob;
	List<Coupon> findAllCouponsByCompanyId(int Id);//Added to use in companyService.findAllCompaniesCoupons;
	List<Coupon> findAllCouponsByCategory(CouponCategory couponCategory);// Added to use in companyService.findAllCouponsByCategory;

}
