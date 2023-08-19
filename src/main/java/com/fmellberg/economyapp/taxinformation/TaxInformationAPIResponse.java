package com.fmellberg.economyapp.taxinformation;


import java.util.List;

public class TaxInformationAPIResponse {

    // Additional/optional fields, used for e.g. api url parameters
    private String next;
    private int resultCount;
    private int offset;
    private int limit;
    private int queryTime;


    // Results field is enough
    // See TaxInformationService: getBody.getResults()
    // Result fields are extracted in alignment with corresponding json properties in TaxInformationDTOResponse
    private List<TaxInformationDTOResponse> results; // See TaxInformationDTOResponse json property fields

    public TaxInformationAPIResponse() {
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(int queryTime) {
        this.queryTime = queryTime;
    }

    public List<TaxInformationDTOResponse> getResults() {
        return results;
    }

    public void setResults(List<TaxInformationDTOResponse> results) {
        this.results = results;
    }
}
