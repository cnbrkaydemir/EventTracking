package com.trackevents.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "participation",
            joinColumns = {
                    @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "event_id")})
    private List<Events> events = new ArrayList<>();



@JsonIgnore
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


}
