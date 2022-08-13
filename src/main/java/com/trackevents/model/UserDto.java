package com.trackevents.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private int eventId;

    private List<Integer> userIds;

    public UserDto(int eventId, List<Integer> userIds) {
        this.eventId = eventId;
        this.userIds = userIds;
    }

    public UserDto(){

    }

}
