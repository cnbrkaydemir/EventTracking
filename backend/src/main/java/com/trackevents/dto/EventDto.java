package com.trackevents.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private int eventId;

    private String eventTitle;

    private String eventDescription;

    private Date eventDate;

    private Date eventExpired;

    private int createdId;

}
