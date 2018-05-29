package com.police.innovation.persistance;

import java.util.List;

import com.police.innovation.model.CrimeReport;

public interface CrimeReportRepoCustom {

	List<CrimeReport> findPast(String type, String locality);
	
	List<CrimeReport> findRelated(String date, String locality);
	
}
