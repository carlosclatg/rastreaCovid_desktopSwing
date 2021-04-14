package com.ioc.rastreacovid.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Sintom {
    String _id;
    String sintoma_cat;
    String sintoma_es;
    String sintoma_eng;

    @Override
    public String toString() {
        return "Sintom{" +
                "_id='" + _id + '\'' +
                ", sintoma_cat='" + sintoma_cat + '\'' +
                ", sintoma_es='" + sintoma_es + '\'' +
                ", sintoma_eng='" + sintoma_eng + '\'' +
                '}';
    }
}
