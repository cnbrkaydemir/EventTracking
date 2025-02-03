package com.trackevents.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Events extends BaseEntity{

 @Id
 @GeneratedValue(strategy= GenerationType.IDENTITY)
 @Column(name = "event_id")
 private int eventId;


 @Column(name = "event_title")
 @Size(message = "Title should be at least 5 characters",min = 5)
 @NotBlank
 private String eventTitle;

 @Column(name = "event_description")
 @Size(message = "Description should be at least 5 characters",min = 5)
 @NotBlank
 private String eventDescription;


 @Column(name = "event_date")
 private Date eventDate;


 @Column(name = "event_expired")
 private Date eventExpired;


 @ManyToMany(mappedBy = "events", fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
 private List<Users> participants = new ArrayList<>();


 @OneToOne(cascade = CascadeType.ALL)
 @JoinColumn(name = "user_id")
 private Users created_by;

 @Column(name = "is_expired")
 private boolean isExpired;

 public Events() {
  this.isExpired=false;
 }


}
