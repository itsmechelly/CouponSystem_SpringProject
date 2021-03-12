package com.couponsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.couponsystem.beans.LoginResponse;
import com.couponsystem.enums.ClientType;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.exceptions.PurchaseCouponException;
import com.couponsystem.security.LoginManagerService;
import com.couponsystem.security.TokenManager;
import com.couponsystem.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController extends ClientController {
	
	private final LoginManagerService loginManagerService;

	private final TokenManager tokenManager;

	@Autowired
	public CustomerController(LoginManagerService loginManagerService, TokenManager tokenManager) {
		super();
		this.loginManagerService = loginManagerService;
		this.tokenManager = tokenManager;
	}

//	------------------------------------------------------------------------------------------------------------

	@PostMapping("/login")
	@Override
	public ResponseEntity<?> ClientLogin(String email, String password) throws LogException {

		HttpHeaders responseHeaders = new HttpHeaders();

		try {
			String token = loginManagerService.login(email, password, ClientType.CUSTOMER);
			responseHeaders.set("CouponSystem_Header", token);
			System.out.println("Customer token = " + token);

			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setToken(token);
			loginResponse.setType("customer");
			return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);

		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@Override
	public ResponseEntity<?> ClientLogout() throws LogException {
		// TODO Auto-generated method stub
		return null;
	}

//	------------------------------------------------------------------------------------------------------------

	@PostMapping("/purchaseCoupon")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			tokenManager.isTokenExist(token);
			return ResponseEntity.ok(((CustomerService) tokenManager.getClientService(token)).purchaseCoupon(coupon));
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
			tokenManager.isTokenExist(token);
			return ResponseEntity.ok(((CustomerService) tokenManager.getClientService(token)).getAllCustomerCoupons());
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
			tokenManager.isTokenExist(token);
			return ResponseEntity.ok(
					((CustomerService) tokenManager.getClientService(token)).getAllCouponsByCategory(couponCategory));
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
			tokenManager.isTokenExist(token);
			return ResponseEntity
					.ok(((CustomerService) tokenManager.getClientService(token)).getAllCouponsUnderMaxPrice(maxPrice));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getCustomerDetails")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			tokenManager.isTokenExist(token);
			return ResponseEntity.ok(((CustomerService) tokenManager.getClientService(token)).getCustomerDetails());
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}