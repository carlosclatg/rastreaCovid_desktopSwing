package com.ioc.rastreacovid.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Contact {
    String _id;
    String name;
    String surname;
    Integer phone;

    @Override
    public String toString() {
        return "Contact{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone=" + phone +
                '}';
    }
}
