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

// Class that we use to be able to update a patient, with the getter and setters.
public class UpdatePacient {

	List<ContactWithoutId> contacts;
	List<String> sintoms;
	String name;
	String surname;
	Integer phone;
	long birthDate;
	long PCRDate;

	@Override
	public String toString() {
		return "UpdatePacient{" + "contacts=" + contacts + ", sintoms=" + sintoms + ", name='" + name + '\''
				+ ", surname='" + surname + '\'' + ", phone=" + phone + ", birthDate=" + birthDate + ", PCRDate="
				+ PCRDate + '}';
	}
}