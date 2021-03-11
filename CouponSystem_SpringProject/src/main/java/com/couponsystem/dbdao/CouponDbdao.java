package com.couponsystem.dbdao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.couponsystem.beans.Coupon;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.repo.CouponRepository;

@Repository
public class CouponDbdao {

	private final CouponRepository couponRepository;

	@Autowired
	public CouponDbdao(CouponRepository couponRepository) {
		super();
		this.couponRepository = couponRepository;
	}

//	------------------------------------------------------------------------------------------------------------

	public Coupon addCoupon(Coupon coupon) {
		return couponRepository.save(coupon);
	}

	public Coupon updateCoupon(Coupon coupon) {
		return couponRepository.saveAndFlush(coupon);
	}

	public void deleteCoupon(int couponId) {
		couponRepository.deleteById(couponId);
	}

	public boolean existsById(int companyId) {
		return couponRepository.existsById(companyId);
	}

	public Coupon findCouponById(int couponId) {
		return couponRepository.findCouponById(couponId);
	}

	public Coupon findCouponByTitle(String title) {
		return couponRepository.findCouponByTitle(title);
	}

	public List<Coupon> findByTitle(String title) {
		return couponRepository.findByTitle(title);
	}

	public List<Coupon> findByEndDateBefore(Date date) {
		return couponRepository.findByEndDateBefore(date);
	}

	public List<Coupon> findAllCouponsByCompanyId(int companyId) {
		return couponRepository.findAllCouponsByCompanyId(companyId);
	}

	public List<Coupon> findAllCouponsByCategory(CouponCategory couponCategory) {
		return couponRepository.findAllCouponsByCategory(couponCategory);
	}
}

