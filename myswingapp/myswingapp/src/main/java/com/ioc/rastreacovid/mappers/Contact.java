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

	public String getNamec() {
		return namec;
	}

	public void setNamec(String namec) {
		this.namec = namec;
	}

	public String getSurnamec() {
		return surnamec;
	}

	public void setSurnamec(String surnamec) {
		this.surnamec = surnamec;
	}

	public Integer getPhonec() {
		return phonec;
	}

	public void setPhonec(Integer phonec) {
		this.phonec = phonec;
	}

	String namec;
	String surnamec;
	Integer phonec;

	public String toString2() {
		return "Contact{" + "_id='" + _id + '\'' + ", namec='" + namec + '\'' + ", surnamec='" + surnamec + '\''
				+ ", phonec=" + phonec + '}';
	}
}
