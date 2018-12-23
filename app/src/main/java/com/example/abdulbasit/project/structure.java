package com.example.abdulbasit.project;

public class structure {
    String description;
    String date;
    String time;

    public structure(String d, String da, String ti) {
        description = d;
        date = da;
        time = ti;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
