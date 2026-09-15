package com.sdsufba.documentdata;

import java.util.Date;

public class DataDocment {

    private String name;
    private String camp;
    private Date date = new Date();

    public DataDocment() {
    }

    public DataDocment(String name, String camp, Date date) {
        this.name = name;
        this.camp = camp;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCamp() {
        return camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }

    public Date getDate() {
        return date;
    }
}
