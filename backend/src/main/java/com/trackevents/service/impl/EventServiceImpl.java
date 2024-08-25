package com.trackevents.service.impl;

import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.repository.EventRepository;
import com.trackevents.repository.UserRepository;
import com.trackevents.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

private final EventRepository eventRepository;

private final UserRepository userRepository;


public void setExpired(){
List<Events> events=eventRepository.findAll();
    long millis=System.currentTimeMillis();
    Date date= new Date(millis);

    events.forEach((event)->{
        event.setExpired(event.getEventExpired().compareTo(date) < 0);
    });

}

    public void saveEvent(Events event){
        eventRepository.save(event);
    }

    public void addUsers(int eventId,int userId){
        Events currentEvent=eventRepository.findByEventId(eventId);
        Users targetUser= userRepository.findByUserId(userId);
        currentEvent.getParticipants().add(targetUser);

    }

    public void discardUsers(int eventId,int userId){
        Events currentEvent=eventRepository.findByEventId(eventId);
        Users targetUser= userRepository.findByUserId(userId);
        currentEvent.getParticipants().remove(targetUser);
    }

   public List<Events> getAllEvents(){
    return eventRepository.findAll();
   }

   public Events findById(int id){
    return eventRepository.findByEventId(id);
   }


}
