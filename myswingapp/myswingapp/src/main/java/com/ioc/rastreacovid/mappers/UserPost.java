package com.ioc.rastreacovid.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

//Class to obtain the user data and to be able to do post
public class UserPost {
	String name;
	String surname;
	String password;
	String passwordConfirm;
	String email;
	String type;
	Integer phone;

}
