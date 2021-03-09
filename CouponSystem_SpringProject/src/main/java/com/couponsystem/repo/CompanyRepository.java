package com.couponsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
