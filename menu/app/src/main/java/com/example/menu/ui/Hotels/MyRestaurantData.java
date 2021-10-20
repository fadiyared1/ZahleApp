package com.example.menu.ui.Hotels;

public class MyRestaurantData {
    private String restname;
    private String reststyle;
    private String restdesc;
    private Integer restimg;

    public MyRestaurantData(String restname, String reststyle, String restdesc, Integer restimg) {
        this.restname = restname;
        this.reststyle = reststyle;
        this.restdesc = restdesc;
        this.restimg = restimg;
    }

    public String getRestname() {
        return restname;
    }

    public void setRestname(String restname) {
        this.restname = restname;
    }

    public String getReststyle() {
        return reststyle;
    }

    public void setReststyle(String reststyle) {
        this.reststyle = reststyle;
    }

    public String getRestdesc() {
        return restdesc;
    }

    public void setRestdesc(String restdesc) {
        this.restdesc = restdesc;
    }

    public Integer getRestimg() {
        return restimg;
    }

    public void setRestimg(Integer restimg) {
        this.restimg = restimg;
    }
}
