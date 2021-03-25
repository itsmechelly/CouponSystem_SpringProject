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

	public boolean existsById(int couponId) {
		return couponRepository.existsById(couponId);
	}

	public Coupon findById(int couponId) {
		return couponRepository.findById(couponId);
	}

	public Coupon findByTitle(String title) {
		return couponRepository.findByTitle(title);
	}

	public List<Coupon> findByEndDateBefore(Date date) {
		return couponRepository.findByEndDateBefore(date);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean existsByCompanyIdAndTitle(int companyId, String title) {
		return couponRepository.existsByCompanyIdAndTitle(companyId, title);
	}

	public Coupon findByCompanyIdAndTitle(int companyId, String title) {
		return couponRepository.findByCompanyIdAndTitle(companyId, title);
	}

	public boolean existsByTitleAndIdNot(String title, int id) {
		return couponRepository.existsByTitleAndIdNot(title, id);
	}

	public List<Coupon> findByCompanyId(int companyId) {
		return couponRepository.findByCompanyId(companyId);
	}

	public List<Coupon> findByCompanyIdAndCategory(int companyId, CouponCategory category) {
		return couponRepository.findByCompanyIdAndCategory(companyId, category);
	}

	public List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double price) {
		return couponRepository.findByCompanyIdAndPriceLessThan(companyId, price);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////
	
	public List<Coupon> getCouponsByCustomersId(int id) {
		return couponRepository.getCouponsByCustomersId(id);
	}

	public List<Coupon> getCouponsByCustomersIdAndCategory(int id, CouponCategory category) {
		return couponRepository.getCouponsByCustomersIdAndCategory(id, category);
	}

	public List<Coupon> getCouponsByCustomersIdAndPriceLessThan(int id, double price) {
		return couponRepository.getCouponsByCustomersIdAndPriceLessThan(id, price);
	}

	
}
