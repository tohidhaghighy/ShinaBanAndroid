package com.example.shinabandudkani.DomainLayer;

public class SaveSetting {
    private Integer Firsttime;
    private String Language;
    private Integer CityId;

    public Integer getFirsttime() {
        return Firsttime;
    }

    public void setFirsttime(Integer firsttime) {
        Firsttime = firsttime;
    }



    public Integer getCityId() {
        return CityId;
    }

    public void setCityId(Integer cityId) {
        CityId = cityId;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
}
