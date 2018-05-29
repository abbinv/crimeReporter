package com.police.innovation.persistance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.police.innovation.model.CrimeReport;

@Repository
public interface CrimeReportRepo extends MongoRepository<CrimeReport, String>, CrimeReportRepoCustom {
	
	
	
}
