package com.ioc.rastreacovid.mappers;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor


public class UpdateUser {
    String name;
    String surname;
    String password;
    String passwordConfirm;
    String email;
    String type;
    Integer phone;
}




/**
 * {
 *   "name": "IOC User",
 *   "surname": "IOC surname",
 *   "password": "UserIOC1234!",
 *   "passwordConfirm": "UserIOC1234!",
 *   "email": "opc@ioc.com",
 *   "type": "rastreator",
 *   "phone": "123456909"
 * }
*/