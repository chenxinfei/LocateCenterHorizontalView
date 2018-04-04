package com.bocweb.fly.locatecenterhorizontalview;

/**
 * Created by fly on 2018/4/4.
 */

public class ContinentModel {
    private int id;
    private String continentName;
    private String sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContinentModel(int id, String continentName, String sort) {
        this.id = id;
        this.continentName = continentName;
        this.sort = sort;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
