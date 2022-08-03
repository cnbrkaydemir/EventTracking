package com.trackevents.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")

public class Events {

 @Id
 @GeneratedValue(strategy= GenerationType.AUTO)
 @GenericGenerator(name = "native",strategy = "native")
 @Column(name = "event_id")
 private int eventId;


 @NotBlank
 @Size(message = "Title should be at least 5 characters",min = 5)
 @Column(name = "event_title")
private String eventTitle;


 @NotBlank
 @Size(message = "Description should be at least 5 characters",min = 5)
 @Column(name = "event_description")
 private String eventDescription;


 @NotBlank
 @Column(name = "event_date")
 private Date eventDate;


 @NotBlank
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


 public int getEventId() {
  return eventId;
 }

 public void setEventId(int eventId) {
  this.eventId = eventId;
 }

 public String getEventTitle() {
  return eventTitle;
 }

 public void setEventTitle(String eventTitle) {
  this.eventTitle = eventTitle;
 }

 public String getEventDescription() {
  return eventDescription;
 }

 public void setEventDescription(String eventDescription) {
  this.eventDescription = eventDescription;
 }

 public Date getEventDate() {
  return eventDate;
 }

 public void setEventDate(Date eventDate) {
  this.eventDate = eventDate;
 }

 public Date getEventExpired() {
  return eventExpired;
 }

 public void setEventExpired(Date eventExpired) {
  this.eventExpired = eventExpired;
 }

 public List<Users> getParticipants() {
  return participants;
 }

 public void setParticipants(List<Users> participants) {
  this.participants = participants;
 }

 public boolean isExpired() {
  return isExpired;
 }

 public void setExpired(boolean expired) {
  isExpired = expired;
 }

 public Users getCreated_by() {
  return created_by;
 }

 public void setCreated_by(Users created_by) {
  this.created_by = created_by;
 }

}
