package com.couponsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.beans.Coupon;
import com.couponsystem.enums.ClientType;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.exceptions.PurchaseCouponException;
import com.couponsystem.service.CustomerService;
import com.couponsystem.security.SessionContext;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController {
	
	private SessionContext sessionContext;

	@Autowired
	public CustomerController(SessionContext sessionContext) {
		super();
		this.sessionContext = sessionContext;
	}

//	------------------------------------------------------------------------------------------------------------

	@PostMapping("/purchaseCoupon")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity.ok(((CustomerService) sessionContext.getClientService(token, ClientType.CUSTOMER.toString())).purchaseCoupon(coupon));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (PurchaseCouponException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllCustomerCoupons")
	public ResponseEntity<?> getAllCustomerCoupons(@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity.ok(((CustomerService) sessionContext.getClientService(token, ClientType.CUSTOMER.toString())).getAllCoupons());
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
					((CustomerService) sessionContext.getClientService(token, ClientType.CUSTOMER.toString())).getAllCouponsByCategory(couponCategory));
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
					.ok(((CustomerService) sessionContext.getClientService(token, ClientType.CUSTOMER.toString())).getAllCouponsUnderMaxPrice(maxPrice));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getCustomerDetails")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity.ok(((CustomerService) sessionContext.getClientService(token, ClientType.CUSTOMER.toString())).getCustomerDetails());
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}