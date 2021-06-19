package com.couponsystem.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.LoginForm;
import com.couponsystem.enums.ClientType;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.rest.CompanyController;
import com.couponsystem.security.LoginController;
import com.couponsystem.utils.ClrUtils;
import com.couponsystem.utils.DateUtil;

@Component
@Order(2)
public class CompanyClr implements CommandLineRunner {

	private final LoginController loginController;
	private final CompanyController companyController;

	@Autowired
	public CompanyClr(LoginController loginController, CompanyController companyController) {
		super();
		this.loginController = loginController;
		this.companyController = companyController;
	}

	@Override
	public void run(String... args) throws Exception {
		
//    _  
//  _( )_
// (_ o _)
//	(_,_)
//		 ___                                           ______          _     _____         _       
//     /  __ \                                         | ___ \        | |   |_   _|       | |      
//	   | /  \/ ___  _ __ ___  _ __   __ _ _ __  _   _  | |_/ /___  ___| |_    | | ___  ___| |_ ___ 
//	   | |    / _ \| '_ ` _ \| '_ \ / _` | '_ \| | | | |    // _ \/ __| __|   | |/ _ \/ __| __/ __|
//	   | \__/\ (_) | | | | | | |_) | (_| | | | | |_| | | |\ \  __/\__ \ |_    | |  __/\__ \ |_\__ \
//	    \____/\___/|_| |_| |_| .__/ \__,_|_| |_|\__, | \_| \_\___||___/\__|   \_/\___||___/\__|___/
//                           | |                 __/ |                                             
//	                         |_|                |___/                                              
		ClrUtils.companyRestTests();

//		------------------------------------------------------------------------------------------------------------

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Testing Company Login:");
//
		System.out.println("Going to test login exception - *WRONG* *Email*:");
		LoginForm badEmailLoginForm = new LoginForm("BADcomp@comp.com", "1111", ClientType.COMPANY);
		System.out.println(loginController.login(badEmailLoginForm));

		System.out.println();
		System.out.println("Going to test login exception - *WRONG* *Password*:");
		LoginForm badPasswordLoginForm = new LoginForm("comp1Email@comp.com", "1010", ClientType.COMPANY);
		System.out.println(loginController.login(badPasswordLoginForm));

		System.out.println();
		System.out.println("Going to test GOOD company login:");
		LoginForm goodLoginForm = new LoginForm("comp1Email@comp.com", "1111", ClientType.COMPANY);
		System.out.println(loginController.login(goodLoginForm));
		String token = loginController.getToken();
		
//		TODO -> Logout
//		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test Company Logout:");

//		------------------------------------------------------------------------------------------------------------

		ClrUtils.testSeparatedLine(
				" --------->>>>>>>> Going to add 5 coupons using companyController.addCompanyCoupon:");

		Coupon coup1 = new Coupon();
		coup1.setCategory(CouponCategory.FOOD);
		coup1.setTitle("coup1Title");
		coup1.setDescription("coup1Description");
		coup1.setStartDate(DateUtil.dateFormat(2029, 06, 28));
		coup1.setEndDate(DateUtil.dateFormat(2029, 10, 28));
		coup1.setAmount(0);
		coup1.setPrice(100);
		coup1.setImage("www.compPng1.com");

		Coupon coup2 = new Coupon();
		coup2.setCategory(CouponCategory.FOOD);
		coup2.setTitle("coup2Title");
		coup2.setDescription("coup2Description");
		coup2.setStartDate(DateUtil.dateFormat(2029, 07, 28));
		coup2.setEndDate(DateUtil.dateFormat(2029, 10, 28));
		coup2.setAmount(200);
		coup2.setPrice(200);
		coup2.setImage("www.compPng2.com");

		Coupon coup3 = new Coupon();
		coup3.setCategory(CouponCategory.RESTAURANT);
		coup3.setTitle("coup3Title");
		coup3.setDescription("coup3DDDDDDDDDDD");
		coup3.setStartDate(DateUtil.dateFormat(2029, 06, 28));
		coup3.setEndDate(DateUtil.dateFormat(2029, 10, 28));
		coup3.setAmount(888);
		coup3.setPrice(300);
		coup3.setImage("www.33333333.com");

		Coupon coup4 = new Coupon();
		coup4.setCategory(CouponCategory.RESTAURANT);
		coup4.setTitle("coup4Title");
		coup4.setDescription("coup4Description");
		coup4.setStartDate(DateUtil.dateFormat(2029, 07, 28));
		coup4.setEndDate(DateUtil.dateFormat(2029, 10, 28));
		coup4.setAmount(400);
		coup4.setPrice(400);
		coup4.setImage("www.compPng4.com");

		Coupon coup5 = new Coupon();
		coup5.setCategory(CouponCategory.RESTAURANT);
		coup5.setTitle("coup5Title");
		coup5.setDescription("coup5Description");
		coup5.setStartDate(DateUtil.dateFormat(2029, 07, 28));
		coup5.setEndDate(DateUtil.dateFormat(2029, 10, 28));
		coup5.setAmount(500);
		coup5.setPrice(500);
		coup5.setImage("www.compPng5.com");

		Coupon coup6 = new Coupon();
		coup6.setCategory(CouponCategory.VACATION);
		coup6.setTitle("coup6Title");
		coup6.setDescription("coup6Description");
		coup6.setStartDate(DateUtil.dateFormat(2029, 07, 28));
		coup6.setEndDate(DateUtil.dateFormat(2029, 10, 28));
		coup6.setAmount(600);
		coup6.setPrice(600);
		coup6.setImage("www.compPng6.com");

		Coupon coup7 = new Coupon();
		coup7.setCategory(CouponCategory.VACATION);
		coup7.setTitle("coup7Title");
		coup7.setDescription("coup7Description");
		coup7.setStartDate(DateUtil.dateFormat(2019, 07, 28));
		coup7.setEndDate(DateUtil.dateFormat(2019, 8, 28));
		coup7.setAmount(700);
		coup7.setPrice(700);
		coup7.setImage("www.compPng7.com");

		Coupon coup8 = new Coupon();
		coup8.setCategory(CouponCategory.RESTAURANT);
		coup8.setTitle("coup8Title");
		coup8.setDescription("coup8Description");
		coup8.setStartDate(DateUtil.dateFormat(2029, 07, 28));
		coup8.setEndDate(DateUtil.dateFormat(2029, 10, 28));
		coup8.setAmount(800);
		coup8.setPrice(800);
		coup8.setImage("www.compPng8.com");

		System.out.println(companyController.addCompanyCoupon(coup1, token));
		System.out.println(companyController.addCompanyCoupon(coup2, token));
		System.out.println(companyController.addCompanyCoupon(coup3, token));
		System.out.println(companyController.addCompanyCoupon(coup4, token));
		System.out.println(companyController.addCompanyCoupon(coup5, token));
		System.out.println(companyController.addCompanyCoupon(coup6, token));
		System.out.println(companyController.addCompanyCoupon(coup7, token));
		System.out.println(companyController.addCompanyCoupon(coup8, token));

		ClrUtils.testSeparatedLine(
				" --------->>>>>>>> Going to test *BAD REQUEST* for companyController.addCompanyCoupon: (company title coup7Title, already exists)");

		Coupon coup77 = new Coupon();
		coup77.setCategory(CouponCategory.VACATION);
		coup77.setTitle("coup7Title");
		coup77.setDescription("coup77Description");
		coup77.setStartDate(DateUtil.dateFormat(2029, 07, 28));
		coup77.setEndDate(DateUtil.dateFormat(2029, 10, 28));
		coup77.setAmount(7700);
		coup77.setPrice(7700);
		coup77.setImage("www.compPng77.com");

		System.out.println(companyController.addCompanyCoupon(coup77, token));

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test companyController.updateCoupon:");

		coup3.setTitle("coup3Title");
		coup3.setDescription("coup3Description");
		coup3.setStartDate(DateUtil.dateFormat(2029, 06, 29));
		coup3.setEndDate(DateUtil.dateFormat(2029, 10, 29));
		coup3.setAmount(333);
		coup3.setPrice(300);
		coup3.setImage("www.compPng3.com");

		System.out.println(companyController.updateCompanyCoupon(coup3, token));

		ClrUtils.testSeparatedLine(
				" --------->>>>>>>> Going to test *BAD REQUEST* for companyController.updateCoupon: (update couponId not allowed)");

		coup3.setId(1);
		System.out.println(companyController.updateCompanyCoupon(coup3, token));

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test companyController.deleteCompanyCoupon:");

		System.out.println(companyController.deleteCompanyCoupon(5, token));

		ClrUtils.testSeparatedLine(
				" --------->>>>>>>> Going to test *BAD REQUEST* for companyController.deleteCompanyCoupon: (coupon not exist)");

		System.out.println(companyController.deleteCompanyCoupon(11, token));

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test companyController.getAllCompaniesCoupons:");

		System.out.println(companyController.getAllCompaniesCoupons(token));

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test companyController.getAllCouponsByCategory:");

		System.out.println(
				companyController.getAllCouponsByCategory(CouponCategory.RESTAURANT, token));

		ClrUtils.testSeparatedLine(
				" --------->>>>>>>> Going to test *BAD REQUEST* for companyController.getAllCouponsByCategory: (company coupons from category type not found)");

		System.out.println(
				companyController.getAllCouponsByCategory(CouponCategory.ELECTRICITY, token));

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test companyController.getAllCouponsUnderMaxPrice:");

		System.out.println(companyController.getAllCouponsUnderMaxPrice(300, token));

		ClrUtils.testSeparatedLine(
				" --------->>>>>>>> Going to test *BAD REQUEST* forcompanyController.getAllCouponsUnderMaxPrice: (company coupons under max price not found)");

		System.out.println(companyController.getAllCouponsUnderMaxPrice(22, token));

		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test companyController.getCompanyDetails:");

		System.out.println(companyController.getCompanyDetails(token));
	}

}