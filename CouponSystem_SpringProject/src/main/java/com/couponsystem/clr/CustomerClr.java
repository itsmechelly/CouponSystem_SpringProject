package com.couponsystem.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.couponsystem.beans.LoginForm;
import com.couponsystem.enums.ClientType;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.impl.CustomerImpl;
import com.couponsystem.rest.CustomerController;
import com.couponsystem.security.TokenManager;
import com.couponsystem.service.LoginService;
import com.couponsystem.utils.ClrUtils;

@Component
@Order(3)
public class CustomerClr implements CommandLineRunner {

	private TokenManager tokenManager;
	private final CustomerController customerController;
	private final CustomerImpl customerImpl;

	private LoginService loginService;

	@Autowired
	public CustomerClr(TokenManager tokenManager, CustomerController customerController,
			CustomerImpl customerImpl, LoginService loginService) {
		super();
		this.tokenManager = tokenManager;
		this.customerController = customerController;
		this.customerImpl = customerImpl;
		this.loginService = loginService;
	}

	@Override
	public void run(String... args) throws Exception {
//    _  
//  _( )_
// (_ o _)
//  (_,_)
//       _____           _                             ______          _     _____         _       
//      /  __ \         | |                            | ___ \        | |   |_   _|       | |      
//      | /  \/_   _ ___| |_ ___  _ __ ___   ___ _ __  | |_/ /___  ___| |_    | | ___  ___| |_ ___ 
//      | |   | | | / __| __/ _ \| '_ ` _ \ / _ \ '__| |    // _ \/ __| __|   | |/ _ \/ __| __/ __|
//      | \__/\ |_| \__ \ || (_) | | | | | |  __/ |    | |\ \  __/\__ \ |_    | |  __/\__ \ |_\__ \
//       \____/\__,_|___/\__\___/|_| |_| |_|\___|_|    \_| \_\___||___/\__|   \_/\___||___/\__|___/

		ClrUtils.customerRestTests();
		
		LoginForm loginForm = new LoginForm();
		loginForm.setEmail("cust1@cust.com");
		loginForm.setPasswoed("1111");
		loginForm.setClientType(ClientType.CUSTOMER);
		
		String token = loginService.login(loginForm);
		System.out.println(token);
		

//		------------------------------------------------------------------------------------------------------------

//		ClrUtils.testSeparatedLine(" --------->>>>>>>> Testing Company Login:");
//
//		System.out.println("Going to test login exception - *WRONG* *Email*:");
//		System.out.println(customerController.ClientLogin("BADcust@cust.com", "1111"));
//
//		System.out.println();
//		System.out.println("Going to test login exception - *WRONG* *Password*:");
//		System.out.println(customerController.ClientLogin("cust1@cust.com", "1010"));
//
//		System.out.println();
//		System.out.println("Going to test GOOD customer login:");
//		System.out.println(customerController.ClientLogin("cust1@cust.com", "1111"));

//		TODO -> Logout
//		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test Customer Logout:");

//		------------------------------------------------------------------------------------------------------------

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test customerController.purchaseCoupon:");

		System.out
		.println(customerController.purchaseCoupon(customerImpl.findCouponById(2), token));		
		System.out
				.println(customerController.purchaseCoupon(customerImpl.findCouponById(4), token));
		System.out
				.println(customerController.purchaseCoupon(customerImpl.findCouponById(6), token));

		ClrUtils.testSeparatedLine(
				" --------->>>>>>>> Going to test *BAD REQUEST* for customerController.purchaseCoupon: (customer can't purchase the same coupon more then once)");

		System.out
				.println(customerController.purchaseCoupon(customerImpl.findCouponById(2), token));

		ClrUtils.testSeparatedLine(
				" --------->>>>>>>> Going to test *BAD REQUEST* for customerController.purchaseCoupon: (customer can't purchase the coupon if amount<0)");

		System.out
				.println(customerController.purchaseCoupon(customerImpl.findCouponById(1), token));

		ClrUtils.testSeparatedLine(
				" --------->>>>>>>> Going to test *BAD REQUEST* for customerController.purchaseCoupon: (customer can't purchase the coupon if the coupon has expired)");

		System.out
				.println(customerController.purchaseCoupon(customerImpl.findCouponById(7), token));

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test customerController.getAllCustomerCoupons:");

		System.out.println(customerController.getAllCustomerCoupons(token));

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test customerController.getAllCouponsByCategory:");

		System.out.println(
				customerController.getAllCouponsByCategory(CouponCategory.RESTAURANT, token));

		ClrUtils.testSeparatedLine(
				" --------->>>>>>>> Going to test *BAD REQUEST* for customerController.getAllCouponsByCategory: (coupons from category type not found)");

		System.out.println(
				customerController.getAllCouponsByCategory(CouponCategory.ELECTRICITY, token));

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test customerController.getAllCouponsUnderMaxPrice:");

		System.out.println(customerController.getAllCouponsUnderMaxPrice(400, token));

		ClrUtils.testSeparatedLine(
				" --------->>>>>>>> Going to test *BAD REQUEST* for customerController.getAllCouponsUnderMaxPrice: (coupons under maxPrice not found)");

		System.out.println(customerController.getAllCouponsUnderMaxPrice(22, token));

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test customerController.getCustomerDetails:");

		System.out.println(customerController.getCustomerDetails(token));
	}

}
