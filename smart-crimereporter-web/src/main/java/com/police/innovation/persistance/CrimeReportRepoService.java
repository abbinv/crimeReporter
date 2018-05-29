package com.police.innovation.persistance;

import com.police.innovation.model.CrimeReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrimeReportRepoService {
	Logger LOG = LoggerFactory.getLogger(this.getClass().getName());


	public void updateSentNotifications(CrimeReport crimeReport) {}

	public List<CrimeReport> getUserSearchCriteria() {
		return null;
		
	}

}
