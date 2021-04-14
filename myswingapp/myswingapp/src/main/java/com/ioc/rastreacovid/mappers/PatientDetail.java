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
public class PatientDetail {
    List<Contact> contacts;
    List<Sintom> sintoms;
    String _id;
    String name;
    String surname;
    Integer phone;
    Date birthdate;
    Date PCRDate;
    String createdby;

    @Override
    public String toString() {
        return "PatientDetail{" +
                "contacts=" + contacts +
                ", sintoms=" + sintoms +
                ", _id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone=" + phone +
                ", birthdate=" + birthdate +
                ", PCRDate=" + PCRDate +
                ", createdby='" + createdby + '\'' +
                '}';
    }


}
