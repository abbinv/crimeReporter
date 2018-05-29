package com.police.innovation.persistance;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.police.innovation.model.CaseFileResponse;
import com.police.innovation.model.DialogueResponse;


@Service
public class Services {
    Logger LOG = LoggerFactory.getLogger(this.getClass().getName());
    private RestTemplate restTemplate = new RestTemplate();

    public HashMap getIntents(String description){

        String url = "https://api.api.ai/v1/query?v=20150910&sessionId=01&query=" + description;
        HttpEntity httpEntity = new HttpEntity("", getHttpHeaders());
        ResponseEntity<DialogueResponse> response = restTemplate.exchange("https://api.api.ai/v1/query?v=20150910&query=" + description + "&lang=en-GB&sessionId=01", HttpMethod.GET, httpEntity, DialogueResponse.class);
        DialogueResponse rs = response.getBody();

        return rs.getResult().getParameters();
    }
    
    public String getCaseFile() {

        String url = "https://api.acceptatie.politie.nl/./v4/gezocht/dossiers?language=nl&lat=52.2861962&lon=4.7820705&radius=5.0&maxnumberofitems=10&offset=0";
        
        HttpEntity<String> entity = new HttpEntity<String>(populateRequestHeaders());
        ResponseEntity<CaseFileResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, CaseFileResponse.class);
        CaseFileResponse rs = response.getBody();


        return rs.toString();
    }

    private HttpHeaders populateRequestHeaders() {
    	 HttpHeaders httpHeader = new HttpHeaders();
         httpHeader.add("Accept", "application/json");
         httpHeader.add("x-api-key", "b1a3c979-6561-4e67-81a9-78fc00dc23c5");
         httpHeader.setContentType(MediaType.APPLICATION_JSON);
         return httpHeader;
	}

	private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer 8933675d075f49f39ec08be3afdeb2a4");

        return httpHeaders;
    }


}
