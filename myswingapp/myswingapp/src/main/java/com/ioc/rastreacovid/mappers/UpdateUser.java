package com.ioc.rastreacovid.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

//Class that we use to be able to update a user, with the getters and setters.
public class UpdateUser {
	String name;
	String surname;
	String password;
	String passwordConfirm;
	String email;
	String type;
	Integer phone;
}
