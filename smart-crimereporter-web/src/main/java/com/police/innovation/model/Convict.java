package com.police.innovation.model;

import java.util.List;


public class Convict extends Person {

	private List<String> crimeTypes;
	private String lastCriminalActivityDate;

	public List<String> getCrimeTypes() {
		return crimeTypes;
	}
	public void setCrimeTypes(List<String> crimeTypes) {
		this.crimeTypes = crimeTypes;
	}
	public String getLastCriminalActivityDate() {
		return lastCriminalActivityDate;
	}
	public void setLastCriminalActivityDate(String lastCriminalActivityDate) {
		this.lastCriminalActivityDate = lastCriminalActivityDate;
	}

	
}
