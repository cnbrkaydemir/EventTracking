package com.trackevents.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
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

   }
