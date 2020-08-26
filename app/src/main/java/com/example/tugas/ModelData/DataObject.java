package com.example.tugas.ModelData;

public class DataObject {
    CountryInfo countryInfo;
    private String country,cases,deaths,recovered;

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public String getCountry() {
        return country;
    }

    public String getCases() {
        return cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getRecovered() {
        return recovered;
    }
}
