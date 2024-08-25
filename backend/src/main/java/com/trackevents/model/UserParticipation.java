package com.trackevents.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserParticipation {
    private String name;
    private int id;
    private int averageParticipation;
    private int userParticipation;

    public UserParticipation(String name, int id, int totalParticipation, int userParticipation) {
        this.name = name;
        this.id = id;
        this.averageParticipation = totalParticipation;
        this.userParticipation = userParticipation;
    }

    }
