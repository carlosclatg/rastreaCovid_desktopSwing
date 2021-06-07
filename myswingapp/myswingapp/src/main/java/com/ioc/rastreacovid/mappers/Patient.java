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

// We set and get information patient and then display them
// Generation of getters & setters

public class Patient {
	List<String> contacts;
	List<String> sintoms;
	String _id;
	String name;
	String surname;
	Integer phone;
	Date birthdate;
	Date PCRDate;
	String createdby;

	@Override
	public String toString() {
		return "Patient{" + "contacts=" + contacts + ", sintoms=" + sintoms + ", _id='" + _id + '\'' + ", name='" + name
				+ '\'' + ", surname='" + surname + '\'' + ", phone=" + phone + ", birthdate=" + birthdate + ", PCRDate="
				+ PCRDate + ", createdby='" + createdby + '\'' + '}';
	}

	public List<String> getContacts() {
		return contacts;
	}

	public void setContacts(List<String> contacts) {
		this.contacts = contacts;
	}

	public List<String> getSintoms() {
		return sintoms;
	}

	public void setSintoms(List<String> sintoms) {
		this.sintoms = sintoms;
	}

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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getPCRDate() {
		return PCRDate;
	}

	public void setPCRDate(Date pCRDate) {
		PCRDate = pCRDate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
}
