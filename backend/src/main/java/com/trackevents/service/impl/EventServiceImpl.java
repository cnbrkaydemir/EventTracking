package com.trackevents.service.impl;

import com.trackevents.dto.EventDto;
import com.trackevents.dto.ParticipationDto;
import com.trackevents.dto.UserDto;
import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.repository.EventRepository;
import com.trackevents.repository.UserRepository;
import com.trackevents.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

private final EventRepository eventRepository;

private final UserRepository userRepository;

private  final ModelMapper modelMapper;


public void setExpired(){
List<Events> events=eventRepository.findAll();
    long millis=System.currentTimeMillis();
    Date date= new Date(millis);

    events.forEach((event)->{
        event.setExpired(event.getEventExpired().compareTo(date) < 0);
    });

}

    public EventDto createEvent(Events event){
        Users adminUser = userRepository.findByUserEmail(event.getCreated_by().getUserEmail()).get(0);
        event.setCreated_by(adminUser);
        event.getParticipants().add(adminUser);
        adminUser.getEvents().add(event);
        eventRepository.save(event);

        return modelMapper.map(eventRepository.save(event), EventDto.class);
    }


    public EventDto saveEvent(Events event){
        return modelMapper.map(eventRepository.save(event), EventDto.class);
    }

    public EventDto addUsers(ParticipationDto info){
        Events event = eventRepository.findById(info.getEventId()).get();

        info.getUsers().forEach((userDto) -> {
            Users participant = userRepository.findByUserId(userDto.getUserId());
            participant.getEvents().add(event);
            event.getParticipants().add(participant);
            List<Users> u = event.getParticipants();
        });

        return modelMapper.map(eventRepository.save(event), EventDto.class);

    }

    public EventDto discardUsers(ParticipationDto info){
        Events event = eventRepository.findById(info.getEventId()).get();

        info.getUsers().forEach((userDto) -> {
            Users participant = userRepository.findByUserId(userDto.getUserId());
            event.getParticipants().remove(participant);
            participant.getEvents().remove(event);
        });

        return modelMapper.map(eventRepository.save(event), EventDto.class);
    }

   public List<EventDto> getAllEvents(){
    return eventRepository.findAll().stream().map((event)->modelMapper.map(event, EventDto.class)).toList();
   }

   public EventDto findById(int id){
    return modelMapper.map(eventRepository.findByEventId(id), EventDto.class);
   }


}
