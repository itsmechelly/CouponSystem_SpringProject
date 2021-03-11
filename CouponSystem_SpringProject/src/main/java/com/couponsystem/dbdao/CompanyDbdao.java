package com.couponsystem.dbdao;

import java.util.List;
import java.util.Optional;

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
	//****************************
	public Company addCompany(Company company) {
		return companyRepository.save(company);
	}

	public Company updateCompany(Company company) {
		return companyRepository.saveAndFlush(company);
	}

	public void deleteCompany(int companyId) {
		companyRepository.deleteById(companyId);
	}
	
	public boolean findByEmailAndPassword(String email, String password) {
		return companyRepository.findByEmailAndPassword(email, password);
	}
	
	public Company findCompanyById(int companyId) {
		return companyRepository.findCompanyById(companyId);
	}
	
	public Optional<Company> findById(int companyId) {
		return companyRepository.findById(companyId);
	}
	
	public Company findCompanyByName(String name) {
		return companyRepository.findCompanyByName(name);
	}
	
	public Company findCompanyByEmail(String email) {
		return companyRepository.findCompanyByEmail(email);
	}
	
	public List<Company> findAllCompanies() {
		return companyRepository.findAll();
	}

}
