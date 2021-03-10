package com.couponsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	int findIdByEmailAndPassword(String email, String password);

	Company findCompanyByEmailAndPassword(String email, String password);

}
