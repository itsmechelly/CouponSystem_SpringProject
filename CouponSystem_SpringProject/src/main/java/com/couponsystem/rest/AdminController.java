package com.couponsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Customer;
import com.couponsystem.beans.LoginResponse;
import com.couponsystem.enums.ClientType;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.CouponSystemException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.security.LoginManagerService;
import com.couponsystem.security.TokenManager;
import com.couponsystem.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AdminController extends ClientController {

	private final LoginManagerService loginManagerService;

	private final TokenManager tokenManager;

	@Autowired
	public AdminController(LoginManagerService loginManagerService,
			TokenManager tokenManager) {
		super();
		this.loginManagerService = loginManagerService;
		this.tokenManager = tokenManager;
	}

//	------------------------------------------------------------------------------------------------------------

	@PostMapping("/login")
	@Override
	public ResponseEntity<?> ClientLogin(@RequestParam String email, @RequestParam String password)
			throws LogException {

		HttpHeaders responseHeaders = new HttpHeaders();

		try {
			String token = loginManagerService.login(email, password, ClientType.ADMIN);
			responseHeaders.set("CouponSystem_Header", token);
			System.out.println("Admin token = " + token);
			
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setToken(token);
			loginResponse.setType("admin");
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

	@PostMapping("/addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company,
			@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			tokenManager.isTokenExist(token);
			return ResponseEntity.ok(((AdminService) tokenManager.getClientService(token)).addCompany(company));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (AlreadyExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,
			@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			tokenManager.isTokenExist(token);
			return ResponseEntity.ok(((AdminService) tokenManager.getClientService(token)).updateCompany(company));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (NotAllowedException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deleteCompany/{companyId}")
	public ResponseEntity<?> deleteCompany(@PathVariable int companyId,
			@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			tokenManager.isTokenExist(token);
			return ResponseEntity.ok(((AdminService) tokenManager.getClientService(token)).deleteCompany(companyId));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getOneCompanyById/{companyId}")
	public ResponseEntity<?> getOneCompanyById(@PathVariable int companyId,
			@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			tokenManager.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) tokenManager.getClientService(token)).getOneCompanyById(companyId));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllCompanies")
	public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			tokenManager.isTokenExist(token);
			return ResponseEntity.ok(((AdminService) tokenManager.getClientService(token)).getAllCompanies());
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			tokenManager.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) tokenManager.getClientService(token)).addCustomer(customer));	
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (AlreadyExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			
			tokenManager.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) tokenManager.getClientService(token)).updateCustomer(customer));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId, @RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			tokenManager.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) tokenManager.getClientService(token)).deleteCustomer(customerId));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getOneCustomerById/{customerId}")
	public ResponseEntity<?> getOneCustomerById(@PathVariable int customerId, @RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			tokenManager.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) tokenManager.getClientService(token)).getOneCustomerById(customerId));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "CouponSystem_Header") String token) throws CouponSystemException {
		try {
			tokenManager.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) tokenManager.getClientService(token)).getAllCustomers());
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}

