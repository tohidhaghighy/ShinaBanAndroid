package com.example.shinabandudkani.ViewModel;

import com.example.shinabandudkani.DomainLayer.Amlak;

import java.util.List;

public class AmlakDetail {
    private int Id;
    private String Tittle;
    private String Description;
    private List<String> Allproperty;
    private List<String> Allimages;
    private List<Amlak> Allamlak;
    private String Name;
    private String Email;
    private String Board_Name;
    private String Tell;
    private String Lat;
    private String Lng;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<String> getAllproperty() {
        return Allproperty;
    }

    public void setAllproperty(List<String> allproperty) {
        Allproperty = allproperty;
    }

    public List<String> getAllimages() {
        return Allimages;
    }

    public void setAllimages(List<String> allimages) {
        Allimages = allimages;
    }

    public List<Amlak> getAllamlak() {
        return Allamlak;
    }

    public void setAllamlak(List<Amlak> allamlak) {
        Allamlak = allamlak;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBoard_Name() {
        return Board_Name;
    }

    public void setBoard_Name(String board_Name) {
        Board_Name = board_Name;
    }

    public String getTell() {
        return Tell;
    }

    public void setTell(String tell) {
        Tell = tell;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLng() {
        return Lng;
    }

    public void setLng(String lng) {
        Lng = lng;
    }
}
