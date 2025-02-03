package com.trackevents.dto;


import lombok.Data;

@Data
public class LoginDto extends BaseDto {

    private int userId;

    private String userName;

    private String userSurname;

    private String userEmail;

    private String userRole;

    private String token;

    public static LoginDto of(UserDto userDto, String token) {
        LoginDto loginDto = new LoginDto();

        loginDto.setUserId(userDto.getUserId());
        loginDto.setUserName(userDto.getUserName());
        loginDto.setUserSurname(userDto.getUserSurname());
        loginDto.setUserEmail(userDto.getUserEmail());
        loginDto.setUserRole(userDto.getUserRole());
        loginDto.setToken(token);
        return loginDto;
    }
}
