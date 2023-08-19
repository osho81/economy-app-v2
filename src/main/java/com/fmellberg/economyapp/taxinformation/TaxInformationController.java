package com.fmellberg.economyapp.taxinformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tax-information")
public class TaxInformationController {


    private final TaxInformationService taxInformationService;

    @Autowired
    public TaxInformationController(TaxInformationService taxInformationService) {
        this.taxInformationService = taxInformationService;
    }

    @GetMapping()
    public ResponseEntity<List<TaxInformationDTOResponse>> getTaxInformation() {
        // Returns a DTO list, extracted in the process of the http service method
        List<TaxInformationDTOResponse> response = taxInformationService.getTaxInformation(0); // No paras in url
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Added 230624
    @GetMapping("/year/{year}")
    public ResponseEntity<List<TaxInformationDTOResponse>> getTaxInfoSpecificYear(@PathVariable int year) {
        List<TaxInformationDTOResponse> taxInfoSpecificYear = new ArrayList<>();
//        List<TaxInformationDTOResponse> response = taxInformationService.getTaxInformation();
        List<TaxInformationDTOResponse> response = taxInformationService.getTaxInformation(year); // has para year

        System.out.println(year);
        System.out.println(response.size());
        for (int i = 0; i < response.size(); i++) {
            System.out.println(response.get(i).getIncomeYear());
            if (response.get(i).getIncomeYear() == year) {
                System.out.println("Hit");
                taxInfoSpecificYear.add(response.get(i));
            }
        }
//        return taxInfoSpecificYear; // If use this return, change method to return list, not responseEntity
        return ResponseEntity.ok(taxInfoSpecificYear);
    }

}
