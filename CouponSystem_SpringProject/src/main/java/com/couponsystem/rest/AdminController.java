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
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Customer;
import com.couponsystem.enums.ClientType;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.CouponSystemException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.service.AdminService;
import com.couponsystem.security.SessionContext;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AdminController {
	
	private final SessionContext sessionContext;
	
	@Autowired
	public AdminController(SessionContext sessionContext) {
		super();
		this.sessionContext = sessionContext;
	}

//	------------------------------------------------------------------------------------------------------------

	@PostMapping("/addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company,
			@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity.ok(( (AdminService) sessionContext.getClientService(token, ClientType.ADMIN.toString())).addCompany(company));
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
			sessionContext.isTokenExist(token);
			return ResponseEntity.ok(((AdminService) sessionContext.getClientService(token, ClientType.ADMIN.toString())).updateCompany(company));
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
			sessionContext.isTokenExist(token);
			return ResponseEntity.ok(((AdminService) sessionContext.getClientService(token, ClientType.ADMIN.toString())).deleteCompany(companyId));
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
			sessionContext.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) sessionContext.getClientService(token, ClientType.ADMIN.toString())).getOneCompanyById(companyId));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllCompanies")
	public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity.ok(((AdminService) sessionContext.getClientService(token, ClientType.ADMIN.toString())).getAllCompanies());
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) sessionContext.getClientService(token, ClientType.ADMIN.toString())).addCustomer(customer));	
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
			
			sessionContext.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) sessionContext.getClientService(token, ClientType.ADMIN.toString())).updateCustomer(customer));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId, @RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) sessionContext.getClientService(token, ClientType.ADMIN.toString())).deleteCustomer(customerId));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getOneCustomerById/{customerId}")
	public ResponseEntity<?> getOneCustomerById(@PathVariable int customerId, @RequestHeader(name = "CouponSystem_Header") String token) {

		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) sessionContext.getClientService(token, ClientType.ADMIN.toString())).getOneCustomerById(customerId));
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "CouponSystem_Header") String token) throws CouponSystemException {
		try {
			sessionContext.isTokenExist(token);
			return ResponseEntity
					.ok(((AdminService) sessionContext.getClientService(token, ClientType.ADMIN.toString())).getAllCustomers());
		} catch (LogException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}