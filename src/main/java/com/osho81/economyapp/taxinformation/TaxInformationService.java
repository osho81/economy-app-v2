package com.osho81.economyapp.taxinformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TaxInformationService {

    @Autowired
    private Environment environment;
    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    public List<TaxInformationDTOResponse> getTaxInformation(int year) { // Modified 230624
        String apiURL = environment.getProperty("skatteverketAPI.url"); // If base url is set in appl.prop
        String urlParas = (year == 0) ? "": ("?år=" + year); // if year is sent from controller, add this query para
        // Use UrlComponentsBuilder etc if multiple paras; this is a solution for practise only

        // Http request using (deprecated) RestTemplate http tool, in this case the exchange method
        // DTO extracts json properties, via TaxInformationAPIResponse.class
        ResponseEntity<TaxInformationAPIResponse> responseEntity = restTemplate.exchange(apiURL + urlParas, HttpMethod.GET, null, TaxInformationAPIResponse.class);
//        ResponseEntity<TaxInformationAPIResponse> responseEntity = restTemplate.exchange(apiURL, HttpMethod.GET, entity, TaxInformationAPIResponse.class, 1000);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println(responseEntity.getBody().getLimit()); // server returns only 100 by default
            return responseEntity.getBody().getResults(); // Get and return the now extracted and filled response DTO list
        } else {
            // Handle error response
            System.out.println("API request failed with status code: " + responseEntity.getStatusCodeValue());
        }

        return null;
    }

}
