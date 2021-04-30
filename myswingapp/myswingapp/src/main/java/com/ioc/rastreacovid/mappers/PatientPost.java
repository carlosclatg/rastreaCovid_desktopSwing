package com.ioc.rastreacovid.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

//** TODO NEXT SPRINT ** //

// In this class we initialize all the data we need for patient creation, so that we can then display them with information.
// Generation of getters & setters

public class PatientPost {
	List<ContactWithoutId> contacts;
	List<String> sintoms;
	String name;
	String surname;
	Integer phone;
	Integer birthDate;
	Integer PCRDate;

	public List<ContactWithoutId> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactWithoutId> contacts) {
		this.contacts = contacts;
	}

	public List<String> getSintoms() {
		return sintoms;
	}

	public void setSintoms(List<String> sintoms) {
		this.sintoms = sintoms;
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

	public Integer getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Integer birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getPCRDate() {
		return PCRDate;
	}

	public void setPCRDate(Integer pCRDate) {
		PCRDate = pCRDate;
	}
}
