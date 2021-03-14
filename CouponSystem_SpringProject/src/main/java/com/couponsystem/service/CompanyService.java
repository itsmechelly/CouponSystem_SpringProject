package com.couponsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Coupon;
import com.couponsystem.dbdao.CompanyDbdao;
import com.couponsystem.dbdao.CouponDbdao;
import com.couponsystem.dbdao.CustomerDbdao;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;
import com.couponsystem.exceptions.NotFoundException;

import lombok.Setter;

@Service
@Scope("prototype")
@Setter
public class CompanyService extends ClientService {

	public int companyId;

	@Autowired
	public CompanyService(CompanyDbdao companyDbdao, CustomerDbdao customerDbdao, CouponDbdao couponDbdao) {
		super(companyDbdao, customerDbdao, couponDbdao);
	}
	
//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) {
		Company company = companyDbdao.findCompanyByEmailAndPassword(email, password);
		if (company != null) {
			return true;
		}
		return false;
	}

	public int findCompanyIdByEmailAndPassword(String email, String password) {
		Company cForCompanyId = companyDbdao.findCompanyByEmailAndPassword(email, password);
		return cForCompanyId.getId();
	}

//	------------------------------------------------------------------------------------------------------------

	public Coupon addCompanyCoupon(Coupon coupon) throws AlreadyExistException, LogException {

		if (couponDbdao.existsByCompanyIdAndTitle(this.companyId, coupon.getTitle())) {
			throw new AlreadyExistException("Company title ", coupon.getTitle());
		}
		
		coupon.setCompanyId(companyId);
		couponDbdao.addCoupon(coupon);
		return coupon;
	}

	public Coupon updateCompanyCoupon(Coupon coupon) throws LogException, NotFoundException, NotAllowedException, AlreadyExistException {
		
		Optional<Coupon> coupFromDb1 = Optional.of(couponDbdao.findCouponByCompanyIdAndTitle(this.companyId, coupon.getTitle()));
		Optional<Coupon> coupFromDb2 = Optional.of(couponDbdao.findCouponById(coupon.getId()));
		
		if (coupon.getId() != coupFromDb1.get().getId()) {
			throw new NotAllowedException("coupon id number", coupon.getId());
		}
		if (coupon.getCompanyId() != coupFromDb2.get().getCompanyId()) {
			throw new NotAllowedException("company id number", this.companyId);
		}
		if (couponDbdao.existsByTitleAndIdNot(coupon.getTitle(), coupon.getId())) {
			throw new AlreadyExistException("Company title ", coupon.getTitle());
		}

		couponDbdao.updateCoupon(coupon);
		return coupon;
	}

	public String deleteCompanyCoupon(int couponId) throws NotFoundException, LogException {
		Company compFromDb = companyDbdao.findCompanyById(companyId);
		List<Coupon> coupListFromDb = compFromDb.getCoupons();
		Coupon coupFromDb = couponDbdao.findCouponById(couponId);

		if (coupListFromDb.isEmpty()) {
			throw new NotFoundException("coupons details.");
		}

		if (coupFromDb == null) {
			throw new NotFoundException("coupon details.");
		}

		coupListFromDb.remove(coupFromDb);
		compFromDb.setCoupons(coupListFromDb);
		companyDbdao.updateCompany(compFromDb);

		if (couponDbdao.existsById(couponId)) {
			couponDbdao.deleteCoupon(couponId);
		}
		return "Coupon with id number " + couponId + " deleted successfully.";
	}

	public List<Coupon> getAllCompaniesCoupons() throws LogException, NotFoundException {
		
		List<Coupon> coupFromDb = couponDbdao.findAllCouponsByCompanyId(this.companyId);
		if (coupFromDb != null)
			return coupFromDb;
		throw new NotFoundException("coupons details.");
	}

	public List<Coupon> getAllCouponsByCategory(CouponCategory couponCategory) throws NotFoundException, LogException {
		List<Coupon> coupFromDbById = couponDbdao.findAllCouponsByCompanyId(companyId);
		if (coupFromDbById.isEmpty()) {
			throw new NotFoundException("coupons details.");
		}

		List<Coupon> coupFromDbByCategory = couponDbdao.findAllCouponsByCategory(couponCategory);
		if (coupFromDbByCategory.isEmpty()) {
			throw new NotFoundException("coupons from category type " + couponCategory + ".");
		}
		return coupFromDbByCategory;
	}

	public List<Coupon> getAllCouponsUnderMaxPrice(double maxPrice) throws LogException, NotFoundException {
		List<Coupon> coupFromDbById = couponDbdao.findAllCouponsByCompanyId(companyId);
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

	public Company getCompanyDetails() throws NotFoundException, LogException {
		Optional<Company> companyFromDb = Optional.of(companyDbdao.findCompanyById(companyId));
		if (companyFromDb.isPresent()) {
			return companyDbdao.findCompanyById(companyId);
		}
		throw new NotFoundException("company details.");
	}

}