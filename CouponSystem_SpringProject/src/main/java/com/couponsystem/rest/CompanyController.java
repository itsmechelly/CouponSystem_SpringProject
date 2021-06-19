package com.couponsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.beans.Coupon;
import com.couponsystem.enums.ClientType;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.service.CompanyService;
import com.couponsystem.security.SessionContext;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CompanyController {

	private final SessionContext sessionContext;

	@Autowired
	public CompanyController(SessionContext sessionContext) {
		super();
		this.sessionContext = sessionContext;
	}
	
//	------------------------------------------------------------------------------------------------------------

	@PostMapping("/addCompanyCoupon")
	public ResponseEntity<?> addCompanyCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity.ok(((CompanyService) sessionContext.getClientService(token, ClientType.COMPANY.toString())).addCoupon(coupon));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (AlreadyExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateCompanyCoupon")
	public ResponseEntity<?> updateCompanyCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "CouponSystem_Header") String token) throws AlreadyExistException {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity
					.ok(((CompanyService) sessionContext.getClientService(token, ClientType.COMPANY.toString())).updateCoupon(coupon));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (NotAllowedException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
		}
	}

	@DeleteMapping("/deleteCompanyCoupon")
	public ResponseEntity<?> deleteCompanyCoupon(@RequestParam int couponId,
			@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity
					.ok(((CompanyService) sessionContext.getClientService(token, ClientType.COMPANY.toString())).deleteCoupon(couponId));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllCompaniesCoupons")
	public ResponseEntity<?> getAllCompaniesCoupons(@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity.ok(((CompanyService) sessionContext.getClientService(token, ClientType.COMPANY.toString())).getAllCoupons());
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllCouponsByCategory/{couponCategory}")
	public ResponseEntity<?> getAllCouponsByCategory(@PathVariable CouponCategory couponCategory,
			@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity.ok(
					((CompanyService) sessionContext.getClientService(token, ClientType.COMPANY.toString())).getAllCouponsByCategory(couponCategory));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllCouponsUnderMaxPrice")
	public ResponseEntity<?> getAllCouponsUnderMaxPrice(@RequestParam double maxPrice,
			@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity
					.ok(((CompanyService) sessionContext.getClientService(token, ClientType.COMPANY.toString())).getAllCouponsUnderMaxPrice(maxPrice));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getCompanyDetails")
	public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity.ok(((CompanyService) sessionContext.getClientService(token, ClientType.COMPANY.toString())).getCompanyDetails());
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}