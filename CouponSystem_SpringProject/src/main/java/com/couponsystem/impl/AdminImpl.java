package com.couponsystem.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.couponsystem.beans.Company;
import com.couponsystem.repo.CompanyRepository;

@Repository
public class AdminImpl {

	private final CompanyRepository companyRepository;
	
	public AdminImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}
	
//	------------------------------------------------------------------------------------------------------------

	public Company addCompany(Company company) {
		return companyRepository.save(company);
	}
	
	public Company updateCompany(Company company) {
		return companyRepository.saveAndFlush(company);
	}
	
	public void deleteCompany(int companyId) {
		companyRepository.deleteById(companyId);
	}
	
	public Company findCompanyById(int companyId) {
		return companyRepository.findCompanyById(companyId);
	}
	
	public List<Company> findAllCompanies() {
		return companyRepository.findAll();
	}
	
	public boolean existsById(int id) {
		return companyRepository.existsById(id);
	}
	
	public boolean existsByEmail(String email) {
		return companyRepository.existsByEmail(email);
	}
	
	public boolean existsByNameIgnoreCase(String name) {
		return companyRepository.existsByNameIgnoreCase(name);
	}
	
	
}
