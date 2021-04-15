package com.couponsystem.beans;

import com.couponsystem.enums.ClientType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginForm {

	private String email;
	private String passwoed;
	private ClientType clientType;
}
