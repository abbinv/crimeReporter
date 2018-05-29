package com.police.innovation.client;

import com.police.innovation.model.kadaster.LocationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * This class contains methods to call the ODS rest endPoints
 *
 */
@Component
public class KadasterClient {


    @Autowired
    private RestOperations restClient;

    public ResponseEntity<LocationResponse> suggestLocation(String location) {

        ResponseEntity<LocationResponse> flight = null;
        UriComponents builder = UriComponentsBuilder.fromHttpUrl("https://geodata.nationaalgeoregister.nl/locatieserver/v3/suggest?wt=xml&q=")
                        .buildAndExpand(location);
        try {
            flight = restClient.exchange(builder.toUri(), HttpMethod.GET, getEntity(), LocationResponse.class);
        }
        catch (Exception e) {
            System.out.print("error " +  e.getMessage());
        }
        return flight;

    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public ResponseEntity<Flight> getDetailsOfSpecificFlightLegDeparture(GetFlightDetailsRequest getFlightDetailsRequest) {
//        ResponseEntity<Flight> flight = null;
//        UriComponents builder = UriComponentsBuilder.fromHttpUrl("https://geodata.nationaalgeoregister.nl/locatieserver/v3/suggest?wt=xml&q=")
//                        .buildAndExpand();
//        try {
//            flight = restClient.exchange(builder.toUri(), HttpMethod.GET, getEntity(), Flight.class);
//        }
//        catch (ResourceAccessException | HttpStatusCodeException exception) {
//            LOG.error("Exception occurred in F720 call to retrieve details of a specific flight leg departure " + exception);
//        }
//
//        return flight;
//    }

    private HttpEntity<String> getEntity() {
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<String>(httpHeader);
    }

}
