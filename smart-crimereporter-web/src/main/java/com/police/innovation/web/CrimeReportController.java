package com.police.innovation.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.police.innovation.persistance.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.police.innovation.model.CrimeReport;
import com.police.innovation.model.FinalReports;
import com.police.innovation.persistance.CrimeReportRepo;

@RestController
public class CrimeReportController {

    Logger LOG = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private Services findIntentsService;
    @Autowired
    private CrimeReportRepo crimeReportRepo;

    @GetMapping(path = "/")
    public CrimeReport getReportTest() {
        LOG.info("CrimeReport home page");
        findIntentsService.getIntents("a german man was carrying a gun for self defense");
        return null;
    }


    @PostMapping(path = "/crimes")
    public FinalReports addReport(@RequestBody CrimeReport crimeReport) {
        LOG.info("Entered controller to fetch past crime reports");
        addParams(crimeReport);
        List<CrimeReport> pastCrimeReport = crimeReportRepo.findPast(crimeReport.getType(), crimeReport.getLocality());
        List<CrimeReport> filteredPastCrimeReport = filterCrime(crimeReport, pastCrimeReport);
        List<CrimeReport> relatedCrimes = crimeReportRepo.findRelated(crimeReport.getDate(), crimeReport.getLocality());
        List<CrimeReport> filteredRelatedCrimeReport = filterCrime(crimeReport, relatedCrimes);
        crimeReportRepo.save(crimeReport);

        if(null != pastCrimeReport && !pastCrimeReport.isEmpty()) {
        	return FinalReports.builder()
        					.pastCrimes(filteredPastCrimeReport)
        					.relatedCrimes(filteredRelatedCrimeReport)
        					.build();
        } else {
        	return FinalReports.builder()
        					.error("There are no matching records found for this crime")
        					.build();
        }

    }

    private List<CrimeReport> filterCrime(CrimeReport newCrimeReport, List<CrimeReport> crimeReports) {
        List<CrimeReport> filtered= new ArrayList<CrimeReport>();
        if (crimeReports !=null && !crimeReports.isEmpty()){
        for(CrimeReport crimeReport1 : crimeReports){
            if(newCrimeReport.getParameters() != null) {
                Object newCrimePersonTypes = newCrimeReport.getParameters().get("person-type");
                Object newCrimeWeaponTypes = newCrimeReport.getParameters().get("weapon-type");
                HashMap hashMapPerson = (HashMap)newCrimePersonTypes;
                HashMap hashMapWeapon = (HashMap)newCrimeWeaponTypes;
                if (hashMapPerson.values())
            }
        }}
        return filtered;
    }

    private void addParams(CrimeReport crimeReport) {
        crimeReport.setParameters(findIntentsService.getIntents(crimeReport.getDescription()));
    }
}
