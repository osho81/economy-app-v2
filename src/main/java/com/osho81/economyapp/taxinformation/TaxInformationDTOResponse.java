package com.osho81.economyapp.taxinformation;

import com.fasterxml.jackson.annotation.JsonProperty;

// Structure for data to be used by TaxInformation APIResponse
// See & compare the equivalent field names in the response as explained here:
// https://skatteverket.entryscape.net/rowstore/dataset/c67b320b-ffee-4876-b073-dd9236cd2a99/swagger

public class TaxInformationDTOResponse {

    @JsonProperty("kyrkoavgift") // Properties extracted from "results", from http request
    private double churchFee;
    @JsonProperty("summa, inkl. kyrkoavgift")
    private double totalTaxAmountIncludingChurchFee;
    @JsonProperty("summa, exkl. kyrkoavgift")
    private double totalTaxAmountExcludingChurchFee;
    @JsonProperty("församling")
    private String churchDistrict;
    @JsonProperty("kommunal-skatt")
    private double municipalTax;
    @JsonProperty("församlings-kod")
    private String churchDistrictCode;
    @JsonProperty("kommun")
    private String municipality;
    @JsonProperty("begravnings-avgift")
    private double burialFee;
    @JsonProperty("landstings-skatt")
    private double countyTax;
    @JsonProperty("år")
    private int incomeYear;

    public TaxInformationDTOResponse() {
    }

    public double getChurchFee() {
        return churchFee;
    }

    public void setChurchFee(double churchFee) {
        this.churchFee = churchFee;
    }

    public double getTotalTaxAmountIncludingChurchFee() {
        return totalTaxAmountIncludingChurchFee;
    }

    public void setTotalTaxAmountIncludingChurchFee(double totalTaxAmountIncludingChurchFee) {
        this.totalTaxAmountIncludingChurchFee = totalTaxAmountIncludingChurchFee;
    }

    public double getTotalTaxAmountExcludingChurchFee() {
        return totalTaxAmountExcludingChurchFee;
    }

    public void setTotalTaxAmountExcludingChurchFee(double totalTaxAmountExcludingChurchFee) {
        this.totalTaxAmountExcludingChurchFee = totalTaxAmountExcludingChurchFee;
    }

    public String getChurchDistrict() {
        return churchDistrict;
    }

    public void setChurchDistrict(String churchDistrict) {
        this.churchDistrict = churchDistrict;
    }

    public double getMunicipalTax() {
        return municipalTax;
    }

    public void setMunicipalTax(double municipalTax) {
        this.municipalTax = municipalTax;
    }

    public String getChurchDistrictCode() {
        return churchDistrictCode;
    }

    public void setChurchDistrictCode(String churchDistrictCode) {
        this.churchDistrictCode = churchDistrictCode;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public double getBurialFee() {
        return burialFee;
    }

    public void setBurialFee(double burialFee) {
        this.burialFee = burialFee;
    }

    public double getCountyTax() {
        return countyTax;
    }

    public void setCountyTax(double countyTax) {
        this.countyTax = countyTax;
    }

    public int getIncomeYear() {
        return incomeYear;
    }

    public void setIncomeYear(int incomeYear) {
        this.incomeYear = incomeYear;
    }
}
