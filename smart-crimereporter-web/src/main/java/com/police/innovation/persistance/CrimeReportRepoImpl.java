package com.police.innovation.persistance;

import java.util.List;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import com.police.innovation.model.CrimeReport;

public class CrimeReportRepoImpl implements CrimeReportRepoCustom{

	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public List<CrimeReport> findPast(String type, String locality) {
	   Query query = new Query();
       query.addCriteria(where("type").is(type).and("locality").is(locality));
     
     List<CrimeReport> crimeReports = mongoOperations.find(query, CrimeReport.class);
     if(null != crimeReports && !crimeReports.isEmpty()) {
    	 return crimeReports;
     }
	return null;
      
	}

	@Override
	public List<CrimeReport> findRelated(String date, String locality) {
		 Query query = new Query();
	       query.addCriteria(where("date").is(date).and("locality").is(locality));
	     
	     List<CrimeReport> crimeReport = mongoOperations.find(query, CrimeReport.class);
	     if(null != crimeReport && !crimeReport.isEmpty()) {
	    	 return crimeReport;
	     } else {
	    	  return null;
	     }
	}

}
