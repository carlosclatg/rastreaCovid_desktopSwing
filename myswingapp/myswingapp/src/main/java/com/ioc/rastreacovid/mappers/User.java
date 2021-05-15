package com.ioc.rastreacovid.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

// We set and get information user and then display them
// Generation of getters & setters


public class User {
	String _id;
	String name;
	String surname;
	String email;
	String type;
	Integer phone;
	String password;
	String passwordConfirm;

	@Override
	public String toString() {
		return "User{" + "_id=" + _id + ", name=" + name + ", surname=" + surname + " email=" + email + ", type='" + type + '\'' + ", phone='" + phone + '}';
	}
	
	public String get_id() {
		return _id;
	}

	public void set_Id(String _id) {
		this._id = _id;
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
	
	public String getPassword() {
		return password;
	}

	public void setPasword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPaswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}

	