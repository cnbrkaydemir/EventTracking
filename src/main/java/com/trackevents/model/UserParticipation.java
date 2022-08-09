package com.trackevents.model;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAverageParticipation() {
        return averageParticipation;
    }

    public void setAverageParticipation(int averageParticipation) {
        this.averageParticipation = averageParticipation;
    }

    public int getUserParticipation() {
        return userParticipation;
    }

    public void setUserParticipation(int userParticipation) {
        this.userParticipation = userParticipation;
    }
}
