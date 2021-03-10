package com.couponsystem.dbdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.couponsystem.beans.Company;
import com.couponsystem.repo.CompanyRepository;

@Repository
public class CompanyDbdao {

	private final CompanyRepository companyRepository;

	@Autowired
	public CompanyDbdao(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

//	------------------------------------------------------------------------------------------------------------

	public int findIdByEmailAndPassword(String email, String password) {
		return companyRepository.findIdByEmailAndPassword(email, password);
	}
	
	public Company findCompanyByEmailAndPassword(String email, String password) {
		return companyRepository.findCompanyByEmailAndPassword(email, password);
	}
}
