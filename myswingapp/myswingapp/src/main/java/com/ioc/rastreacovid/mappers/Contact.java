package com.ioc.rastreacovid.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

// In this class we start all the data we need from the contacts, so that we can later display them with information
// Generation of getters & setters

public class Contact {
	String _id;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
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

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	String name;
	String surname;
	Integer phone;

	@Override
	public String toString() {
		return "Contact{" + "_id='" + _id + '\'' + ", name='" + name + '\'' + ", surname='" + surname + '\''
				+ ", phone=" + phone + '}';
	}
}
