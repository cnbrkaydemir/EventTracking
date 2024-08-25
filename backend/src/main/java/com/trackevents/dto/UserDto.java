package com.trackevents.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int userId;

    private String userName;

    private String userSurname;

    private String userEmail;

    private String userRole;


}
