package com.couponsystem.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.couponsystem.enums.CouponCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = true)
	private int companyId;
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private CouponCategory category;
	@Column(nullable = true)
	private String title;
	@Column(nullable = true)
	private String description;
	@Column(nullable = true)
	private Date startDate;
	@Column(nullable = true)
	private Date endDate;
	@Column(nullable = true)
	private int amount;
	@Column(nullable = true)
	private double price;
	@Column(nullable = true)
	private String image;
	
}