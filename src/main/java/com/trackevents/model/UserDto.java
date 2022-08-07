package com.trackevents.model;


import java.util.List;

public class UserDto {

    private int eventId;

    private List<Integer> userIds;

    public UserDto(int eventId, List<Integer> userIds) {
        this.eventId = eventId;
        this.userIds = userIds;
    }

    public UserDto(){

    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
