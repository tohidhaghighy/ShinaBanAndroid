package com.example.shinabandudkani.ViewModel;

import com.example.shinabandudkani.DomainLayer.Amlak;
import com.example.shinabandudkani.DomainLayer.Category;

import java.util.List;

public class AmlakCategory {
    private List<Amlak> amlaks;
    private List<Category> categories;

    public List<Amlak> getAmlaks() {
        return amlaks;
    }

    public void setAmlaks(List<Amlak> amlaks) {
        this.amlaks = amlaks;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
