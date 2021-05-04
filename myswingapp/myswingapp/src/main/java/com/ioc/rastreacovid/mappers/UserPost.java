package com.ioc.rastreacovid.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

// We set and get information user and then display them
// Generation of getters & setters


public class UserPost {
	String name;
	String surname;
	String pass;
	String cpass;


	String email;
	String type;
	Integer phone;

	@Override
	public String toString() {
		return "User{" + "name=" + name + ", surname=" + surname + ", pass=" + pass + ", cpass=" + cpass +" email=" + email + ", type='" + type + '\'' + ", phone='" + phone + '}';
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getCpass() {
		return cpass;
	}


	public void setCpass(String cpass) {
		this.cpass = cpass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}
}

	