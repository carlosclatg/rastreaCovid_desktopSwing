package com.ioc.rastreacovid.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Stats {
    Integer nsintoms;
    public Integer getNsintoms() {
		return nsintoms;
	}
	public void setNsintoms(Integer nsintoms) {
		this.nsintoms = nsintoms;
	}
	public Integer getNcontacts() {
		return ncontacts;
	}
	public void setNcontacts(Integer ncontacts) {
		this.ncontacts = ncontacts;
	}
	Integer ncontacts;
}
