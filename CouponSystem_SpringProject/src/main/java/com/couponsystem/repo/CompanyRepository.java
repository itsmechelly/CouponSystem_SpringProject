package com.couponsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	boolean findByEmailAndPassword(String email, String password);//Added to use in Services.login methods;
	Company findCompanyById(int companyId);//Added to use in companyService;
	Company findCompanyByName(String name);//Added to use in adminService.addCompany;
	Company findCompanyByEmail(String email);//Added to use in adminService.addCompany;
	Company findCompanyByEmailAndPassword(String email, String password);//Added to use in LoginManager.login methods;	

}
