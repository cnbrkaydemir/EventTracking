package com.trackevents.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@JsonIgnoreProperties(value = {"authorities","userId"})
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    @NotBlank
    private String userName;

    @Column(name = "user_surname")
    @NotBlank
    private String userSurname;


    @Column(name = "user_email")
    @Size(min=6, message = "Email should be at least 6 characters")
    @NotBlank
    private String userEmail;


    @Column(name = "user_password")
    @Size(min=5, message = "Password should be at least 5 characters")
    @NotBlank
    private String userPassword;


    @Column(name="user_role")
    @NotBlank
    private String userRole;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "participation",
            joinColumns = {
                    @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "event_id")})
    private List<Events> events = new ArrayList<>();




    @OneToMany(mappedBy="users",fetch=FetchType.EAGER)
    private Set<Authority> authorities;


    public Users(){

    }


    public Users(int userId, String userName, String userSurname, String userEmail, String userPassword, String role) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRole = role;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
