package com.couponsystem.beans;

import com.couponsystem.enums.ClientType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
	
	private String token;
	private ClientType type;
	
}