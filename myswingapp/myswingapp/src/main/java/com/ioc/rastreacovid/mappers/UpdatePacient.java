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
public class UpdatePacient {

	List<ContactWithoutId> contacts;
	List<String> sintoms;
	String _id;
	String name;
	String surname;
	Integer phone;
	long birthDate;
	long PCRDate;
}