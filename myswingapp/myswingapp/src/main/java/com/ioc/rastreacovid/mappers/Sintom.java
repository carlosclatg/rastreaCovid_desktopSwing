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
    public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getSintoma_cat() {
		return sintoma_cat;
	}

	public void setSintoma_cat(String sintoma_cat) {
		this.sintoma_cat = sintoma_cat;
	}

	public String getSintoma_es() {
		return sintoma_es;
	}

	public void setSintoma_es(String sintoma_es) {
		this.sintoma_es = sintoma_es;
	}

	public String getSintoma_eng() {
		return sintoma_eng;
	}

	public void setSintoma_eng(String sintoma_eng) {
		this.sintoma_eng = sintoma_eng;
	}

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
