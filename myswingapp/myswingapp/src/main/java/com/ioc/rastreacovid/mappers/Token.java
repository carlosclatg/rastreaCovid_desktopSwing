package com.ioc.rastreacovid.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

//We set and get the information from the token

public class Token {
	String token;

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}