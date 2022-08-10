package com.trackevents.model;

import java.sql.Date;

public class EventInfo {

    private String title;

    private long dueDate;

    private int id;

    private Date eventExpire;

    public EventInfo(String title, long dueDate, int id,Date eventExpire) {
        this.title = title;
        this.dueDate = dueDate;
        this.id = id;
        this.eventExpire=eventExpire;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEventExpire() {
        return eventExpire;
    }

    public void setEventExpire(Date eventExpire) {
        this.eventExpire = eventExpire;
    }
}
